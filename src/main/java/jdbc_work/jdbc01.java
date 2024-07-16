package jdbc_work;

import java.sql.*;

//-> "workersss" adında bir tablo oluşturup "worker_id INT, worker_name varchar(50), salary real" sütunlarını ekleyiniz.
//        -> PreparedStatement ile Verileri ekleyiniz
//INSERT INTO workersss VALUES(123, 'Ogretmen', 1000);
//INSERT INTO workersss VALUES(124, 'Ogrenci', 2000);
//INSERT INTO workersss VALUES(125, 'Mudur', 3000);

//  SORU-1: workersss tablosunda Ogrenci'nin maasini 500 olarak güncelleyiniz.
//  SORU-2: workersss tablosunda worker_id'si 123 olan Ogretmenin'nin ismini Edebiyat Ogretmeni olarak güncelleyiniz.
//  SORU-3: workersss tablosunda maasi ortalama maastan az olanlarin maaslarini 5000 yapiniz.
//ortalama Maas = (500+1000+3000)/3=1500 -> Ogrenci maasi degiserek 5000 olmali
//  SORU-4: workersss tablosunun ismini iscilerrr olarak güncelleyiniz.
//  SORU-5: iscilerrr tablosunda salary sütununun data tipini int olarak güncelleyiniz.
//  SORU-6: Ismi içinde 'm' olan (buyuk-kucuk harf farketmez) iscilerin isimlerini ve maaşlarını yazdiriniz.
//  SORU-7: Isminin ikinci harfi u olan iscilerin tum bilgilerini yazdirin
//  Baglantiyi kopariniz.

public class jdbc01 {
    public static void main(String[] args) throws SQLException {

        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/B254JDBC_nt","B254nt","password");
        Statement st=con.createStatement();
        String sql1 = "CREATE TABLE if not exists workersss(worker_id int, worker_name VARCHAR(50), salary REAL)";
        st.execute(sql1);
        String sql2 = "INSERT INTO workersss VALUES(?,?,?)";
        PreparedStatement prst = con.prepareStatement(sql2);
        prst.setInt(1,123);
        prst.setString(2,"Öğretmen");
        prst.setDouble(3,1000);
        //prst.executeUpdate();
        prst.setInt(1,124);
        prst.setString(2,"Öğrenci");
        prst.setDouble(3,2000);
        //prst.executeUpdate();
        prst.setInt(1,125);
        prst.setString(2,"Müdür");
        prst.setDouble(3,3000);
        //prst.executeUpdate();

        String sql3 = "UPDATE workersss set salary=? where worker_name=?";
        PreparedStatement prst2 = con.prepareStatement(sql3);
        prst2.setDouble(1,500);
        prst2.setString(2,"Öğrenci");
        //prst2.executeUpdate();

        //  SORU-2: workersss tablosunda worker_id'si 123 olan Ogretmenin'nin ismini Edebiyat Ogretmeni olarak güncelleyiniz.

        String sql5 = "UPDATE workersss set worker_name=? where worker_id=?";
        prst2 = con.prepareStatement(sql5);
        prst2.setString(1,"Edebiyat Öğretmeni");
        prst2.setInt(2,123);
        //prst2.executeUpdate();


        //  SORU-3: workersss tablosunda maasi ortalama maastan az olanlarin maaslarini 5000 yapiniz.

        String sql4 = "UPDATE workersss set salary= ? where salary < ?";
        String subQuery = "SELECT AVG(salary) FROM workersss";
        ResultSet rs = st.executeQuery(subQuery);
        double avg = 0;
        while (rs.next()) {
            avg = rs.getDouble(1);
        }
        prst2 = con.prepareStatement(sql4);
        prst2.setDouble(1,5000);
        prst2.setDouble(2,avg);
        //prst2.executeUpdate();

        //  SORU-4: workersss tablosunun ismini iscilerrr olarak güncelleyiniz.

        String sql6 = "ALTER TABLE workersss RENAME TO iscilerrr";
        //st.executeUpdate(sql6);

        //  SORU-5: iscilerrr tablosunda salary sütununun data tipini int olarak güncelleyiniz.
        String sql7 = "ALTER TABLE iscilerrr ALTER COLUMN salary type int";
        //st.executeUpdate(sql7);

        //  SORU-6: Ismi içinde 'm' olan (buyuk-kucuk harf farketmez) iscilerin isimlerini ve maaşlarını yazdiriniz.
        String sql8 = "SELECT worker_name, salary FROM iscilerrr WHERE worker_name ilike '%m%'";
        ResultSet rs2 = st.executeQuery(sql8);
        while (rs2.next()) {
            System.out.println("worker_name: "+rs2.getString("worker_name")+ " salary: "+rs2.getInt("salary"));
        }

        //  SORU-7: Isminin ikinci harfi u olan iscilerin tum bilgilerini yazdirin
        String sql9 = "SELECT * FROM iscilerrr WHERE worker_name like '_ü%'";
        ResultSet rs3 =st.executeQuery(sql9);
        while (rs3.next()) {
            System.out.println("worker_id: "+rs3.getInt("worker_id")+" salary: "+rs3.getInt("salary")+" worker_name: "+rs3.getString("worker_name"));
        }

        //  Baglantiyi kopariniz.

        st.close();
        con.close();





    }
}
