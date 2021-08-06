package be.digitalcity.formation.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class StudentDTO {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private String login;
    private Section section;
    private int yearResult;
    private String courseId;
    private String locality;

    public StudentDTO(long id, String firstName, String lastName, LocalDateTime birthDate, String login, Section section, int yearResult, String courseId, String locality) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.login = login;
        this.section = section;
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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
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
        final StringBuilder sb = new StringBuilder("StudentDTO{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", login='").append(login).append('\'');
        sb.append(", section=").append(section);
        sb.append(", yearResult=").append(yearResult);
        sb.append(", courseId='").append(courseId).append('\'');
        sb.append(", locality='").append(locality).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return id == that.id && yearResult == that.yearResult && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(birthDate, that.birthDate) && Objects.equals(login, that.login) && Objects.equals(section, that.section) && Objects.equals(courseId, that.courseId) && Objects.equals(locality, that.locality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, login, section, yearResult, courseId, locality);
    }
}
