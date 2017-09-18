package service;

import model.UserAccount;
import model.dto.UserAccountDTO;

import java.util.List;

public interface UserAccountService {

    UserAccountDTO createUserAccount(UserAccountDTO userAccount);

    boolean deleteUser(long id);

    List<UserAccount> getAllUser();

    UserAccountDTO getUserById(Long id);


}
