package DersAnlatimi.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Deneme {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Adim
        Class.forName("org.postgresql.Driver");
        //2.Adim
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        //3.Adim
        Statement st = con.createStatement();
        System.out.println("Baglinti basarili");
        //4.Query calistirma
        //ÖRNEK 1:"workers" adında bir tablo oluşturup
        // "worker_id,worker_name,salary" sütunlarını ekleyiniz.

      // boolean sql1 = st.execute("CREATE TABLE workers(worker_id INT,worker_name VARCHAR(50),salary REAL)");
       // System.out.println(sql1);
        //execute metodu DQL veya
        // DDl icin kullanilabilir.
        // Geriye default deger olarak false doner
        //DQL icin kullanilirsa :ResultSet nesnesi alirsa true dondurur
        //aksi halde false dondurur.

        //ÖRNEK 2:"workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.
       // String query2 = ("Alter table workers add column city varchar(20)");
       // st.execute(query2);
        //ÖRNEK 3:"workers" tablosunu SCHEMAdan siliniz.
        String query3 = ("drop table workers");
        st.execute(query3);

        //statement kapatma icin
        st.close();
        con.close();




    }


}
