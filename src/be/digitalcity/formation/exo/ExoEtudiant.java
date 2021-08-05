package be.digitalcity.formation.exo;

import be.digitalcity.formation.ConnectionFactory;
import be.digitalcity.formation.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

public class ExoEtudiant {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Donne moi l'id de l'étudiant dont tu souhaite les informations");
        long id = scan.nextLong();

        try (Connection co = ConnectionFactory.getConnection()) {

            Statement stmt = co.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE student_id = "+id);

            if (rs.next()) {
                Student s = new Student(rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getTimestamp(4) == null ? null : rs.getTimestamp(4).toLocalDateTime(),
                        rs.getString(5),
                        rs.getLong(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9));
                System.out.println(s);

            } else {
                System.out.println("pas d'étudiant avec cet ID");
            }


        } catch (SQLException throwables) {
            System.out.println("Error" + throwables.getMessage());
        }

    }

}
