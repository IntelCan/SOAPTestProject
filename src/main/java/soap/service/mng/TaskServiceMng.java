package soap.service.mng;

import soap.model.dto.ContributorsDTO;
import soap.model.dto.NewTaskDTO;
import soap.model.mng.TaskMng;

import java.util.List;

public interface TaskServiceMng{

        TaskMng createTask(NewTaskDTO newTaskDTO, String name);

        List<String> addContributorsToTask(String task_name, ContributorsDTO name_contributors);

        void endTask(String task_name);
}
