package soap.service;

import soap.model.Task;
import soap.model.UserAccount;
import soap.model.dto.NewTaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import soap.utill.AbstractDao;
import soap.utill.Converter;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@WebService
public class TaskServiceImpl extends AbstractDao implements TaskService {



    @Autowired
    private Converter newTaskConverter;

    @Autowired
    private Converter taskConverter;

    public TaskServiceImpl() {}
    
    public TaskServiceImpl(Converter newTaskConverter, Converter taskConverter) {
        super();
        this.newTaskConverter = newTaskConverter;
        this.taskConverter = taskConverter;
    }

    @WebMethod
    @Override
    public NewTaskDTO createTask(NewTaskDTO newTaskDTO) {
        Task task = (Task) newTaskConverter.convertDtoToEntity(newTaskDTO);
        task.setDateStartOfTask(new Date());
        this.saveOrUpdate(task);
        return newTaskDTO;
    }

    @WebMethod
    @Override
    public Task addContributorsToTask(long idTask, UserAccount... userAccounts) {
         Task task = (Task)find(Task.class, idTask);
         Set<UserAccount> contributors = new HashSet<UserAccount>(Arrays.asList(userAccounts));
         task.setContributors(contributors);
         super.saveOrUpdate(task);
         return task;
    }

    @WebMethod
    @Override
    public void endTask(long idTask) {

    }

}
