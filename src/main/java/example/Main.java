package example;


import model.UserAccount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.TaskServiceImpl;
import service.UserAccountServiceImpl;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        UserAccountServiceImpl userAccountService = new UserAccountServiceImpl();
        TaskServiceImpl taskService = new TaskServiceImpl();
        String addressUserAccount = "http://localhost:9001/userAccountService";
        Endpoint.publish(addressUserAccount, userAccountService);
        String addressTask = "http://localhost:9001/taskService";
        Endpoint.publish(addressTask, taskService);

    }

}
