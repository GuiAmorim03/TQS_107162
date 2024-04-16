package tqs.ua.pt.containersdbmigration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@SuppressWarnings("rawtypes")
public class ContainersDbmigrationApplicationTests {

	@Container
	@Order(1)
	public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:14")
			.withUsername("user")
			.withPassword("password")
			.withDatabaseName("test");

	@Autowired
	private StudentRepository studentRepository;

	@DynamicPropertySource
	@Order(2)
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@Test
	@Order(3)
	void contextLoads() {
		Student student = new Student();
		student.setnMec(999999);
		student.setName("Unknown");

		studentRepository.save(student);

		System.out.println("Context loads!");
	}

	@Test
	@Order(4)
	void test_addStudent() {
		int NMEC = 107162;
		String NAME = "Guilherme Amorim";
		String EMAIL = "guilhermeamorim@ua.pt";
		String COURSE = "LEI";

		Student student = new Student();
		student.setnMec(NMEC);
		student.setName(NAME);
		student.setEmail(EMAIL);
		student.setCourse(COURSE);

		studentRepository.save(student);

		Student studentToFind = studentRepository.findBynMec(NMEC);

		assertThat(studentToFind.getName()).isEqualTo(NAME);
		assertThat(studentToFind.getEmail()).isEqualTo(EMAIL);
		assertThat(studentToFind.getCourse()).isEqualTo(COURSE);
	}
}
