package DersAnlatimi.JDBC;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");

        Statement st = con.createStatement();


        //ÖRNEK1:bolumler tablosunda taban puanı
        // en yüksek 2. bölümün ismini ve puanını yazdırınız.

        String query1 = ("Select bolum,taban_puanı from bolumler order by taban_puanı desc offset 1 limit 1");
        ResultSet rs1 = st.executeQuery(query1);

        while(rs1.next()){
            System.out.println( rs1.getString("bolum") + "  " + rs1.getInt("taban_puanı"));
        }

        //offset ve limit kullanmadan
        String query11 = "Select bolum,taban_puanı from bolumler where taban_puanı ="+
                "(Select max(taban_puanı) from bolumler where taban_puanı<(select max(taban_puanı) from bolumler))";

        //ÖRNEK2:Bölüm isimlerini, kampüslerini ve
        //her bölümde okuyan öğrencilerin en yüksek puanlarını listeleyiniz.
        String query2 = "Select bolum,kampus ,(select max(puan) from ogrenciler  o where o.bolum = b.bolum) as maxpuan from bolumler b";
        ResultSet rs2 = st.executeQuery(query2);
        while(rs2.next()){
            System.out.println(rs2.getString("bolum") +" " +
                    rs2.getString("kampus") +" " + rs2.getInt("maxpuan"));
        }


        con.close();
        st.close();

    }



}