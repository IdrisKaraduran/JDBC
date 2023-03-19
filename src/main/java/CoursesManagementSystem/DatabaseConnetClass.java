package CoursesManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnetClass {

    private Connection con;
    private Statement st;
    private PreparedStatement prst;


    public void getConnection(){
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaCourses","dev_user","password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void getStatement(){
        try {
            this.st = this.con.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void getPreparedStatement(String sql){
        try {
            this.prst=this.con.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable(){
        getConnection();
        getStatement();
        String sql = "Create table if not exists courseTable (id serial unique, vorname varchar(20),nachname varchar(30)," +
                "stadt varchar(30),alt int ,email varchar(50))";
        try {
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }


    }
    public void anmelden(Teilnehmer teilnehmer) {
        getConnection();

        String sql = "insert into courseTable(vorname,nachname,stadt,alt,email) values(?,?,?,?,?)";
        getPreparedStatement(sql);
        try {
        prst.setString(1,teilnehmer.getVorname());
        prst.setString(2,teilnehmer.getNachname());
        prst.setString(3,teilnehmer.getStadt());
        prst.setInt(4,teilnehmer.getAlt());
        prst.setString(5,teilnehmer.getEmail());
        int eklenen = prst.executeUpdate();
        if(eklenen >0){
            System.out.println("Basariyla kaydiniz alindi");
        }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            try {
                con.close();
                prst.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public List<Teilnehmer> getAllTeilnehmer() {
        getConnection();
        getStatement();
        List<Teilnehmer> list1 = new ArrayList<>();
        try {
        String sql = "Select * from courseTable";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            Teilnehmer t = new Teilnehmer();
            t.setId(rs.getInt("id"));
            t.setVorname(rs.getString("vorname"));
            t.setNachname(rs.getString("nachname"));
            t.setStadt(rs.getString("stadt"));
            t.setAlt(rs.getInt("alt"));
            t.setEmail(rs.getString("email"));
            list1.add(t);
        }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list1;
    }

    public Teilnehmer getTeilnehmerById(int id) {

        getConnection();
        getStatement();
        String sql = "Select * from courseTable where id ="+id;
        Teilnehmer tl = new Teilnehmer();
        try {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){


                tl.setId(rs.getInt("id"));
                tl.setVorname(rs.getString("vorname"));
                tl.setNachname(rs.getString("nachname"));
                tl.setStadt(rs.getString("stadt"));
                tl.setAlt(rs.getInt("alt"));
                tl.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        if(tl.getVorname() != null){
            return tl;
        }else{
            return null;
        }


    }


    public void updateById(int id) {
        getConnection();
        getStatement();
        String sql = "Select * from courseTable where id="+id;
        try {
            ResultSet rs = st.executeQuery(sql);
           if(rs.getInt("id") >0){
               String sql1 = "update courseTable set(vorname =?,nachname,stadt,alt,email) where id ="+id;

           }else{
               System.out.println("Bu id ye sahip kimse bulunamadi");
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
















}
