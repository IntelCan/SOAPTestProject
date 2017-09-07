package service;

import model.UserAccount;

import java.util.List;

public interface UserAccountService {

    UserAccount createUserAccount(UserAccount userAccount);

    boolean deleteUser(long id);

    public List<UserAccount> getAllUser();
}
