package helloworld;

import com.amazonaws.services.simplesystemsmanagement.*;
import com.amazonaws.services.simplesystemsmanagement.model.*;

public class Config {

    private static final AWSSimpleSystemsManagement ssm =
            AWSSimpleSystemsManagementClientBuilder.defaultClient();

    public static String get(String key, String defaultValue) {
        try {
            GetParameterRequest req = new GetParameterRequest()
                    .withName("/myapp/" + key)
                    .withWithDecryption(true);
            return ssm.getParameter(req).getParameter().getValue();
        } catch (Exception e) {
            return defaultValue;  // fallback if parameter not found
        }
    }
}
