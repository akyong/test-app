package test.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grup")
public class Group {

    public Group(){}

    public Group(@NotNull String code, @NotNull String name, String description){
        this.code = code;
        this.name = name;
        this.description =description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "name",nullable = false)
    private  String name;

    @Null
    @Column(name = "description")
    private String description;

    @OneToOne(cascade =  CascadeType.ALL,mappedBy = "student")
    private StudentGroup studentGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public StudentGroup getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(StudentGroup studentGroups) {
        this.studentGroups = studentGroups;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Grup{");
        sb.append("id=");
        sb.append(id);
        sb.append(", code='");
        sb.append(code);
        sb.append("', name='");
        sb.append(name);
        sb.append("', description='");
        sb.append(description);
        sb.append("'}");
        return sb.toString();

    }
}
