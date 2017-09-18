package model.dto;

import lombok.Data;
import model.UserAccount;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
public class NewTaskDTO {

    private String name;

    private Date dateStartOfTask;

    private Date dateEndOfTask;

    private UserAccount owner;

    private Set<UserAccount> contributors;
}
