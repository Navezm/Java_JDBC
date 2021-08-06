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

public class SectionDAO implements DAO<Section, Long> {

//    Singleton

    private static SectionDAO instance;

    public static SectionDAO getInstance(){
        return instance == null ? instance = new SectionDAO() : instance;
    }

    private SectionDAO(){}

//    Fin Singleton

    @Override
    public boolean insert(Section toInsert) {

        if(toInsert == null)
            throw new IllegalArgumentException("toInsert shouldn't be null");

        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
        ) {

            return 0 < stmt.executeUpdate("INSERT INTO section VALUES "+
                    "( "+ toInsert.getId() +", "+ toInsert.getName() +", "+ toInsert.getDelegateId() + ")" );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Section getOne(Long id) {

        Section s = null;

        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM section WHERE section_id = " + id);
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
    public List<Section> getAll() {
        List<Section> sections = new ArrayList<>();
        try (
            Connection co = ConnectionFactory.getConnection();
            Statement stmt = co.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM section");
        ) { // La connexion sera auto fermée si on met ça dans le try()

            while(rs.next()) {
                Section s = extract(rs);
                sections.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException("SQL Error : " + e.getMessage());
        }
        return sections;
    }

    @Override
    public boolean update(Section section) {

        if (section == null) {
            throw new IllegalArgumentException("section shouldn't be null");
        }

        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
        ) {

            return 0 < stmt.executeUpdate("UPDATE section " +
                    " SET section_name='"+ section.getName() + "'" +", delegate_id="+ section.getDelegateId() +
                    " WHERE section_id = "+ section.getId());

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

            return 0 < stmt.executeUpdate("DELETE FROM section WHERE section_id = " + id);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    private Section extract(ResultSet rs) throws SQLException {
        Section s = new Section();
//                s.setId(rs.getLong("section_id"));
        s.setId(rs.getLong(1));
//                s.setName(rs.getString("section_name"));
        s.setName(rs.getString(2));
//                s.setDelegateId(rs.getInt("delegate_id"));
        s.setDelegateId(rs.getInt(3));

        return s;
    }

}
