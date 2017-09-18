package config;

import service.TaskServiceImpl;
import service.UserAccountServiceImpl;

import javax.xml.ws.Endpoint;

public class EnpointConfig {

    public void activeSoapEndpoint{

        UserAccountServiceImpl userAccountService = new UserAccountServiceImpl();
        TaskServiceImpl taskService = new TaskServiceImpl();

        String addressUserAccount = "http://localhost:9001/userAccountService";
        Endpoint.publish(addressUserAccount, userAccountService);
        String addressTask = "http://localhost:9001/taskService";
        Endpoint.publish(addressTask, taskService);

    }

}
