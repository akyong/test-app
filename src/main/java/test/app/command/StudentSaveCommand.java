package test.app.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

public class StudentSaveCommand {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Positive
    private int age;

    @NotNull
    private Date birthday;

    public StudentSaveCommand() {}

    public StudentSaveCommand(String firstName, String lastName, int age, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        System.out.println("age  ==  "+age);
        this.age = age;
        this.birthday = birthday;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge(){ return age;}

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday(){return birthday;}
}
