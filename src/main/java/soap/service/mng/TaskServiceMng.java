package soap.service.mng;

import soap.model.dto.ContributorsDTO;
import soap.model.dto.NewTaskDTO;
import soap.model.mng.TaskMng;

public interface TaskServiceMng{

        TaskMng createTask(NewTaskDTO newTaskDTO, String name);

        TaskMng addContributorsToTask(String task_name, ContributorsDTO name_contributors);

        void endTask(String task_name);
}
