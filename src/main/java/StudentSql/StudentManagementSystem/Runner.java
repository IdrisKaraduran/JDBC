package StudentSql.StudentManagementSystem;

import java.sql.Connection;
import java.util.Scanner;

/*
Proje:Student Management System
     -1-Herhangi bir eğitim kurumu için öğrenci yönetim uygulaması geliştiriniz.
     -2-Kullanıcı
               -öğrenci kayıt
               -öğrenci veya öğrencileri görüntüleme
               -id ile öğrenci güncelleme
               -id ile öğrenci silme
               -ad soyad ile ogrenci filtreleme
       işlemlerini yapabilmeli.
     -3-öğrenci:id,name,lastname,city,age özelliklerine sahiptir.
 */
public class Runner {
    public static void main(String[] args) {
        start();
    }

    //1-Uygulama icin yapilacak islemleri gosteren menu
    //2-student nesnesine ihtiyacuimiz var bundan dolayi student clasi olustur
    public static void start() {
        Scanner scan = new Scanner(System.in);
        StudentService service = new StudentService();
        //uygulama calistirildiginda tablo olussun
        service.createTable1();

        int select;
        do{
            System.out.println("********************************************");
            System.out.println("Ogrenci Yonetim Paneli");
            System.out.println("1-Ogrenci Kayit");
            System.out.println("2-Tum Ogrencileri Listele");
            System.out.println("3-Ogrenci Guncelleme");
            System.out.println("4-Ogrenci Sil");
            System.out.println("5-Ogrenci Bulma");
            System.out.println("6-Ad VEYA Soyad ile ogrenci filtrele");
            System.out.println("0-CIKIS");
            System.out.println("Islem Seciniz");
            select = scan.nextInt();
            scan.nextLine();
            int id;
            switch(select){
                case 1:
                    service.saveStudent();
                    break;
                case 2:
                    service.getAllStudent();
                    break;
                case 3:
                    id = getId(scan);
                    //guncelleme
                    break;
                case 4:
                    id = getId(scan);
                    service.deleteStudent(id);
                    //ogrenci silme
                    break;
                case 5:
                    id = getId(scan);
                    Student student = service.getStudentId(id);
                    if(student == null){
                        System.out.println("Ogrenci Bulunamadi");
                    }else{
                        System.out.println(student);
                    }

                    //spesifik ogrenciyi bulma
                    break;
                case 6:
                   service.listStudentByNameOrLastName();
                    break;
                case 0:
                    System.out.println("Iyi gunler dileriz");
                    break;
                default:
                    System.out.println("Hatali giris");
                    break;
            }
        }while(select != 0);
    }

    private static int getId(Scanner scan){
        System.out.println("Ogrenci id giriniz");
        int id = scan.nextInt();
        scan.nextLine();
        return id;
    }







}



























