package example;


import model.UserAccount;
import service.UserAccountServiceImpl;

import javax.xml.ws.Endpoint;

public class Main {

    public static void main(String[] args) {
        UserAccountServiceImpl userAccountService = new UserAccountServiceImpl();
        String address = "http://localhost:9000/soapTest";
        Endpoint.publish(address, userAccountService);
    }


}
