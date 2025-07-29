package com.vw.employeeportal.controller;

import com.vw.employeeportal.binding.DashboardResponse;
import com.vw.employeeportal.binding.LoginForm;
import com.vw.employeeportal.binding.SignUpForm;
import com.vw.employeeportal.binding.UnlockForm;
import com.vw.employeeportal.entity.UserDetails;
import com.vw.employeeportal.repository.UserDetailsRepo;
import com.vw.employeeportal.service.UserService;
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

    @Autowired
    private UserDetailsRepo userDetailsRepo;


    // this  method will load the signup page
    @GetMapping("/signup")
    public String signUpPage(Model model){ //when my sign up page is loading i need to send the binding object . empty binding object i need to send
        model.addAttribute("user", new SignUpForm()); // We put an empty SignUpForm object into the model so the HTML form knows what to bind its fields to and can show empty fields ready for user input. User is the binding object here.
        return "signup";
    }

    //to submit the form I need to write a method
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

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("login", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("login") LoginForm form, Model model) {
        String status = userService.login(form);

        if ("success".equals(status)) {
            return "redirect:/dashboard";  // redirect to existing GET method
        } else {
            model.addAttribute("errMsg", status);
            return "login";
        }
    }

    @GetMapping("/unlock")
    public String unlockAccount(@RequestParam("email") String email, Model model ){
        // @RequestParam("email") String email — This tells Spring to read a query parameter named email from the URL (e.g., /unlock?email=test@gmail.com) and give it to us as a Java String.
        UnlockForm unlockForm = new UnlockForm(); // This object will hold data to show on the page or receive form data from the user later.
        unlockForm.setEmail(email);
        model.addAttribute("unlock", unlockForm);
        return "unlockAccount";
    }

    @PostMapping("/unlock")
    public String unlockAccount(@ModelAttribute("unlock") UnlockForm form , Model model){ //@ModelAttribute("unlock") automatically binds form fields to UnlockForm object.
        // Check if new and confirm passwords match
        if (!form.getNewPwd().equals(form.getConfirmPwd())){
           model.addAttribute("errMsg","New Password and Confirm Password do not match!");
           model.addAttribute("unlock",form);
           return "unlockAccount";
        }
       boolean status = userService.unlockAccount(form);
        if (status){
            model.addAttribute("succMsg", "Your account unlocked successfully. Please login now.");
            model.addAttribute("login", new LoginForm()); // We create a new LoginForm object so the login page has a fresh form to bind.
            return "login";
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
    public String forgotPwd(@RequestParam("email") String email, Model model){ // i am taking form email from UI by @RequestParam
        System.out.println(email);
        //if the email is coming then i need to write a logic
       boolean status = userService.forgotPwd(email);
       if (status){
           // send the success message
           model.addAttribute("succMsg", "Password sent to your email");
       }else
       {
           //send error msg
           model.addAttribute("errMsg","Invalid email");
       }
        //i need to verify that if
        return "forgotPwd";
    }
}
