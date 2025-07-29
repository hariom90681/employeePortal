package com.vw.employeeportal.service;

import com.vw.employeeportal.binding.LoginForm;
import com.vw.employeeportal.binding.SignUpForm;
import com.vw.employeeportal.binding.UnlockForm;

public interface UserService {

    public String login(LoginForm form);

    public boolean signUp(SignUpForm form); // this method will take the data from the UI by Controller that's why this method will expect the data in the form of Object
                                            // and now it will call the repository method.

    public boolean unlockAccount(UnlockForm form);

    public boolean forgotPwd(String email);
}
