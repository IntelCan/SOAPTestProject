package soap.model.converters;

import soap.model.UserAccount;
import soap.model.dto.UserAccountDTO;
import org.springframework.stereotype.Component;
import soap.utill.Converter;

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
