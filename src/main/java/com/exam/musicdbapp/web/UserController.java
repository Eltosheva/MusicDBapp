package com.exam.musicdbapp.web;

import com.exam.musicdbapp.bindingModels.UserLoginBindingModel;
import com.exam.musicdbapp.bindingModels.UserRegisterBindingModel;
import com.exam.musicdbapp.model.service.UserServiceModel;
import com.exam.musicdbapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null){
            return "/";
        }
        if (!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("isWrongConfirmPassword", false);
        }
        if (!model.containsAttribute("userNameOrEmailExist")) {
            model.addAttribute("userNameOrEmailExist", false);
        }
        return "/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getConfirmPassword().equals(userRegisterBindingModel.getPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",
                    userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("isWrongConfirmPassword",
                    userRegisterBindingModel.getConfirmPassword().equals(userRegisterBindingModel.getPassword()));
            redirectAttributes.addFlashAttribute("userNameOrEmailExist", false);

            return "redirect:register";
        }

        if (userService.findByUsernameOrEmail(userRegisterBindingModel.getUsername()
                , userRegisterBindingModel.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("userNameOrEmailExist", true);
            return "redirect:register";
        }
        userService.createUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("username") != null){
            return "redirect:/";
        }
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        if (!model.containsAttribute("wrongUsernameOrPassword")) {
            model.addAttribute("wrongUsernameOrPassword", false);
        }
        return "/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                            bindingResult);
            redirectAttributes.addFlashAttribute("wrongUsernameOrPassword", false);
            return "redirect:login";
        }

        UserServiceModel userServiceModel = userService.findByUsernameAndPassword(userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword());

        if (userServiceModel == null) {
            redirectAttributes.addFlashAttribute("wrongUsernameOrPassword", true);
            return "redirect:login";
        }
        userService.saveLoggedUser(userServiceModel);
        httpSession.setAttribute("user", userServiceModel.getUsername());
        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        if(httpSession.getAttribute("user") == null) {
            return "redirect:login";
        }
        httpSession.invalidate();
        return "redirect:/";
    }
}
