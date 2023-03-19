package CoursesManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeilnehmerService {
    Scanner scan = new Scanner(System.in);
    DatabaseConnetClass database = new DatabaseConnetClass();
    public void createTable(){
        database.createTable();
    }
    public void anmelden(){
        System.out.println("Ad : ");
        String vorname = scan.nextLine();
        System.out.println("Soyad : " );
        String nachname = scan.nextLine();
        System.out.println("Stadt : ");
        String stadt = scan.nextLine();
        System.out.println("Email : ");
        String email = scan.nextLine();
        System.out.println("Yas : ");
        int alt = scan.nextInt();
        scan.nextLine();
        Teilnehmer teilnehmer = new Teilnehmer(vorname,nachname,stadt,alt,email);
        database.anmelden(teilnehmer);

    }
    public void getAllTeilnehmer(){
        List<Teilnehmer> list = new ArrayList<>();
        list = database.getAllTeilnehmer();

        for(Teilnehmer w:list){
            System.out.println(w);
        }
    }
    public void getTeilnehmerById(int id){
        Teilnehmer tl = new Teilnehmer();
         if(database.getTeilnehmerById(id) != null){
             tl = database.getTeilnehmerById(id);
             System.out.println(tl);
         }
         else{
             System.out.println("Ogrenci Bulunamadi");
         }

    }
    public  Teilnehmer updateById(int id){
        Teilnehmer t = new Teilnehmer();
        database.updateById(id);
        return t;
    }




}
