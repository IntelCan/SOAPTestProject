package service;

import model.Task;
import model.UserAccount;
import model.converters.NewTaskConverter;
import model.dto.NewTaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import utill.AbstractDao;
import utill.Converter;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebService
public class TaskServiceImpl extends AbstractDao implements TaskService {

    @Autowired
    private Converter newTaskConverter;

    public TaskServiceImpl(Converter converter) {
        super();
        this.newTaskConverter = new NewTaskConverter();
    }

    @WebMethod
    public NewTaskDTO createTask(NewTaskDTO newTaskDTO) {
        this.saveOrUpdate(newTaskDTO);
        return newTaskDTO;
    }

    @WebMethod
    public Task addContributorsToTask(long idTask, UserAccount... userAccounts)
    {
         Task task = (Task)find(Task.class, idTask);
         Set<UserAccount> contributors = new HashSet<UserAccount>(Arrays.asList(userAccounts));
         task.setContributors(contributors);
         super.saveOrUpdate(task);
         return task;
    }


    @WebMethod
    public void endTask(long idTask) {

    }
}
