package be.digitalcity.formation.data_access;

import be.digitalcity.formation.ConnectionFactory;
import be.digitalcity.formation.model.Section;
import be.digitalcity.formation.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAO<Student, Long>{

//    Singleton
    private static StudentDAO instance;

    public static StudentDAO getInstance(){
        return instance == null ? instance = new StudentDAO() : instance;
    }

    private StudentDAO(){}
//    Fin Singleton

    @Override
    public boolean insert(Student toInsert) {

        return false;
    }

    @Override
    public Student getOne(Long id) {
        Student s = null;

        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE student_id = " + id);
        ) {

            if(rs.next()) {
                s = extract(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SQL Error : " + e.getMessage());
        }

        return s;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM student");
        ) { // La connexion sera auto fermée si on met ça dans le try()

            while(rs.next()) {
                Student s = extract(rs);
                students.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SQL Error : " + e.getMessage());
        }
        return students;
    }

    @Override
    public boolean update(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("student shouldn't be null");
        }

        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
        ) {

            return 0 < stmt.executeUpdate("UPDATE student " +
                    " SET first_name="+ student.getFirstName() +", last_name="+ student.getLastName() + ", birth_date=" + student.getBirthDate() +
                    " WHERE section_id = "+ student.getId());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
        ) {

            return 0 < stmt.executeUpdate("DELETE FROM student WHERE student_id = " + id);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Student extract(ResultSet rs) throws SQLException {
        return new Student(rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getTimestamp(4) == null ? null : rs.getTimestamp(4).toLocalDateTime(),
                rs.getString(5),
                rs.getLong(6),
                rs.getInt(7),
                rs.getString(8),
                rs.getString(9));
    }
}
