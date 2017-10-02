package soap.service.mng;

import soap.model.Task;
import soap.model.dto.ContributorsDTO;
import soap.model.dto.NewTaskDTO;

import java.util.List;

public interface TaskServiceMng{

        Task createTask(NewTaskDTO newTaskDTO, String name);

        List<String> addContributorsToTask(String task_name, ContributorsDTO name_contributors);

        void endTask(String task_name);
}
