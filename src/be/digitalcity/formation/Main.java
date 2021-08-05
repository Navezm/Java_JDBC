package be.digitalcity.formation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/DBSlide";
        String username = "postgres";
        String password = "123456";

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

        System.out.println("Trying connection");
        try (Connection co = DriverManager.getConnection(url, username, password);) { // La connexion sera auto fermée
            System.out.println("Connection gotten");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
