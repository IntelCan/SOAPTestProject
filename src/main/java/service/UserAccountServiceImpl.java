package service;

import model.UserAccount;
import model.converters.UserAccountConverter;
import model.dto.UserAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import utill.AbstractDao;
import utill.Converter;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@WebService
public class UserAccountServiceImpl extends AbstractDao implements UserAccountService {

    @Autowired
    private Converter userConverter;

    public UserAccountServiceImpl(Converter converter) {
        super();
        this.userConverter = converter;
    }

    @WebMethod
    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO) {

        UserAccount newUser = (UserAccount)userConverter.convertDtoToEntity(userAccountDTO);
        super.saveOrUpdate(newUser);
     return userAccountDTO;
    }

    @WebMethod
    public boolean deleteUser(long user_id) {
        try {
           Object foundUserAccount = super.find(UserAccount.class, user_id);
            super.delete(foundUserAccount);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @WebMethod
    public List<UserAccountDTO> getAllUser(){
        List<UserAccount> users = super.findAll(UserAccount.class);
        return users.stream()
                .map( user -> (UserAccountDTO)userConverter.convertEntityToDto(user)).
                collect(Collectors.toList());
    }

    @WebMethod
    public UserAccountDTO getUserById(Long user_id) {
       UserAccount foundUserAccount = (UserAccount) super.find(UserAccount.class, user_id);

     return (UserAccountDTO)userConverter.convertEntityToDto(foundUserAccount);
    }
}
