package com.vw.employeeportal.service;

import com.vw.employeeportal.binding.LoginForm;
import com.vw.employeeportal.binding.SignUpForm;
import com.vw.employeeportal.binding.UnlockForm;
import com.vw.employeeportal.entity.UserDetails;
import com.vw.employeeportal.repository.UserDetailsRepo;
import com.vw.employeeportal.utils.EmailUtil;
import com.vw.employeeportal.utils.PwdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.vw.employeeportal.utils.PwdUtil.generateRandomPwd;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public boolean signUp(SignUpForm form) {

        //Before going to insert the record first I am checking with the given email id record is available or not
        UserDetails user = userDetailsRepo.findByEmail(form.getEmail());
        if (user != null){
           return false;
        }

        //TODO:  Copy data from binding object to entity object
        UserDetails entity = new UserDetails();
        BeanUtils.copyProperties(form, entity);

        //TODO:  Generate random password and set to object  bcz it will not come from the UI
        String tempPassword = PwdUtil.generateRandomPwd();
        entity.setPassword(tempPassword);

        //TODO:  Set account status as LOCKED
        entity.setAccStatus("LOCKED");

        //TODO:  Insert recode into the table
        userDetailsRepo.save(entity);

        //TODO:  Send email to user to unlock the account
        String to = form.getEmail(); // bcz we already know, so we will take this to from the form
        String subject = "Unlock Your Account | VW ";

         /* String body = "<h1> Use below temporary pwd to unlock your account </h1> ";
         we will not go with String bcz it is an immutable object whenever you will change it will create a new object
*/
        StringBuffer body = new StringBuffer("");
        body.append("<h1> Use below temporary pwd to unlock your account </h1>");
        body.append("Temporary password : "+ tempPassword);
        body.append("<br/>");
        body.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\">Click here to unlock your account</a>");

        emailUtil.sendEmail(to, subject, body.toString());
        return true;
    }

    @Override
    public boolean unlockAccount(UnlockForm form) {
        UserDetails entity = userDetailsRepo.findByEmail(form.getEmail());
        if (entity != null && entity.getPassword().equals(form.getTempPwd())) {
            entity.setPassword(form.getNewPwd());
            entity.setAccStatus("UNLOCKED");
            userDetailsRepo.save(entity);
            return true;
        }
        return false;
    }


    @Override
    public String login(LoginForm form) {
       UserDetails entity = userDetailsRepo.findByEmail(form.getEmail());

       if (entity == null){
          return  "Invalid Credentials";
       }
       if (!entity.getPassword().equals(form.getPassword())){
           return  "Invalid Credentials";
       }
       if ("LOCKED".equals(entity.getAccStatus())){
           return "Your account is locked!";
       }
        return "success";
    }


    @Override
    public boolean forgotPwd(String email) {
        //Check record present in db with given mail
       UserDetails entity = userDetailsRepo.findByEmail(email); // it will retrieve the code based on the given mail id
       //if record not available send error msg
        if (entity == null){
            return false;
        }
       //if record is available send psd to email and send success msg
        String subject = "Recover Password";
        String body = "Your Password : " + entity.getPassword();

        emailUtil.sendEmail(email, subject, body);
        return true;
    }
}
