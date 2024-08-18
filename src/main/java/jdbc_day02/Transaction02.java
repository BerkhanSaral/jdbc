package jdbc_day02;

import java.sql.*;

public class Transaction02 {
    public static void main(String[] args) throws SQLException {
        //!!! 2.ADIM : Hangi DB , Hangi kullanici ve sifre
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/B254JDBC_nt","B254nt","password");

        //!!! 3.ADIM: statement olusturma, yazacagimiz query'leri statement nesnesi ile DB ye gonderecegiz
        Statement st=con.createStatement();
        System.out.println("Success");


        st.execute("CREATE TABLE IF NOT EXISTS hesaplar2(hesap_no INT UNIQUE, isim VARCHAR(50), bakiye REAL)");

        String sql1 = "INSERT INTO hesaplar2 VALUES(?,?,?)";
        java.sql.PreparedStatement prst1 = con.prepareStatement(sql1);

        //prst1.setInt(1,1234);
        //prst1.setString(2,"Ahmet");
        //prst1.setDouble(3,9000);
        //prst1.executeUpdate();
//
        //prst1.setInt(1,5678);
        //prst1.setString(2,"Emre");
        //prst1.setDouble(3,6000);
        //prst1.executeUpdate();

        try {
            con.setAutoCommit(false);//defualt olarak true
            //!!! TASK : hesap no:1234 den hesap no:5678 e 1000 para tranferi yapilsin
            String sql2="Update hesaplar2 set bakiye=bakiye+? where hesap_no=?";
            PreparedStatement prst2=con.prepareStatement(sql2);
            //1.adim : hesap no 1234 'un bakiyesini guncelleme
            prst2.setInt(1,-1000);
            prst2.setInt(2,1234);
            prst2.executeUpdate();//otomatik olarak calistirir// bu yapılar artık otomatik olarak calismaz calismak icin commit methodunu bekler
            //eger transfer sirasinda bir exception alırsak
            //if (true){
            //    throw new RuntimeException();
            //}

            //2.adim : hesap no 5678 'in bakiyesini guncelleme
            prst2.setInt(1,1000);
            prst2.setInt(2,5678);
            prst2.executeUpdate();

            con.commit();//buda execute yapılarının tamamının aynı anda calismasini saglar
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("sistemde hata var!!!");
        } finally {
            st.close();
            con.close();
        }
        System.out.println("sistem calismaya devam ediyor mu?");

    }
}