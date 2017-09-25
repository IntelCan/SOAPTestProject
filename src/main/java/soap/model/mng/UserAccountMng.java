package soap.model.mng;

import lombok.Data;

@Data
public class UserAccountMng {

    private Long id;

    // must be unique for crud
    private String name;
}
