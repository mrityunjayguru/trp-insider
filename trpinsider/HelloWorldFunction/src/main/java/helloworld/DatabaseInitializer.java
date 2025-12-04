package helloworld;



import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import helloworld.model.Employee;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final EmployeeRepository userRepository;

    public DatabaseInitializer(EmployeeRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // This runs once per container cold start
        System.out.println("Lambda container initialized. Tables ready!");
        // Optionally, seed initial data:
        if(userRepository.count() == 0) {
            userRepository.save(new Employee(1L, "John Doe"));
        }
    }
}
