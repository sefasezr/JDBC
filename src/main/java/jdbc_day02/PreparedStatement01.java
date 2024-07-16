package jdbc_day02;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        //!!! 2.ADIM : Hangi DB , Hangi kullanici ve sifre
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/B254JDBC_nt","B254nt","password");

        //!!! 3.ADIM: statement olusturma, yazacagimiz query'leri
        //statement nesnesi ile DB ye gonderecegiz
        Statement st=con.createStatement();
        System.out.println("success");

        //!!!   ÖRNEK1: bolumler tablosunda bilgisayar muhendisligi bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
        String sql1 = "UPDATE bolumler set taban_puani= 475 where bolum = 'Bilgisayar Mühendisliği'";
        int updated = st.executeUpdate(sql1);
        System.out.println("updated = " + updated);

        // parametreli sorgu ile yazalim
        //!!!   ÖRNEK2: bolumler tablosunda Bilgisayar Mühendisliği bölümünün taban_puanı'nı 499 olarak güncelleyiniz.
        String sql2 = "UPDATE bolumler set taban_puani=? where bolum ilike ?";
        PreparedStatement prst =  con.prepareStatement(sql2);
        prst.setInt(1,499);
        prst.setString(2,"Bilgisayar Mühendisliği");
        int updated2 = prst.executeUpdate();
        System.out.println("updated2 = " + updated2);

        //!!!   Örnek3: Prepared Statement kullanarak bolumler tablosunda Mimarlik bölümünün taban_puanı'nı
        // 477 olarak güncelleyiniz.
        prst.setInt(1,477);
        prst.setString(2,"Mimarlık");
        int updated3 = prst.executeUpdate();
        System.out.println("updated3 = " + updated3);

        //!!!   Örnek 4:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=11,taban_puanı=475,) bölümünü ekleyiniz.

        String sql3="Insert Into bolumler VALUES(?,?,?)";
        PreparedStatement prst2 = con.prepareStatement(sql3);
        prst2.setInt(1,11);
        prst2.setString(2,"Yazılım Mühendisliği");
        prst2.setDouble(3,475.0);

        int updated4 = prst2.executeUpdate();
        System.out.println("updated4 = " + updated4);
        st.close();
        con.close();

    }
}
