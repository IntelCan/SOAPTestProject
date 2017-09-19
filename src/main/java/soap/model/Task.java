package soap.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Date dateStartOfTask;

    private Date dateEndOfTask;

    @OneToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private UserAccount owner;

    @OneToMany( fetch = FetchType.LAZY)
    private Set<UserAccount> contributors;

}
