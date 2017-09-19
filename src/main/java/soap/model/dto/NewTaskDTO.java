package soap.model.dto;

import lombok.Data;
import soap.model.UserAccount;

import java.util.Date;
import java.util.Set;

@Data
public class NewTaskDTO {

    private String name;

    private UserAccount owner;

    private Set<UserAccount> contributors;
}
