package example;


import model.UserAccount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.UserAccountServiceImpl;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        UserAccountServiceImpl userAccountService = new UserAccountServiceImpl();
        String addressUserAccount = "http://localhost:9000/userAccountService";
        Endpoint.publish(addressUserAccount, userAccountService);
    }

}
