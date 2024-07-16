package jdbc_day02;

import java.sql.*;
import java.util.Scanner;

public class Transaction01 {
    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/B254JDBC_nt","B254nt","password");

        Statement st = con.createStatement();
        System.out.println("Success");

        st.execute("create table if not exists hesaplar(hesap_no int unique ,isim VARCHAR(50), bakiye REAL)");

        String sql1 = "Insert Into hesaplar VALUES(?,?,?)";
        PreparedStatement prst1 = con.prepareStatement(sql1);
//        prst1.setInt(1, 1234);
//        prst1.setString(2, "Ali");
//        prst1.setDouble(3, 9000);
//        prst1.executeUpdate();
//        Scanner input = new Scanner(System.in);
//        System.out.println("Lutfen olusturmak istediginiz ismi giriniz");
//        String str = input.nextLine();
//
//        prst1.setInt(1,5679);
//        prst1.setString(2, str);
//        prst1.setDouble(3, 6000);
//        prst1.executeUpdate();
        //!!! TASK : hesap no:1234 den hesap no:5678 e 1000 para tranferi yapilsin

        String sql2 = "Update hesaplar set bakiye = bakiye+? where hesap_no = ?";
        PreparedStatement prst2 = con.prepareStatement(sql2);

        //1.adim : hesap no 1234 un bakiye guncellemesi
        prst2.setInt(1,-1000);
        prst2.setInt(2,1234);
        prst2.executeUpdate();


        //2.adim : hesap no 5678 un bakiye guncellemesi
        prst2.setInt(1,1000);
        prst2.setInt(2,5678);
        prst2.executeUpdate();

        st.close();
        con.close();





    }
}
