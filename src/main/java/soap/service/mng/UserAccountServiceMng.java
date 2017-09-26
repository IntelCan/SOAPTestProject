package soap.service.mng;

import soap.model.dto.UserAccountDTO;
import soap.model.mng.UserAccountMng;

import java.util.List;

public interface UserAccountServiceMng {

    UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO);

    boolean deleteUser(String name);

    List<UserAccountMng> getAllUser();

    UserAccountMng getUserByName(String name);

}
