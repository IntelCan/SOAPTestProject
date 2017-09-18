package service;

import model.UserAccount;
import model.dto.UserAccountDTO;

import java.util.List;

public interface UserAccountService {

    UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO);

    boolean deleteUser(long id);

    List<UserAccountDTO> getAllUser();

    UserAccountDTO getUserById(Long id);

}
