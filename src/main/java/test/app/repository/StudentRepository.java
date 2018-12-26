package test.app.repository;

import test.app.domain.Student;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Optional<Student> findById(@NotNull Long id);

    Student save(@NotBlank String firstName,@NotBlank String lastName, int age);

    void deleteById(@NotNull Long id);

    int update(@NotNull Long id, @NotBlank String firstName, @NotBlank String lastName, int age);

    List<Student> findAll(@NotNull SortingAndOrderArguments args);

}
