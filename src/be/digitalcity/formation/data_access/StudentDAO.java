package be.digitalcity.formation.data_access;

import be.digitalcity.formation.ConnectionFactory;
import be.digitalcity.formation.mapper.Mapper;
import be.digitalcity.formation.model.Section;
import be.digitalcity.formation.model.Student;
import be.digitalcity.formation.model.StudentDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAO<StudentDTO, Long>{

//    Singleton
    private static StudentDAO instance;
    public static StudentDAO getInstance(){
        return instance == null ? instance = new StudentDAO() : instance;
    }
    private StudentDAO(){}
//    Fin Singleton

    @Override
    public boolean insert(StudentDTO toInsert) {
        if(toInsert == null)
            throw new IllegalArgumentException("toInsert shouldn't be null");

        try (
                Connection co = ConnectionFactory.getConnection();
        ) {
            CallableStatement stmt = co.prepareCall("call insert(?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, (int) toInsert.getId());
            stmt.setString(2, toInsert.getFirstName());
            stmt.setString(3, toInsert.getLastName());
            stmt.setTimestamp(4, Timestamp.valueOf(toInsert.getBirthDate()));
            stmt.setString(5, toInsert.getLogin());
            stmt.setInt(6, (int)toInsert.getSection().getId());
            stmt.setInt(7, toInsert.getYearResult());
            stmt.setString(8, toInsert.getCourseId());
            stmt.setString(9, toInsert.getLocality());

            return 0 < stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public StudentDTO getOne(Long id) {
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
    public List<StudentDTO> getAll() {
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
    public boolean update(StudentDTO student) {
        if (student == null) {
            throw new IllegalArgumentException("student shouldn't be null");
        }

        String requete = "UPDATE student SET first_name = ?, last_name = ?, birth_date = ?, login = ?, section_id = ?, year_result = ?, course_id = ?, locality = ? WHERE student_id = ?";

        try (
                Connection co = ConnectionFactory.getConnection();
        ) {
            PreparedStatement stmt = co.prepareStatement(requete);
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setTimestamp(3, Timestamp.valueOf(student.getBirthDate()));
            stmt.setString(4, student.getLogin());
            stmt.setInt(5, (int)student.getSection().getId());
            stmt.setInt(6, student.getYearResult());
            stmt.setString(7, student.getCourseId());
            stmt.setString(8, student.getLocality());
            stmt.setLong(9, student.getId());

            return 0 < stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {

//        String requete = "DELETE FROM student WHERE student_id = ?";

        try (
                Connection co = ConnectionFactory.getConnection();
        ) {
            CallableStatement stmt = co.prepareCall("call delete(?)");
            stmt.setInt(1, Math.toIntExact(id));

            return 0 < stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<StudentDTO> getStudentSup(int value){
        List<StudentDTO> students = new ArrayList<>();

        String requete = "SELECT * FROM student WHERE year_result > ?";

        try (
                Connection co = ConnectionFactory.getConnection();
        ) {
            PreparedStatement stmt = co.prepareStatement(requete);
            stmt.setInt(1, value);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                StudentDTO s = Mapper.toStudentDTO2(rs);
                students.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SQL Error : " + e.getMessage());
        }
        return students;
    }

    public boolean deleteWithName(String name){

        String requete = "DELETE FROM student WHERE first_name = ?";

        try (
                Connection co = ConnectionFactory.getConnection();
        ) {
            PreparedStatement stmt = co.prepareStatement(requete);
            stmt.setString(1, name);

            return 0 < stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
