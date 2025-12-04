package helloworld;



import helloworld.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Example custom query method: find employees by department
    List<Employee> findByDepartment(String department);

    // Example: find by email
    Employee findByEmail(String email);
}
