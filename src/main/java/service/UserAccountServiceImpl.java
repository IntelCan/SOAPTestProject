package service;

import model.UserAccount;
import utill.AbstractDao;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class UserAccountServiceImpl extends AbstractDao implements UserAccountService {

    public UserAccountServiceImpl() {
        super();
    }

    @WebMethod
    public UserAccount createUserAccount(UserAccount userAccount) {
        super.saveOrUpdate(userAccount);
     return userAccount;
    }

    @WebMethod
    public boolean deleteUser(long id) {
        try {
           UserAccount foundUserAccount =(UserAccount) super.find(UserAccount.class, id);
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

}
