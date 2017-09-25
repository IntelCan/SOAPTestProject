package soap.example;

import lombok.extern.slf4j.Slf4j;
import soap.model.converters.NewTaskConverter;
import soap.model.converters.TaskConverter;
import soap.model.converters.UserAccountConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.service.TaskServiceImpl;
import soap.service.UserAccountServiceImpl;
import soap.service.mng.UserAccountServiceMngImpl;

import javax.xml.ws.Endpoint;

@Component
@Slf4j
public class SoapSetup {

    @Autowired
    private NewTaskConverter newTaskConverter;

    @Autowired
    private UserAccountConverter userAccountConverter;

    @Autowired
    private TaskConverter taskConverter;

    public SoapSetup() {
        this.newTaskConverter = new NewTaskConverter();
        this.userAccountConverter = new UserAccountConverter();
        this.taskConverter = new TaskConverter();
    }

    public void activeSoapEndpoint(){

        UserAccountServiceImpl userAccountService = new UserAccountServiceImpl(userAccountConverter);
        TaskServiceImpl taskService = new TaskServiceImpl(newTaskConverter, taskConverter);
        UserAccountServiceMngImpl userAccountServiceMng = new UserAccountServiceMngImpl();

        log.info("Start aplikacji testowej SOAP");

        String addressUserAccount = "http://localhost:9001/userAccountService";
        Endpoint.publish(addressUserAccount, userAccountService);
        String addressTask = "http://localhost:9001/taskService";
        Endpoint.publish(addressTask, taskService);
        String addressUserMng = "http://localhost:9001/userAccountServiceMng";
        Endpoint.publish(addressUserMng, userAccountServiceMng);


    }

}
