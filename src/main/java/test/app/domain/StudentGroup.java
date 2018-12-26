package test.app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student_group")
public class StudentGroup {
    public StudentGroup(){}

    public StudentGroup(Student student, Group group){
        this.student = student;
        this.group = group;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)/**/
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Group group;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StudentGroup{");
        sb.append("id=");
        sb.append(id);
        sb.append(", student=");
        sb.append(student);
        sb.append(", group=");
        sb.append(group);
        sb.append("}");
        return sb.toString();
    }
}
