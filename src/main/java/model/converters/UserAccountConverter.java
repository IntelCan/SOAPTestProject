package model.converters;

import model.UserAccount;
import model.dto.UserAccountDTO;
import org.springframework.stereotype.Component;
import utill.Converter;

@Component
public class UserAccountConverter implements Converter<UserAccount,UserAccountDTO> {

    @Override
    public UserAccountDTO convertEntityToDto(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setName(userAccount.getName());
        return userAccountDTO;
    }

    @Override
    public UserAccount convertDtoToEntity(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setName(userAccountDTO.getName());
        return userAccount;
    }
}
