package test.app.controller;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
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
@Controller("/student")
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
        System.out.println("-------------aaa------");
        return studentRepository.findAll(args);
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post("/create")
    public HttpResponse<Student> save(@Body @Valid StudentSaveCommand command) {
        System.out.println("firstname = "+command.getFirstName());
        System.out.println("lastname = "+command.getLastName());
        System.out.println("gae = "+command.getAge());

        Student student = studentRepository.save(
                command.getFirstName(),
                command.getLastName(),
                command.getAge()
        );

        return HttpResponse
                .created(student)
                .headers(headers -> headers.location(location(student.getId())));
//        System.out.println("hasil = "+HttpResponse
//                .created(student)
//                .headers(headers -> headers.location(location(student.getId()))));


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
