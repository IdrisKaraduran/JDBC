package DersAnlatimi.JDBC;

import java.sql.*;

public class Execute01 {
    public static void main(String[] args) throws SQLException {

        //2.Adim
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        //3.Adim
        Statement st = con.createStatement();
        //ÖRNEK 1:id'si 5 ile 10 arasında olan ülkelerin "country_name" bilgisini listeleyiniz.
        String query1 ="select country_name from countries where id between  5 and 10";
        boolean sql1 = st.execute(query1);
        System.out.println(sql1);
        //Kayitlari gormek icin execute.Query() kullanmaliyiz
        ResultSet resultSet = st.executeQuery(query1);
//        resultSet.next();
//        System.out.println(resultSet.getString("country_name"));
        while(resultSet.next()){
              System.out.println("Ulke Ismi " +resultSet.getString("country_name"));
           // System.out.println(resultSet.getString(1)); tavsiye edilmez.
        }
        //ResulSet'in  first() last(), next() gibi metodlari var.
        //ResultSet te geriye donus yok.

        //ÖRNEK 2: phone_code'u 200 den büyük olan ülkelerin
        // "phone_code" ve "country_name" bilgisini listeleyiniz.
        String query2 = ("select phone_code,country_name from countries where phone_code>600");
        ResultSet rs =st.executeQuery(query2);
        System.out.println("Ornek2");
        while(rs.next()){
            System.out.println(rs.getInt("phone_code") +"   "+rs.getString("country_name"));
        }

        //ÖRNEK 3:developers tablosunda "salary"
        // değeri en düşük olan developerların tüm bilgilerini gösteriniz.

        String query3 = "select * from developers where salary=(select min(salary) from developers)";
        ResultSet rs1 = st.executeQuery(query3);
        while(rs1.next()) {
            System.out.println(rs1.getInt("id")+"  " + rs1.getString("name")+"   " + rs1.getDouble("salary")+"   " + rs1.getString("prog_lang"));
        }

        System.out.println("Ornek 4");
        //ÖRNEK 4:Puanı taban puanlarının ortalamasından
        // yüksek olan öğrencilerin isim ve puanlarını listeleyiniz.

        String query4 = "select isim,puan from ogrenciler where puan>(select avg(taban_puanı) from bolumler)";

       ResultSet rs4 = st.executeQuery(query4);
       while(rs4.next()){
           System.out.println(rs4.getString("isim") + "   " + rs4.getInt("puan"));
       }













    }
}
