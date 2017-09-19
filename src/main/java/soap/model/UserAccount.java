package soap.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name="users_seq_name",sequenceName="users_seq", allocationSize=1, initialValue = 5)
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

}
