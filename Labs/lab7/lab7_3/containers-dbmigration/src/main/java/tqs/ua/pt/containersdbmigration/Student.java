package tqs.ua.pt.containersdbmigration;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    private int nMec;
    private String name;
    private String email;
    private String course;

    public Student(int nMec, String name, String email, String course) {
        this.nMec = nMec;
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public Student() {
    }

    public int getnMec() {
        return nMec;
    }

    public void setnMec(int nMec) {
        this.nMec = nMec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
