package soap.model.mng;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class TaskMng {

    private Long id;

    private String name;

    private Date dateStartOfTask;

    private Date dateEndOfTask;

    private UserAccountMng owner;

    private Set<UserAccountMng> contributors;
}
