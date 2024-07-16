package jdbc_day02;

import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/B254JDBC_nt","B254nt","password");
        Statement st = con.createStatement();
        System.out.println("success");

        // !!! update islemi oncesi kayitlarin isim ve maas bilgilerini ekrana basalim

        System.out.println("----------UPDATE ONCESI---------------");
        ResultSet rs = st.executeQuery("select name,salary from developers");
        while (rs.next()){
            System.out.printf("%-15s  %-10s\n","Name : "+rs.getString("name"),
                    "   salary : "+rs.getDouble("salary"));
        }

        //!!!  ÖRNEK1:developers tablosunda maaşı,
        // ortalama maaştan az olanların maaşını ortalama maaş
        // ile güncelleyiniz
        //subquery'i ortalama maas sorgusu
        //sub query : select avg(salary)from developers
        String sql1="update developers set salary=(select avg(salary)from developers) where salary<(select avg(salary)from developers)";
        int updated = st.executeUpdate(sql1);
        System.out.println("Guncellenen kayit sayisi : "+updated);

        // !!! update islemi sonrasi kayitlarin isim ve maas bilgilerini ekrana basalim

        System.out.println("----------------UPDATE SONRASI------------------");
        ResultSet rs2 = st.executeQuery("select name,salary from developers");
        while (rs2.next()){
            System.out.printf("%-15s  %-10s\n","Name : "+rs2.getString("name"),
                    "   salary : "+rs2.getDouble("salary"));
        }
        st.close();
        con.close();

//        executeQuery Metodu:
//        Kullanım Amacı: Bu metod genellikle veritabanından veri çekmek için kullanılır. SELECT sorguları için idealdir.
//        Dönen Sonuç: Bu metod, ResultSet nesnesi döner. ResultSet, veritabanından çekilen verileri içeren bir veri yapısıdır.
//
//        execute Metodu:
//        Kullanım Amacı: Bu metod hem veri çekmek (SELECT) hem de veri güncellemek (INSERT, UPDATE, DELETE, vb.) için kullanılabilir. Genel amaçlı bir metoddur.
//        Dönen Sonuç: Bu metod, boolean türünde bir değer döner. Eğer sorgu bir ResultSet döndürüyorsa (yani bir SELECT sorgusu ise) true, aksi takdirde false döner.


    }
}
