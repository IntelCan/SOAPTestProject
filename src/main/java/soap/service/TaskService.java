package soap.service;

import org.springframework.stereotype.Service;
import soap.model.Task;
import soap.model.UserAccount;
import soap.model.dto.NewTaskDTO;

import java.util.Set;

@Service
public interface TaskService {

    NewTaskDTO createTask(NewTaskDTO newTaskDTO, long owner_id);

    Task addContributorsToTask(long idTask, Long... contributors_id);

    void endTask(long idTask);

}
