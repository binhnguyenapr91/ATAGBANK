package com.atag.atagbank.controller;

import com.atag.atagbank.model.MyUser;
import com.atag.atagbank.model.Role;
import com.atag.atagbank.service.account.IAccountService;
import com.atag.atagbank.service.user.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IAccountService accountService;

    @Autowired
    MyUserService myUserService;

    @GetMapping("/makeDeposit")
    public ModelAndView showMakeDepositForm() {
        MyUser currentUser = getUserFromPrincipal();
        return new ModelAndView("personal/makeDeposit", "currentUser", currentUser);
    }

    @GetMapping("/profile")
    public ModelAndView getPersonalProfile(HttpSession session) {
        MyUser currentUser = getUserFromPrincipal();
        session.setAttribute("currentUserName", currentUser.getName());
        return new ModelAndView("personal/profile", "currentUser", currentUser);
    }

    @PostMapping("/profile")
    public ModelAndView updateProfile(@Validated @ModelAttribute("currentUser") MyUser currentUser, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("personal/profile");
            return modelAndView;
        }
        MyUser selectedUser = myUserService.findById(currentUser.getId());
        Role role = selectedUser.getRole();
        currentUser.setRole(role);
        currentUser.setAccount(selectedUser.getAccount());
        myUserService.save(currentUser);
        ModelAndView modelAndView = new ModelAndView("personal/profile");
        modelAndView.addObject("currentUser", currentUser);
        modelAndView.addObject("message", "The information has been updated!");
        return modelAndView;
    }

    @GetMapping("/changePassword")
    public ModelAndView showChangePasswordForm() {
        MyUser currentUser = getUserFromPrincipal();
        return new ModelAndView("personal/changePassword", "currentUser", currentUser);
    }

    private MyUser getUserFromPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return myUserService.findByUserName(username);
    }

    @PostMapping("/changePassword")
    public ModelAndView changePassword(@Validated @ModelAttribute("currentUser") MyUser currentUser, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("personal/changePassword");
            return modelAndView;
        }

        if (!currentUser.getPassword().equals(currentUser.getConfirmPassword())){
            ModelAndView modelAndView = new ModelAndView("personal/changePassword","validatedPass","Not match Password");
            return modelAndView;
        }
        MyUser selectedUser = myUserService.findById(currentUser.getId());
        Role role = selectedUser.getRole();
        currentUser.setRole(role);
        currentUser.setAccount(selectedUser.getAccount());
        myUserService.save(currentUser);
        ModelAndView modelAndView = new ModelAndView("personal/changePassword");
        modelAndView.addObject("currentUser", currentUser);
        modelAndView.addObject("message", "The information has been updated!");
        return modelAndView;
    }
}
