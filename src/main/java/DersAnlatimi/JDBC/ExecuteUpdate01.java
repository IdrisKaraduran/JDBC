package DersAnlatimi.JDBC;

import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        Statement st = con.createStatement();

        //ÖRNEK1:developers tablosunda maaşı ortalama maaştan
        // az olanların maaşını ortalama maaş ile güncelleyiniz
        String sql1= "update developers set salary=(select avg(salary) from developers)"+
                "where salary<(select avg(salary) from developers)";
        int updatet = st.executeUpdate(sql1);
        System.out.println("Guncelenen:"+updatet);

       ResultSet rs1= st.executeQuery("select id,name,salary from developers");
       while(rs1.next()){
           System.out.println(rs1.getInt("id") +" " + rs1.getString("name")+ " " +rs1.getDouble("salary"));
       }
        //ÖRNEK2:developers tablosuna yeni bir developer ekleyiniz.
       // String sql2 ="insert into developers(name,salary,prog_lang) values('Ilker','5300','React')";
      //  int insert = st.executeUpdate(sql2);
       // System.out.println("eklenen"+insert);

        //ÖRNEK3:developers tablosundan id'si 14 olanı siliniz.
       // String sql3 = "delete from developers where id = 14";
       // int silinen = st.executeUpdate(sql3);
       // System.out.println("Silenen " + silinen);

        //ÖRNEK4:developers tablosundan prog_lang Css olanları siliniz.
         String sql4 = "delete from developers where prog_lang ilike 'Css'";
         int silinen1= st.executeUpdate(sql4);
         System.out.println("Silenen " + silinen1);
         st.close();
         con.close();;


    }
}
