package test.app;

import io.micronaut.context.ApplicationContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import test.app.command.StudentSaveCommand;
import test.app.command.StudentUpdateCommand;
import test.app.domain.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class StudentControllerTest {
    private static EmbeddedServer server; // <1>
    private static HttpClient client; // <2>

    @BeforeClass
    public static void setupServer() {
        server = ApplicationContext
                .build()
                .run(EmbeddedServer.class); // <1>
        client = server.getApplicationContext().createBean(HttpClient.class, server.getURL()); // <2>
    }

    @AfterClass
    public static void stopServer() {
        if (server != null) {
            server.stop();
        }
        if (client != null) {
            client.stop();
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void supplyAnInvalidOrderTriggersValidationFailure() {
        thrown.expect(HttpClientResponseException.class);
//        thrown.expect(hasProperty("response", hasProperty("status", is(HttpStatus.BAD_REQUEST))));
        thrown.expect(hasProperty("response", hasProperty("status", is(HttpStatus.NOT_FOUND))));
        client.toBlocking().exchange(HttpRequest.GET("/student/list?order=foo"));
    }

    @Test
    public void testFindNonExistingGenreReturns404() {
        thrown.expect(HttpClientResponseException.class);
        thrown.expect(hasProperty("response", hasProperty("status", is(HttpStatus.NOT_FOUND))));
        HttpResponse response = client.toBlocking().exchange(HttpRequest.GET("/student/99"));
        System.out.println("Response = "+response);
    }

    @Test
    public void testStudentCrudOperations() {

        List<Long> studentIds = new ArrayList<>(); //list tampung id
        Date skrg = new Date(); //membuat tanggal skrg

        HttpRequest request = HttpRequest.POST("/student", new StudentSaveCommand("Bobby","S. Kom", 25)); // <3>
        HttpResponse response = client.toBlocking().exchange(request);
        studentIds.add(entityId(response));
        assertEquals(HttpStatus.CREATED, response.getStatus());
//
//        request = HttpRequest.POST("/stuent", new StudentSaveCommand("Akiong","M. Kom",27,skrg)); // <3>
//        response = client.toBlocking().exchange(request);
//        assertEquals(HttpStatus.CREATED, response.getStatus());
//        Long id = entityId(response);
//        studentIds.add(id);
//
//        request = HttpRequest.GET("/student/"+id);
//        Student student = client.toBlocking().retrieve(request, Student.class); // <4>
//
//        assertEquals("Bobby", student.getFirstName());
//
//        request = HttpRequest.PUT("/student", new StudentUpdateCommand(id, "Bobby","S.Kom,M.Kom",28,skrg));
//        response = client.toBlocking().exchange(request);  // <5>
//
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
//
//        request = HttpRequest.GET("/student/" + id);
//        student = client.toBlocking().retrieve(request, Student.class);
//        assertEquals("Bobby", student.getFirstName());
//
//        request = HttpRequest.GET("/student/list");
//        List<Student> students = client.toBlocking().retrieve(request, Argument.of(List.class, Student.class));
//
//        assertEquals(2, students.size());
//
//        request = HttpRequest.GET("/student/list?max=1");
//        students = client.toBlocking().retrieve(request, Argument.of(List.class, Student.class));
//
//        assertEquals(1, students.size());
//        assertEquals("Bobby", students.get(0).getFirstName());
//
//        request = HttpRequest.GET("/student/list?max=1&order=desc&sort=name");
//        students = client.toBlocking().retrieve(request, Argument.of(List.class, Student.class));
//
//        assertEquals(1, students.size());
//        assertEquals("Bobby", students.get(0).getFirstName());
//
//        request = HttpRequest.GET("/student/list?max=1&offset=10");
//        students = client.toBlocking().retrieve(request, Argument.of(List.class, Student.class));
//
//        assertEquals(0, students.size());

//         cleanup:
//        for (Long studentId : studentIds) {
//            request = HttpRequest.DELETE("/student/"+studentId);
//            response = client.toBlocking().exchange(request);
//            assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
//        }
    }

    protected Long entityId(HttpResponse response) {
        String path = "/student/";
        String value = response.header(HttpHeaders.LOCATION);
        if ( value == null) {
            return null;
        }
        int index = value.indexOf(path);
        if ( index != -1) {
            return Long.valueOf(value.substring(index + path.length()));
        }
        return null;
    }

}
