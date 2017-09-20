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
        return newTaskDTO;
    }

    public Task convertDtoToEntity(NewTaskDTO newTaskDTO) {
        Task task = new Task();
        task.setName(newTaskDTO.getName());
        return task;
    }
}
