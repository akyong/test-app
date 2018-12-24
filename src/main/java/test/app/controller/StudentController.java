package test.app.controller;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import test.app.command.StudentSaveCommand;
import test.app.command.StudentUpdateCommand;
import test.app.domain.Student;
import test.app.repository.SortingAndOrderArguments;
import test.app.repository.StudentRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Validated
@Controller("student")
public class StudentController {
    protected final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Get("/{id}")
    public Student show(Long id) {
        return studentRepository
                .findById(id)
                .orElse(null);
    }

    @Put("/")
    public HttpResponse update(@Body @Valid StudentUpdateCommand command) {
        int numberOfEntitiesUpdated = studentRepository.update(
                command.getId(),
                command.getFirstName(),
                command.getLastName(),
                command.getAge()
//                command.getBirthday()
        );

        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
    }

    @Get(value = "/list{?args*}")
    public List<Student> list(@Valid SortingAndOrderArguments args) {
        return studentRepository.findAll(args);
    }

    @Post("/")
    public HttpResponse<Student> save(@Body @Valid StudentSaveCommand command) {
        Student student = studentRepository.save(
                command.getFirstName(),
                command.getLastName(),
                command.getAge()
        );

        return HttpResponse
                .created(student)
                .headers(headers -> headers.location(location(student.getId())));
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        studentRepository.deleteById(id);
        return HttpResponse.noContent();
    }

    protected URI location(Long id) {
        return URI.create("/student/" + id);
    }

    protected URI location(Student student) {
        return location(student.getId());
    }
}
