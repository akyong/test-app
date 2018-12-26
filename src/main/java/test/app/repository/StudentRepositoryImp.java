package test.app.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import test.app.configuration.ApplicationConfiguration;
import test.app.domain.Student;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Singleton
public class StudentRepositoryImp implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;
    private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "firstName","lastName","age","birthday");

    public StudentRepositoryImp(@CurrentSession EntityManager entityManager, ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(@NotNull Long id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }
    //nanti tambahkan findByFirstName, findByLastName, findByAge, findByBirthday

    @Override
    @Transactional
    public Student save(@NotBlank String firstName, @NotBlank String lastName,@Positive int age,@NotNull Date birthday) {
        Student student = new Student(firstName,lastName,age,birthday,new Date(),new Date());
        entityManager.persist(student);
        return student;
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }

    @Override
    @Transactional
    public int update(@NotNull Long id, @NotBlank String firstName, @NotBlank String lastName,@Positive int age,@NotNull Date birthday) {
        return entityManager.createQuery(
                "UPDATE Student g SET firstName = :firstName," +
                        "lastName = :lastName," +
                        "age = :age, " +
                        "birthday = :birthday, " +
                        "lastUpdated = :lastUpdated " +
                        "where id = :id")
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("age", age)
                .setParameter("birthday", birthday)
                .setParameter("lastUpdated", new Date())
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional(readOnly = true)
    public List<Student> findAll(@NotNull SortingAndOrderArguments args) {
        String qlString = "SELECT g FROM Student as g";
        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY g." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
        }
        TypedQuery<Student> query = entityManager.createQuery(qlString, Student.class);
        query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
        args.getOffset().ifPresent(query::setFirstResult);

        return query.getResultList();
    }


}
