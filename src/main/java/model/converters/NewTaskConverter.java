package model.converters;

import model.Task;
import model.dto.NewTaskDTO;
import org.springframework.stereotype.Component;
import utill.Converter;


@Component
public class NewTaskConverter implements Converter<Task, NewTaskDTO> {

    public NewTaskDTO convertEntityToDto(Task task) {
        NewTaskDTO newTaskDTO = new NewTaskDTO();
        newTaskDTO.setName(task.getName());
        newTaskDTO.setOwner(task.getOwner());
        newTaskDTO.setDateStartOfTask(task.getDateStartOfTask());
        newTaskDTO.setDateEndOfTask(task.getDateEndOfTask());
        newTaskDTO.setContributors(task.getContributors());
        return newTaskDTO;
    }

    public Task convertDtoToEntity(NewTaskDTO newTaskDTO) {
        Task task = new Task();
        task.setName(newTaskDTO.getName());
        task.setOwner(newTaskDTO.getOwner());
        task.setDateStartOfTask(newTaskDTO.getDateStartOfTask());
        task.setDateEndOfTask(newTaskDTO.getDateEndOfTask());
        task.setContributors(newTaskDTO.getContributors());
        return task;
    }
}
