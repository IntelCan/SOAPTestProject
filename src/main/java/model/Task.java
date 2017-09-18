package model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserAccount owner;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserAccount> contributors;

}
