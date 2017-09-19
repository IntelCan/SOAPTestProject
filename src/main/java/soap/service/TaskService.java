package soap.service;

import org.springframework.stereotype.Service;
import soap.model.Task;
import soap.model.UserAccount;
import soap.model.dto.NewTaskDTO;

@Service
public interface TaskService {

    public NewTaskDTO createTask(NewTaskDTO newTaskDTO);

    Task addContributorsToTask(long idTask, UserAccount... userAccounts);

    void endTask(long idTask);

}
