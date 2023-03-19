package StudentSql.StudentManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//db ile iletisimde olan sinif
public class StudentRepository {

    private Connection con;
    private Statement st;
    private PreparedStatement prsd;

    //Connection icin bir method olusturalim
    //ve her baglandigimizda burda kullanalim.

    private void getConnection(){
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //Statement olusturmak icin method yapalim
    private void getStatement(){
        try {
            this.st = con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getPreparedStatement(String query){
        try {
            this.prsd = con.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //Kayitlarimiz kaydetmek icin bir tablo ihtiyaci var
    //8-tablo oluşturma
    public void createTable(){
        //constrain eklenmeli
        getConnection();
        getStatement();
        try {
            st.execute("CREATE TABLE IF NOT EXISTS t_student(id SERIAL,name VARCHAR(50),lastname VARCHAR(50),city VARCHAR(20),age INT)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void save(Student student){
        getConnection();
        String sql = "Insert into t_student(name,lastname,city,age) values(?,?,?,?)";
        getPreparedStatement(sql);
        try {
            prsd.setString(1,student.getName());
            prsd.setString(2,student.getLastName());
            prsd.setString(3,student.getCity());
            prsd.setInt(4,student.getAge());
            prsd.executeUpdate();
            System.out.println("Kayit basariyla gerceklesti.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                prsd.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    //Tablodaki tum kayitlari goruntuleme
    public void findAll() {
        getConnection();
        getStatement();
        String sql = "Select * from t_student";
        try {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                //List olustur her gelen degeri student objesine at ve list e at.
                System.out.println(rs.getInt("id")+"  "+
                        rs.getString("name")+"  "+
                        rs.getString("lastname") + "  "+
                        rs.getString("city")+"  "+
                        rs.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            try {
                st.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }


    public void delete(int id) {
        getConnection();
        String sql = "delete from t_student where id = ?";
        getPreparedStatement(sql);
        try {
            prsd.setInt(1,id);
            int deleted = prsd.executeUpdate();
            if(deleted>0){
                System.out.println("id "+id + " olan kayit  erfolgreich delete stattfunden");
            }else{
                System.out.println("Bulunamadi");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
                prsd.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Student findStudentById(int id) {
        Student student = null;
        getConnection();
//        String sql = "Select * from student where id ="+id;
//        getStatement();
//        st.executeQuery(sql);
        String sql = "Select * from t_student where id =?";
        try {
            getPreparedStatement(sql);
            prsd.setInt(1,id);
            ResultSet rs = prsd.executeQuery();
            if(rs.next()){
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setLastName(rs.getString("lastname"));
                student.setCity(rs.getString("city"));
                student.setAge(rs.getInt("age"));
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{

            try {
                con.close();
                prsd.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


        return student;
    }

    public void update(Student student) {
        getConnection();
        //20-veri güncelleme
        String sql="UPDATE t_student SET name=?,lastname=?,city=?,age=? WHERE id=?";
        getPreparedStatement(sql);
            try {
                prsd.setString(1,student.getName());
                prsd.setString(2,student.getLastName());
                prsd.setString(3,student.getCity());
                prsd.setInt(4,student.getAge());
                prsd.setInt(5,student.getId());
                int updated=prsd.executeUpdate();
                if(updated>0){
                    System.out.println("Öğrenci başarıyla güncellendi.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }finally {
                try {
                    prsd.close();
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }


        }


    public List<Student> findStudentByNameOrLastName(String nameLastName) {
        List<Student> list = new ArrayList<>();
        getConnection();
        String searched = "%"+nameLastName+"%";
        String sql = "Select * from t_student where name ilike ? or lastName ilike ?";
        getPreparedStatement(sql);

        try {
            prsd.setString(1,searched);
            prsd.setString(2,searched);
            ResultSet rs = prsd.executeQuery();
            while(rs.next()){
                Student st = new Student();
                st.setId(rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setLastName(rs.getString("lastName"));
                st.setCity(rs.getString("city"));
                st.setAge(rs.getInt("age"));
                list.add(st);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {

            try {
                con.close();
                prsd.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}















