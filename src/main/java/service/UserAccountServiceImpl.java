package service;

import model.UserAccount;
import model.converters.UserAccountConverter;
import model.dto.UserAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import utill.AbstractDao;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class UserAccountServiceImpl extends AbstractDao implements UserAccountService {

    @Autowired
    private UserAccountConverter userAccountConverter;

    public UserAccountServiceImpl() {
        super();
    }

    @WebMethod
    public UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO) {

        super.saveOrUpdate(userAccountDTO);
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
    public List<UserAccount> getAllUser(){
        return  super.findAll(UserAccount.class);
    }

    @WebMethod
    public UserAccountDTO getUserById(Long user_id) {
       UserAccount foundUserAccount = (UserAccount) super.find(UserAccount.class, user_id);

     return userAccountConverter.convertEntityToDto(foundUserAccount);
    }
}
