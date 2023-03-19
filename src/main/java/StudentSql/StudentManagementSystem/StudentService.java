package StudentSql.StudentManagementSystem;

import java.util.List;
import java.util.Scanner;

public class StudentService {
    Scanner scan = new Scanner(System.in);

    //Repository deki methodlara ulasmak ic
    StudentRepository repository = new StudentRepository();

    //Tabloyu olusturmak icin metodu yazalim
    public void createTable1(){
      repository.createTable();

    }

    public void saveStudent(){
        System.out.println("Ad: ");
        String name = scan.nextLine();
        System.out.println("Soyad: ");
        String lastName = scan.nextLine();
        System.out.println("Sehir");
        String city = scan.nextLine();
        System.out.println("Yasinizi giriniz");
        int age = scan.nextInt();
        scan.nextLine();
        Student newStudent = new Student(name,lastName,city,age);
        repository.save(newStudent);

    }
    //Tum ogrencileri listeleyen method
    public void getAllStudent(){
        repository.findAll();
    }

    public void deleteStudent(int id){
        repository.delete(id);
    }


    //Id ile ogrenci getirme
    public Student getStudentId(int id){
        Student student = repository.findStudentById(id);
        return student;

    }

    //ogrenci guncelleme
    public void updateStudent(int id){
        //Girmis oldugu id ile ogrenci var mi?
        Student student = getStudentId(id);
        if(student == null){
            System.out.println(id+ " id numarasina sahip ogrenci bulunamadi.");
        }else{
            System.out.println("Ad: ");
            String name = scan.nextLine();
            System.out.println("Soyad: ");
            String lastName = scan.nextLine();
            System.out.println("Sehir");
            String city = scan.nextLine();
            System.out.println("Yasinizi giriniz");
            int age = scan.nextInt();
            //yeni degerler ile field lari guncelle

            student.setName(name);
            student.setLastName(lastName);
            student.setCity(city);
            student.setAge(age);
            repository.update(student);


        }

    }


    //girilen ad veya soyad bilgisini iceren kayitlari listeleme
    //Kelime act -----> ad : soyad : react olani getirme
    public void listStudentByNameOrLastName(){

        System.out.println("Ad veya Soyad : ");
        String nameLastName= scan.nextLine();
        //birden fazla kayit donebilir.
        List<Student> studentList = repository.findStudentByNameOrLastName(nameLastName);
        //Bu listedeki ogrencileri yazdiralim
        if(studentList.size()==0){
            System.out.println("Ogrenci bulunamadi");
        }else{
            studentList.forEach(System.out::println);
        }

    }





















}
