package test.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    public Student(){}

    public Student(@NotNull String firstName, @NotNull String lastName, @NotNull int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
//        this.birthday = birthday;
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

//    @NotNull
//    @Column(name = "birthday",nullable = false)
//    private Date birthday;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<StudentGroup> studentGroups = new HashSet<>();

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

//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(Date birthday){
//        this.birthday = birthday;
//    }

    public void setStudentGroups(Set<StudentGroup> studentGroups) {
        this.studentGroups = studentGroups;
    }

    public Set<StudentGroup> getStudentGroups() {
        return studentGroups;
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
//        sb.append(", birthday=");
//        sb.append(birthday);
        sb.append("}");
        return sb.toString();
    }
}
