package soap.service;

import org.springframework.stereotype.Service;
import soap.model.Task;
import soap.model.UserAccount;
import soap.model.dto.NewTaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import soap.utill.AbstractDao;
import soap.utill.Converter;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.*;

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
    public NewTaskDTO createTask(NewTaskDTO newTaskDTO, long owner_id) {
        UserAccount owner = (UserAccount) super.find(UserAccount.class, owner_id);
        Task task = (Task) newTaskConverter.convertDtoToEntity(newTaskDTO);
        task.setDateStartOfTask(new Date());
        task.setOwner(owner);
        this.saveOrUpdate(task);
        return newTaskDTO;
    }

    @WebMethod
    @Override
    public Task addContributorsToTask(long idTask, Long... contributors_id) {
         Task task = (Task)find(Task.class, idTask);
         Set<UserAccount> contributors = new HashSet<>(task.getContributors());
         for(long contributor_id: contributors_id){
             contributors.add((UserAccount)super.find(UserAccount.class, contributor_id));
         }
         task.setContributors(contributors);
         super.saveOrUpdate(task);
         return task;
    }

    @WebMethod
    @Override
    public void endTask(long idTask) {
        Task task = (Task)super.find(Task.class, idTask);
        task.setDateEndOfTask(new Date());
        super.saveOrUpdate(task);
    }



}
