package model.dto;

import lombok.Data;
import model.UserAccount;

import java.time.LocalDate;
import java.util.Set;

@Data
public class NewTaskDTO {

    private String name;

    private LocalDate dateStartOfTask;

    private LocalDate dateEndOfTask;

    private UserAccount owner;

    private Set<UserAccount> contributors;
}
