package example;


import model.UserAccount;
import service.UserAccountServiceImpl;

import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args) {
        UserAccountServiceImpl userAccountService = new UserAccountServiceImpl();
        String addressUserAccount = "http://localhost:9000/userAccountService";
        Endpoint.publish(addressUserAccount, userAccountService);
    }

}
