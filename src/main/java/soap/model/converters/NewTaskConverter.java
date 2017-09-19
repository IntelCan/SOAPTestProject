package soap.model.converters;

import soap.model.Task;
import soap.model.dto.NewTaskDTO;
import org.springframework.stereotype.Component;
import soap.utill.Converter;


@Component
public class NewTaskConverter implements Converter<Task, NewTaskDTO> {

    public NewTaskDTO convertEntityToDto(Task task) {
        NewTaskDTO newTaskDTO = new NewTaskDTO();
        newTaskDTO.setName(task.getName());
        newTaskDTO.setOwner(task.getOwner());
        newTaskDTO.setContributors(task.getContributors());
        return newTaskDTO;
    }

    public Task convertDtoToEntity(NewTaskDTO newTaskDTO) {
        Task task = new Task();
        task.setName(newTaskDTO.getName());
        task.setOwner(newTaskDTO.getOwner());
        task.setContributors(newTaskDTO.getContributors());
        return task;
    }
}
