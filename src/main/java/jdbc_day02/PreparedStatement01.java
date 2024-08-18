package jdbc_day02;

import java.sql.*;
import java.util.Scanner;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {

        //!!! 2.ADIM : Hangi DB , Hangi kullanici ve sifre
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/B254JDBC_nt","B254nt","password");

        //!!! 3.ADIM: statement olusturma, yazacagimiz query'leri statement nesnesi ile DB ye gonderecegiz
        Statement st=con.createStatement();
        System.out.println("Success");

        //!!!   ÖRNEK1: bolumler tablosunda bilgisayar muhendisligi bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
        String sql1="Update bolumler set taban_puani=475 where bolum='Bilgisayar Mühendisliği'";
        int updated=st.executeUpdate(sql1);
        System.out.println("updated = " + updated);//1

        // parametreli sorgu ile yazalim
        //!!!   ÖRNEK2: bolumler tablosunda Bilgisayar Mühendisliği bölümünün taban_puanı'nı 499 olarak güncelleyiniz.
        String sql2="Update bolumler set taban_puani=? where bolum ilike ?";//? bizim icin parametre anlamına geliyor!!!
        PreparedStatement prst1=con.prepareStatement(sql2);
        prst1.setInt(1,499);
        prst1.setString(2,"bilgisayar mühendisliği");

        //String sql2="Update bolumler set taban_puani=499 where bolum ilike 'Bilgisayar Mühendisliği'";
        int updated2=prst1.executeUpdate();
        System.out.println("updated2 = " + updated2);

        //!!!   Örnek3: Prepared Statement kullanarak bolumler tablosunda Mimarlık bölümünün taban_puanı'nı
        // 477 olarak güncelleyiniz.
        //Scanner input=new Scanner(System.in);
        //System.out.println("lutfen taban puanini belirlemek icin bir int deger giriniz");
        //int sayi= input.nextInt();
        prst1.setInt(1,477);
        prst1.setString(2,"Mimarlık");
        // int updated3=;
        System.out.println("updated3 = " +prst1.executeUpdate());

        //!!!   Örnek 4:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=5006,taban_puanı=475) bölümünü ekleyiniz.

        String sql3="Insert Into bolumler Values(?,?,?)";
        PreparedStatement prst2=con.prepareStatement(sql3);
        prst2.setInt(1,5006);
        prst2.setString(2,"Yazılım Mühendisliği");
        prst2.setDouble(3,475);
        //while dongusune alıp kullanıcıdan bilgiler alarak istedigim kadar veri ekleyebilirim
        //int updated4=prst2.executeUpdate();
        //System.out.println("updated4 = " + updated4);

        prst1.close();
        prst2.close();
        st.close();
        con.close();


    }
}