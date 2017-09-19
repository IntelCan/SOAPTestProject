package soap.service;

import org.springframework.stereotype.Service;
import soap.model.dto.UserAccountDTO;

import java.util.List;

@Service
public interface UserAccountService {

    UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO);

    boolean deleteUser(long id);

    List<UserAccountDTO> getAllUser();

    UserAccountDTO getUserById(Long id);

}
