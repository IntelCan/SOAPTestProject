package soap.model.converters;

import soap.model.Task;
import soap.model.dto.TaskDTO;
import org.springframework.stereotype.Component;
import soap.utill.Converter;

@Component
public class TaskConverter implements Converter<Task, TaskDTO> {

    public TaskDTO convertEntityToDto(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName(task.getName());
        taskDTO.setOwner(task.getOwner());
        taskDTO.setDateStartOfTask(task.getDateStartOfTask());
        taskDTO.setDateEndOfTask(task.getDateEndOfTask());
        taskDTO.setContributors(task.getContributors());
        return taskDTO;
    }

    public Task convertDtoToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setOwner(taskDTO.getOwner());
        task.setDateStartOfTask(taskDTO.getDateStartOfTask());
        task.setDateEndOfTask(taskDTO.getDateEndOfTask());
        task.setContributors(taskDTO.getContributors());
        return task;
    }
}
