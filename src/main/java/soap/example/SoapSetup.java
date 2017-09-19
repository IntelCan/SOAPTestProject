package soap.example;

import soap.model.converters.NewTaskConverter;
import soap.model.converters.TaskConverter;
import soap.model.converters.UserAccountConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.service.TaskServiceImpl;
import soap.service.UserAccountServiceImpl;
import soap.utill.Converter;

import javax.xml.ws.Endpoint;

@Component
public class SoapSetup {

    @Autowired
    private Converter newTaskConverter;

    @Autowired
    private Converter userAccountConverter;

    @Autowired
    private Converter taskConverter;

    public SoapSetup() {
        this.newTaskConverter = new NewTaskConverter();
        this.userAccountConverter = new UserAccountConverter();
        this.taskConverter = new TaskConverter();
    }

    public void activeSoapEndpoint(){

        UserAccountServiceImpl userAccountService = new UserAccountServiceImpl(userAccountConverter);
        TaskServiceImpl taskService = new TaskServiceImpl(newTaskConverter, taskConverter);

        String addressUserAccount = "http://localhost:9001/userAccountService";
        Endpoint.publish(addressUserAccount, userAccountService);
        String addressTask = "http://localhost:9001/taskService";
        Endpoint.publish(addressTask, taskService);

    }

}
