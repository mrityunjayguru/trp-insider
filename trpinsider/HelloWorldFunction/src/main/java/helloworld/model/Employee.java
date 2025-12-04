package helloworld.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data                 // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor    // Generates a no-args constructor
@AllArgsConstructor   // Generates a constructor with all fields
public class Employee {
    @Id
    private Long id;
    private String name;

    //Auto Generated Getters and Setters
}
