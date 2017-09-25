package soap.service.mng;

import soap.model.mng.UserAccountMng;

import java.util.List;

public interface UserAccountServiceMng {

    UserAccountMng createUserAccount(UserAccountMng userAccountMng);

    boolean deleteUser(String name);

    List<UserAccountMng> getAllUser();

    UserAccountMng getUserByName(String name);

}
