package DersAnlatimi.JDBC;

import java.sql.*;

/*
Transaction ,database deki atomik parcalanamaz en kucuk islem
Birden fazla islem icin kullanici tarfindan transaction olustururlabilir.
Bu islemelerin tamami basarili bir sekilde gerceklesince transaction commit edilir.
Yani yapilan islemler kalici hale gelir.
Islemlerden en az birinde bir hata olursa rollback ile tum islemler iptal edilir.
 */
public class Transection {

    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        Statement st = con.createStatement();
        //hesapNo 1234 ten 5676 hesap noya 1000 lira transfer et.
        con.setAutoCommit(false);
        Savepoint sp = null;
        try {
            //insert
             sp = con.setSavepoint("x");

            String sql ="update hesaplar set bakiye = bakiye+? where hesap_no=?";
            PreparedStatement prst =con.prepareStatement(sql);
            prst.setDouble(1, -1000);
            prst.setInt(2, 1234);
            prst.executeUpdate();
            if (false) {
                throw new Exception();
            }
            prst.setDouble(1, 1000);
            prst.setInt(2, 5678);
            prst.executeUpdate();
            prst.close();
            con.commit();
            con.close();
        }catch(Exception e){
            con.rollback(sp);
           // con.rollback();
        }


    }



}
