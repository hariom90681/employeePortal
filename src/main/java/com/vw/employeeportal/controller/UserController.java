package com.vw.employeeportal.controller;

import com.vw.employeeportal.binding.DashboardResponse;
import com.vw.employeeportal.binding.LoginForm;
import com.vw.employeeportal.binding.SignUpForm;
import com.vw.employeeportal.binding.UnlockForm;
import com.vw.employeeportal.entity.UserDetails;
import com.vw.employeeportal.exception.LoginFailedException;
import com.vw.employeeportal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession; // Import HttpSession
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private UserService userService;


    // This method now handles both the root ("/") and "/login" URLs
    @GetMapping({"/", "/login"})
    public String loginPage(Model model) {
        model.addAttribute("login", new LoginForm());
        // It now correctly returns "index" to match your file name
        return "index";
    }

 /*   @PostMapping("/login")
    public String handleLogin(@ModelAttribute("login") LoginForm form, Model model, HttpSession session) {
        UserDetails user = userService.login(form);

        if (user != null) {
            // Store user ID and account type in session
            session.setAttribute("userId", user.getUserId());
            return "redirect:/dashboard";
        } else {
            model.addAttribute("errMsg", "Invalid Credentials");
            // On failure, it re-renders the index page with the error
            return "index";
        }
    }*/

    @PostMapping("/login")
    public String handleLogin(LoginForm form, Model model, HttpSession session) {

        try {
            // 1. Call the service to perform the login logic.
            UserDetails user = userService.login(form);
            //create session and store user data in session
            session.setAttribute("userId", user.getUserId());

            // 4. Redirect the user to their main page.
            return "redirect:/dashboard";

        } catch (LoginFailedException e) {

            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
    @GetMapping("/signup")
    public String signUpPage(Model model){
        model.addAttribute("user", new SignUpForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model){
        boolean status = userService.signUp(form);
        if (status){
            model.addAttribute("succMsg","Account Created, Check Your Email");
        } else {
            model.addAttribute("errMsg","Choose Unique Email");
        }
        return "signup";
    }

    @GetMapping("/unlock")
    public String unlockAccount(@RequestParam("email") String email, Model model ){
        UnlockForm unlockForm = new UnlockForm();
        unlockForm.setEmail(email);
        model.addAttribute("unlock", unlockForm);
        return "unlockAccount";
    }

    @PostMapping("/unlock")
    public String unlockAccount(@ModelAttribute("unlock") UnlockForm form , Model model){
        if (!form.getNewPwd().equals(form.getConfirmPwd())){
            model.addAttribute("errMsg","New Password and Confirm Password do not match!");
            model.addAttribute("unlock",form);
            return "unlockAccount";
        }
        boolean status = userService.unlockAccount(form);
        if (status){
            model.addAttribute("succMsg", "Your account unlocked successfully. Please login now.");
            // Return to the main login page
            model.addAttribute("login", new LoginForm());
            return "index";
        }else {
            model.addAttribute("errMsg","Invalid temporary password!");
            model.addAttribute("unlock", form);
            return "unlockAccount";
        }
    }

    @GetMapping("/forgotPwd")
    public String forgotPwdPage(){
        return "forgotPwd";
    }

    @PostMapping("/forgotPwd")
    public String forgotPwd(@RequestParam("email") String email, Model model){
        boolean status = userService.forgotPwd(email);
        if (status){
            model.addAttribute("succMsg", "Password sent to your email");
        }else {
            model.addAttribute("errMsg","Invalid email");
        }
        return "forgotPwd";
    }
}