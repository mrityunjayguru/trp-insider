package helloworld;


/* 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            final String pageContents = this.getPageContents("https://checkip.amazonaws.com");
            String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents);

            return response
                    .withStatusCode(200)
                    .withBody(output);
        } catch (IOException e) {
            return response
                    .withBody("{}")
                    .withStatusCode(500);
        }
    }

    private String getPageContents(String address) throws IOException{
        URL url = new URL(address);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
*/




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.sql.*;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;


public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {

        String path = request.getPath();
        String method = request.getHttpMethod();
        

        System.out.println(" Common Handler ");

        System.out.println(" Common Handler ");
        System.out.println(" Common Handler ");
        System.out.println(" Common Handler ");
        System.out.println(" Common Handler ");

        this.getConnection();


        // Simple routing
        if (path.equals("/Prod/user") && method.equals("GET")) {
            return response(200, "User endpoint hit!  Hi I am Niraj Also ");
        }
        if (path.equals("/Prod/createemployee") && method.equals("POST")) {
         return  new CreateEmployeeHandler().handleRequest(request, context);
            // return response(200, "User endpoint hit!  Hi I am Niraj Also ");
        }
        if (path.equals("/Prod/getallemployee") && method.equals("GET")) {
         return  new CreateEmployeeHandler().getAllEmployee(request, context);
       
        }

        if (path.equals("/Prod/order") && method.equals("POST")) {
            return response(200, "Order created!");
        }
        if (path.startsWith("/Prod/product/") && method.equals("GET")) {
            String id = request.getPathParameters().get("id");
            return response(200, "Product ID: " + id);
        }

        return response(404, "Niraj : Endpoint not found");
    }

    private APIGatewayProxyResponseEvent response(int status, String body) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(status)
                .withBody(body);
    }


public APIGatewayProxyResponseEvent handleRequestResponse(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            final String pageContents = this.getPageContents("https://checkip.amazonaws.com");
            String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents);

            return response
                    .withStatusCode(200)
                    .withBody(output);
        } catch (IOException e) {
            return response
                    .withBody("{}")
                    .withStatusCode(500);
        }
    }



private String getPageContents(String address) throws IOException{
        URL url = new URL(address);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }


 private Connection getConnection(){

    String host = "13.203.239.58";
        String dbName = "trpinsiderdb";
        String user = "postgres";
        String password = "traccar@1234";
        String port =  "5432";

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

        System.out.println("URL "+url);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            PreparedStatement stmt = conn.prepareStatement("SELECT NOW()");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("PostgreSQL Time: " + rs.getString(1));
            
                return conn;
            }

        } catch (Exception e) {
            System.out.println( "Error: " + e.getMessage());
            return null;
        }

        return null;
    }

    
    


}
