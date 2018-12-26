package test.app.repository;

import test.app.domain.Student;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student findById(@NotNull Long id);

    Student save(@NotBlank String firstName,@NotBlank String lastName,@Positive int age, @NotNull Date birthday);

    void deleteById(@NotNull Long id);

    int update(@NotNull Long id, @NotBlank String firstName, @NotBlank String lastName, @Positive int age, @NotNull Date birthday);

    List<Student> findAll(@NotNull SortingAndOrderArguments args);

}
