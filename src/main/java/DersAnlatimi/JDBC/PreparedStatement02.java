package DersAnlatimi.JDBC;

import java.sql.*;
/*
CallableStatement:SQL de geriye data return eden metodlara fonksiyon
                         geriye data return etmeyenlere prosedür denir.
                  Connection'ın  prepareCall metodu ile callablestament oluşturarak
                  Java uygulamızda SQL fonksiyonları/prosedürleri çağrılabilir.
CallableStatement Statement extend eder.
 */
public class PreparedStatement02 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        Statement st = con.createStatement();

        //ÖRNEK1:Prepared Statement kullanarak ogrenciler
        // tablosundan Matematik bölümünde okuyanları siliniz.

        String sql = "delete from ogrenciler where bolum ilike ?";
        PreparedStatement prst = con.prepareStatement(sql);
        prst.setString(1,"matematik");
        int deletet = prst.executeUpdate();
        System.out.println("Deletet : " + deletet);

        //ÖRNEK2:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=5006,taban_puanı=475,
        // kampus='Merkez') bölümünü ekleyiniz.

        String sql2 = "Insert into bolumler values(?,?,?,?)";
        PreparedStatement prst2 = con.prepareStatement(sql2);
        prst2.setInt(1,5006);
        prst2.setString(2,"Yazilim Muhendisligi");
        prst2.setInt(3,475);
        prst2.setString(4,"Merkez");
        int insertet = prst2.executeUpdate();
        System.out.println("Eklenen : " + insertet);

        prst2.close();
        prst.close();
        st.close();
        con.close();





    }
}
