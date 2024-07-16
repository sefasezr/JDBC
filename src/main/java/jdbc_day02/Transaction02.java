package jdbc_day02;

import java.sql.*;

public class Transaction02 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/B254JDBC_nt","B254nt","password");

        Statement st = con.createStatement();
        System.out.println("Success");

        st.execute("CREATE TABLE IF NOT EXISTS hesaplar2(hesap_no INT UNIQUE, isim VARCHAR(50), bakiye REAL)");

        String sql1 = "INSERT INTO hesaplar2 VALUES(?,?,?)";
        java.sql.PreparedStatement prst1 = con.prepareStatement(sql1);

//        prst1.setInt(1,1234);
//        prst1.setString(2,"Ahmet");
//        prst1.setDouble(3,9000);
//        prst1.executeUpdate();
//
//        prst1.setInt(1,5678);
//        prst1.setString(2,"Emre");
//        prst1.setDouble(3,6000);
//        prst1.executeUpdate();

        //!!! TASK : hesap no:1234 den hesap no:5678 e 1000 para tranferi yapilsin
        try{
            con.setAutoCommit(false); //transaction yonetimi artık temelli bizde
            String sql2="Update hesaplar2 set bakiye=bakiye+? where hesap_no=?";
            PreparedStatement prst2= con.prepareStatement(sql2);
            //1.adim : hesap no 1234 un bakiye guncellemesi
            prst2.setInt(1,-1000);
            prst2.setInt(2,1234);
            prst2.executeUpdate(); //otomatik calismasini engeller bu satir calismiyor ne zamana kadar con.commit methodunu cagirana kadar
            //sistemde bir hata olustu
//            if(true){
//                throw new RuntimeException();
//            }
            //2.adim : hesap no 5678 un bakiye guncellemesi
            prst2.setInt(1,1000);
            prst2.setInt(2,5678);
            prst2.executeUpdate(); //otomatik calismasini engeller

            con.commit();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Sistemde bir hata olustu");
        }finally {
            st.close();
            con.close();
        }

    }
}
