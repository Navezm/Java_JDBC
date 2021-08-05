package be.digitalcity.formation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Student {

    private long id;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private String login;
    private long sectionId;
    private int yearResult;
    private String courseId;
    private String locality;

    public Student(){
    }

    public Student(long id, String firstName, String lastName, LocalDateTime birthDate, String login, long sectionId, int yearResult, String courseId, String locality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.login = login;
        this.sectionId = sectionId;
        this.yearResult = yearResult;
        this.courseId = courseId;
        this.locality = locality;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public int getYearResult() {
        return yearResult;
    }

    public void setYearResult(int yearResult) {
        this.yearResult = yearResult;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", login='").append(login).append('\'');
        sb.append(", sectionId=").append(sectionId);
        sb.append(", yearResult=").append(yearResult);
        sb.append(", courseId='").append(courseId).append('\'');
        sb.append(", locality='").append(locality).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
