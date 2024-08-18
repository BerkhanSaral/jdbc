package jdbc_day02;

import java.sql.*;

public class Transaction01 {
    public static void main(String[] args) throws SQLException {
        //!!! 2.ADIM : Hangi DB , Hangi kullanici ve sifre
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/B254JDBC_nt","B254nt","password");

        //!!! 3.ADIM: statement olusturma, yazacagimiz query'leri statement nesnesi ile DB ye gonderecegiz
        Statement st=con.createStatement();
        System.out.println("Success");

        st.execute("Create table if not exists hesaplar(hesap_no int unique,isim varchar(50),bakiye real)");

        String sql1="Insert into hesaplar values(?,?,?)";
        PreparedStatement prst1=con.prepareStatement(sql1);
        // prst1.setInt(1,1234);
        // prst1.setString(2,"Nihat");
        // prst1.setDouble(3,9000);
        // prst1.executeUpdate();

        // prst1.setString(2,"Ali");
        // prst1.setInt(1,5678);
        // prst1.setDouble(3,6000);
        // prst1.executeUpdate();

        //!!! TASK : hesap no:1234 den hesap no:5678 e 1000 para tranferi yapilsin
        String sql2="Update hesaplar set bakiye=bakiye+? where hesap_no=?";
        PreparedStatement prst2=con.prepareStatement(sql2);
        //1.adim : hesap no 1234 'un bakiyesini guncelleme
        prst2.setInt(1,-1000);
        prst2.setInt(2,1234);
        prst2.executeUpdate();

        //2.adim : hesap no 5678 'in bakiyesini guncelleme
        prst2.setInt(1,1000);
        prst2.setInt(2,5678);
        prst2.executeUpdate();

        st.close();
        con.close();





    }
}