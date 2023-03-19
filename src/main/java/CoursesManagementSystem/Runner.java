package CoursesManagementSystem;

import java.util.Scanner;

/*
Proje:Course Management System
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

    private static void start() {
        Scanner scan = new Scanner(System.in);
        TeilnehmerService service = new TeilnehmerService();
        service.createTable();
        int select;

        do{
            System.out.println("***************  Techproeducation Kursuna Hosgeldiniz  ***************");
            System.out.println("1-Ogrenci Kayit Islemi");
            System.out.println("2-Tum Ogrencileri Goruntuleme");
            System.out.println("3-Id ile Ogrenci Guncelleme");
            System.out.println("4-Id ile Ogrenci Silme ");
            System.out.println("5-Id ile Ogrenci Bulma");
            System.out.println("6-Ad veya Soyad ile Ogrenci Bulma");
            System.out.println("0-Cikis");
            System.out.println("Isleminizi Seciniz");
            select = scan.nextInt();
            scan.nextLine();
            switch(select){
                case 1:
                    service.anmelden();
                    break;
                case 2:
                    service.getAllTeilnehmer();
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    service.getTeilnehmerById(getId());
                    break;
                case 6:
                    break;
                case 0:
                    System.out.println("Iyi Gunler Dileriz....");
                    break;
                default:
                    System.out.println("Hatali Giris Yaptiniz Tekrar Seciniz");
                    break;

            }

        }while(select != 0);

    }
    private static int  getId(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Secmek istediginiz Ogrencinin Id sini girininz");
       int id = scan.nextInt();
       return  id;

    }

}
