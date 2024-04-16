package tqs.ua.pt.containersdbmigration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public Student findBynMec(int nMec);

    public Student findByName(String name);

    public Student findByEmail(String email);

    public List<Student> findByCourse(String course);

}
