package be.digitalcity.formation;

import be.digitalcity.formation.model.Section;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {

//        String url = "jdbc:postgresql://localhost:5432/DBSlide";
//        String username = "postgres";
//        String password = "123456";
//
//        Connection co = null;
//        try () {
//            System.out.println("Trying connection");
//            co = DriverManager.getConnection(url, username, password);
//            System.out.println("Connection gotten");
//            co.close();
//            System.out.println("Connection closed");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try (Connection co = ConnectionFactory.getConnection()) { // La connexion sera auto fermée

            Statement stmt = co.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM section");

            while(rs.next()) {

                Section s = new Section();
//                s.setId(rs.getLong("section_id"));
                s.setId(rs.getLong(1));
//                s.setName(rs.getString("section_name"));
                s.setName(rs.getString(2));
//                s.setDelegateId(rs.getInt("delegate_id"));
                s.setDelegateId(rs.getInt(3));

                System.out.println(s.getId() + " " +
                        s.getName() + " " +
                        s.getDelegateId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
