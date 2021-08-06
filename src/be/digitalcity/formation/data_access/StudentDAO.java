package be.digitalcity.formation.data_access;

import be.digitalcity.formation.ConnectionFactory;
import be.digitalcity.formation.mapper.Mapper;
import be.digitalcity.formation.model.Section;
import be.digitalcity.formation.model.Student;
import be.digitalcity.formation.model.StudentDTO;

import java.sql.*;
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
        if(toInsert == null)
            throw new IllegalArgumentException("toInsert shouldn't be null");

        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
        ) {

            return 0 < stmt.executeUpdate("INSERT INTO student VALUES "+
                    "( "+ toInsert.getId() +", "+ toInsert.getFirstName() +", "+ toInsert.getLastName() + ", " + toInsert.getBirthDate() + ", " + toInsert.getLogin() +
                    ", " + toInsert.getSectionId() + ", " + toInsert.getYearResult() + ", " + toInsert.getCourseId() + ", " + toInsert.getLocality() + " )" );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
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
                s = Mapper.toStudentDTO(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SQL Error : " + e.getMessage());
        }

        return s;
    }

    public StudentDTO getOneDTO(Long id) {
        StudentDTO s = null;

        String requete = "SELECT student_id, first_name, last_name, birth_date, login, s.section_id, s.section_name, s.delegate_id, year_result, course_id, locality FROM student " +
                "JOIN section s on student.section_id = s.section_id " +
                "WHERE student_id = ?";

        try (
                Connection co = ConnectionFactory.getConnection();
        ) {
                PreparedStatement stmt = co.prepareStatement(requete);
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                s = Mapper.toStudentDTO2(rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
                Student s = Mapper.toStudentDTO(rs);
                students.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SQL Error : " + e.getMessage());
        }
        return students;
    }

    public List<StudentDTO> getAllDTO() {
        List<StudentDTO> students = new ArrayList<>();
        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT student_id, first_name, last_name, birth_date, login, s.section_id, s.section_name, s.delegate_id, year_result, course_id, locality FROM student " +
                                                "JOIN section s on student.section_id = s.section_id");
        ) { // La connexion sera auto fermée si on met ça dans le try()

            while(rs.next()) {
                StudentDTO s = Mapper.toStudentDTO2(rs);
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
                    " SET first_name='"+ student.getFirstName() + "'" +", last_name='" + student.getLastName() + "'" + ", birth_date='" + student.getBirthDate() + "'" +
                    ", login='" + student.getLogin() + "'" + ", section_id='" + student.getSectionId() + "'" + ", year_result='" + student.getYearResult() + "'" + ", course_id='" + student.getCourseId() + "'" +
                    " WHERE student_id = "+ student.getId());

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

    public List<Student> getStudentSup(int value){
        List<Student> students = new ArrayList<>();
        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE year_result > " + value);
        ) {

            while(rs.next()) {
                Student s = Mapper.toStudentDTO(rs);
                students.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SQL Error : " + e.getMessage());
        }
        return students;
    }

    public boolean deleteWithName(String name){
        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
        ) {

            return 0 < stmt.executeUpdate("DELETE FROM student WHERE first_name = " + "'" + name + "'");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
