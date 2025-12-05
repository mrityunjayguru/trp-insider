package helloworld;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import helloworld.model.Employee;

public class CreateEmployeeHandler 

{
    private ObjectMapper mapper = new ObjectMapper();
    private static final Map<String, String> corsHeaders = Map.of(
    "Access-Control-Allow-Origin", "*",
    "Access-Control-Allow-Methods", "GET,POST,OPTIONS",
    "Access-Control-Allow-Headers", "Content-Type"
);

    
    public APIGatewayProxyResponseEvent getAllEmployee(APIGatewayProxyRequestEvent request, Context context) {
    ObjectMapper mapper = new ObjectMapper();
    try (Connection conn = DBConnection.getConnection();
         Statement stmt = conn.createStatement()) {

        // Execute query
        ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

        
        // Convert ResultSet to List<Employee>
        List<Employee> employees = new ArrayList<>();
        while (rs.next()) {
            Employee emp = new Employee();
            emp.setId(rs.getLong("id"));
            emp.setName(rs.getString("name"));
            emp.setEmail(rs.getString("email"));
            emp.setSalary(rs.getDouble("salary"));
            employees.add(emp);
        }

        // Convert list to JSON
        String jsonResponse = mapper.writeValueAsString(employees);

        conn.close();
        rs.close();

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(jsonResponse)
                .withHeaders(Map.of(
            "Access-Control-Allow-Origin", "*",
            "Access-Control-Allow-Methods", "GET,POST,OPTIONS",
            "Access-Control-Allow-Headers", "Content-Type,Authorization"
        ));
    } catch (Exception e) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(500)
                .withBody("Error: " + e.getMessage())
                .withHeaders(Map.of(
            "Access-Control-Allow-Origin", "*",
            "Access-Control-Allow-Methods", "GET,POST,OPTIONS",
            "Access-Control-Allow-Headers", "Content-Type,Authorization"
        ));

    }
    finally{

        

    }
}


     public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        try {
            // Convert JSON → Employee object
          ObjectMapper mapper = new ObjectMapper();
mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

String body = request.getBody();
if (body == null || body.isEmpty()) {
     return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withHeaders(corsHeaders)
        .withBody("Niraj: Request Body is empty");

}

Employee employee = mapper.readValue(body, Employee.class);
            // Save to DB

            System.out.println(" Employee ");
            System.out.println(" Employee ");
            System.out.println(employee);
            System.out.println(" Employee ");
            System.out.println(" Employee ");
            System.out.println(" Employee ");



            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO employees (name, email, salary) VALUES ( ?, ?, ?)");
            
           // ps.setInt(1, employee.getId());
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setDouble(3, employee.getSalary());

            ps.executeUpdate();

            conn.close();
            ps.close();

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withHeaders(corsHeaders).withBody("Niraj Employee created successfully");


        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    
                    .withHeaders(corsHeaders).withBody("Niraj from daabase Error: " + e.getMessage());
        }
    }


}
