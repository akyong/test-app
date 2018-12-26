package test.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    public Student(){}

    public Student(@NotNull String firstName, @NotNull String lastName, @NotNull int age, @NotNull Date birthday, Date dateCreated, @Nullable Date lastUpdated){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthday = birthday;
        this.dateCreated = new Date();

        if(!lastUpdated.equals(null)){
            this.lastUpdated = new Date();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "firstName",nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "lastName",nullable = false)
    private String lastName;

    @Positive
    @Column(name = "age")
    private int age;

    @NotNull
    @Column(name = "birthday",nullable = false)
    private Date birthday;

    @Column(name = "date_created")
    private Date dateCreated;

    @Nullable
    @Column(name = "last_updated")
    private Date lastUpdated;

    @OneToOne(cascade =  CascadeType.ALL,mappedBy = "student")
    private StudentGroup studentGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setStudentGroups(StudentGroup studentGroups) {
        this.studentGroups = studentGroups;
    }

    public StudentGroup getStudentGroups() {
        return studentGroups;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Nullable
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(@Nullable Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student{");
        sb.append("id=");
        sb.append(id);
        sb.append(", firstName='");
        sb.append(firstName);
        sb.append("', lastName='");
        sb.append(lastName);
        sb.append("', age=");
        sb.append(age);
        sb.append(", birthday=");
        sb.append(birthday);
        sb.append("', date_created='");
        sb.append(dateCreated);
        sb.append("'}");
        return sb.toString();
    }
}
