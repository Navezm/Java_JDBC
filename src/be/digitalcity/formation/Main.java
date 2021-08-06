package be.digitalcity.formation;

import be.digitalcity.formation.data_access.DAO;
import be.digitalcity.formation.data_access.SectionDAO;
import be.digitalcity.formation.data_access.StudentDAO;
import be.digitalcity.formation.model.Section;
import be.digitalcity.formation.model.Student;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        DAO<Section, Long> dao = SectionDAO.getInstance();
////        System.out.println(dao.insert(new Section(120, null, 1)));
//
//        System.out.println(dao.delete(120L));

        StudentDAO dao2 = StudentDAO.getInstance();

//        Student stud = new Student(101L,"Martin","Navez", LocalDateTime.now(),"navezm",1010,15,"0","Bx");

        System.out.println(dao2.getOneDTO(15L));

    }


}
