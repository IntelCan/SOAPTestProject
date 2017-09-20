package soap.model.dto;

import lombok.Data;
import soap.model.UserAccount;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class TaskDTO {

    private String name;

    private Date dateStartOfTask;

    private Date dateEndOfTask;

    private UserAccount owner;

    private Set<UserAccount> contributors;
}
