package model.DTO;

import model.UserAccount;

import java.time.LocalDate;
import java.util.Set;

public class NewTaskDTO {

    private String name;

    private LocalDate dateStartOfTask;

    private LocalDate dateEndOfTask;

    private UserAccount owner;

    private Set<UserAccount> contributors;
}
