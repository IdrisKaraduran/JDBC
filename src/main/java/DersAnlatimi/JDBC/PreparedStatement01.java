package DersAnlatimi.JDBC;

import java.sql.*;
/*
PreparedStatement Statement interface'ini extend eder.
Statement ile oluşturduğumuz sorgu için DBde bellekte sorgunun bir örneği oluşturulur.
Sorgu her çalıştırıldığında yeni bir örneği oluşturulur.
PreparedStatement; birden fazla kez çalıştırılabile parametrelendirilmiş SQL sorgularını temsil eder.
PreparedStatement ile sorgu oluşturduğumuzda bu sorgunun örneği DB de bellekte bir tutulur,
sorgu her çalıştırıldığında aynı önceki örneği kullanılır.
*/

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        Statement st = con.createStatement();

       //ÖRNEK1:Prepared Statement kullanarak bolumler tablosunda
       // Matematik bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
       // String sql1 = "Update bolumler set taban_puanı=475 where bolum ilike 'Matematik'"
       // st.executeUpdate(sql1);
       // st.executeUpdate("Update bolumler set taban_puanı=475 where bolum ilike 'Ceng'")

        /*
        1.PreparedStatement icin parametreli query i yaz.
        2.Prepared Statement olustur
         //prepared statement için parametreli queryi yaz
           //parametrelerin değerlerini gir
           //prepared statement ile queryi çalıştır.
         */
        String sql2 = "update bolumler set taban_puanı=? where bolum ilike ?";
        PreparedStatement prst = con.prepareStatement(sql2);
        prst.setInt(1,475);
        prst.setString(2,"matematik");

        int updatet = prst.executeUpdate();
        System.out.println("Etkilenen : " + updatet);

        //tum kayirlari gormek icin
        ResultSet rs = st.executeQuery("select * from bolumler");
        while(rs.next()){
            System.out.println(rs.getInt("bolum_id")+" " +
                    rs.getString("bolum") + " "+ rs.getInt("taban_puanı"));
        }

        //ÖRNEK2:Prepared Statement kullanarak bolumler tablosunda
        //Edebiyat bölümünün taban_puanı'nı 455 olarak güncelleyiniz.

        prst.setInt(1,455);
        prst.setString(2,"edebiyat");
        int updatet1 = prst.executeUpdate();
        System.out.println("Etkilenen : "+updatet1);

        ResultSet rs1 = st.executeQuery("select * from bolumler");
        while(rs1.next()){
            System.out.println(rs1.getInt("bolum_id")+" " +
                    rs1.getString("bolum") + " "+ rs1.getInt("taban_puanı"));
        }
        prst.close();
        st.close();
        con.close();



    }
}
