package be.digitalcity.formation.mapper;

import be.digitalcity.formation.model.Section;
import be.digitalcity.formation.model.Student;
import be.digitalcity.formation.model.StudentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper {

    public static Section toSectionDTO(ResultSet rs) throws SQLException {
        Section s = new Section();
//                s.setId(rs.getLong("section_id"));
        s.setId(rs.getLong(1));
//                s.setName(rs.getString("section_name"));
        s.setName(rs.getString(2));
//                s.setDelegateId(rs.getInt("delegate_id"));
        s.setDelegateId(rs.getInt(3));

        return s;
    }

    public static Student toStudentDTO(ResultSet rs) throws SQLException {
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

    public static StudentDTO toStudentDTO2(ResultSet rs) throws SQLException {
        return new StudentDTO(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getTimestamp(4) == null ? null : rs.getTimestamp(4).toLocalDateTime(),
                rs.getString(5),
                rs.getInt(6),
                rs.getString(7),
                rs.getInt(8),
                rs.getInt(9),
                rs.getString(10),
                rs.getString(11));
    }

}
