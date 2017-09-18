package service;

import model.Task;
import model.UserAccount;
import model.dto.NewTaskDTO;
import utill.AbstractDao;

public interface TaskService {

    public NewTaskDTO createTask(NewTaskDTO newTaskDTO);

    Task addContributorsToTask(long idTask, UserAccount... userAccounts);

    void endTask(long idTask);

}
