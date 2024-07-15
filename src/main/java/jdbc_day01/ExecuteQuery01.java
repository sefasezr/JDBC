package jdbc_day01;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {
        //!!! 2.ADIM : Hangi DB , Hangi kullanici ve sifre
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/B254JDBC_nt","B254nt","password");

        //!!! 3.ADIM: statement olusturma, yazacagimiz query'leri
        // statement nesnesi ile DB ye gonderecegiz
        Statement st = con.createStatement();
        System.out.println("success");

        //!!! 4.ADIM: sorguları olusturma
        st.execute("CREATE TABLE if not exists countries\n" +
                "        ( id SERIAL PRIMARY KEY\n" +
                "         , country_name VARCHAR(100) );");
        /*st.execute("INSERT INTO countries\n" +
                "                (id,country_name) VALUES\n" +
                "                (1,'Turkey'), (2,'Germany'), (3,\n" +
                "                'France'),\n" +
                "        (4,'United States'),\n" +
                "        (5,'Canada'),\n" +
                "        (6,'Japan'),\n" +
                "        (7,'Brazil'),\n" +
                "        (8,'Australia'),\n" +
                "        (9,'India'),\n" +
                "        (10,'United Kingdom');"); */
        //!!!   ÖRNEK 1:id'si 5 ile 10 arasında olan ülkelerin "country_name" bilgisini listeleyiniz.

        String query1 = "SELECT country_name FROM countries WHERE id BETWEEN 5 AND 10 ";
        boolean sql1 = st.execute(query1);
        System.out.println("sql1 = "+sql1);

        // Verileri almak istersem :
        ResultSet rs = st.executeQuery(query1);
        while (rs.next()) {
            System.out.println("Ulke Adı : "+rs.getString("country_name"));
        }
    }
}
