package model.converters;

import model.UserAccount;
import model.dto.UserAccountDTO;
import org.springframework.stereotype.Component;
import utill.Converter;

@Component
public class NewUserAccountConverter implements Converter<UserAccount,UserAccountDTO> {
    public UserAccountDTO convertEntityToDto(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setName(userAccount.getName());
        return userAccountDTO;
    }

    public UserAccount convertDtoToEntity(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setName(userAccountDTO.getName());
        return userAccount;
    }
}
