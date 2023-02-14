package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO ;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

// AccountDAO constructor for test cases - mock
public AccountService(AccountDAO accountDAO) { this.accountDAO = accountDAO;}

    /*
    * User Registration
    The registration will be successful iff the username is not blank, the password is at least 4 characters long, 
    and an Account with that username does not already exist.
    */
    public Account addAccount(Account account) {
        if(account.password.length() >= 4 && account.username != null && !account.username.isBlank() 
            && accountDAO.getAccountByUsername(account.getUsername()) == null) 
             {return accountDAO.insertAccount(account);} else {return null;}

    }
}
/* 
Create New Message - Logic part (for, if ,else, testing value ranges done in Service class)
The creation of the message will be successful iff the message_text is not blank, is under 255 characters, 
and posted_by refers to a real, existing user.
*/
