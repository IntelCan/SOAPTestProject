package soap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import soap.example.SoapSetup;

@SpringBootApplication
@ComponentScan(basePackages = "soap")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        SoapSetup soapSetup = new SoapSetup();
        soapSetup.activeSoapEndpoint();
    }

}
