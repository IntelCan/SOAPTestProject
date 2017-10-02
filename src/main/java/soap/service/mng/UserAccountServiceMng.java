package soap.service.mng;

import soap.model.UserAccount;
import soap.model.dto.UserAccountDTO;

import java.util.List;

public interface UserAccountServiceMng {

    UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO);

    boolean deleteUser(String name);

    List<UserAccount> getAllUser();

    UserAccount getUserByName(String name);

}
