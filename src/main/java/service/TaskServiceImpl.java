package service;

import model.Task;
import model.UserAccount;
import model.converters.NewTaskConverter;
import model.dto.NewTaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import utill.AbstractDao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TaskServiceImpl extends AbstractDao implements TaskService {

    @Autowired
    private NewTaskConverter newTaskConverter;


    public NewTaskDTO createTask(NewTaskDTO newTaskDTO) {
      //  this.saveOrUpdate(newTaskDTO);
        return newTaskDTO;
    }

    public Task addContributorsToTask(long idTask, UserAccount... userAccounts)
    {
//         Task task = (Task)find(Task.class, idTask);
//         Set<UserAccount> contributors = new HashSet<UserAccount>(Arrays.asList(userAccounts));
//         task.setContributors(contributors);
//         super.saveOrUpdate(task);
         return null;
    }


    public void endTask(long idTask) {

    }
}
