package test.app.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

public class StudentUpdateCommand {
    @NotNull
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Positive
    private int age;

    @NotNull
    private Date birthday;


    public StudentUpdateCommand() {}

    public StudentUpdateCommand(Long id, String firstName,String lastName, int age, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
       return lastName;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
}
