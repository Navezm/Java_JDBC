package be.digitalcity.formation.data_access;

import be.digitalcity.formation.ConnectionFactory;
import be.digitalcity.formation.model.Section;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SectionDAO implements DAO<Section, Long> {


    @Override
    public boolean insert(Section toInsert) {
        return false;
    }

    @Override
    public Section getOne(Long aLong) {
        return null;
    }

    @Override
    public List<Section> getAll() {
        List<Section> sections = new ArrayList<>();
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

                sections.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SQL Error : " + e.getMessage());
        }
        return sections;
    }

    @Override
    public boolean update(Section element) {
        return false;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }
}
