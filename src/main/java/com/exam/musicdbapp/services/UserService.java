package com.exam.musicdbapp.services;

import com.exam.musicdbapp.model.service.UserServiceModel;

public interface UserService {
    void createUser(UserServiceModel userServiceModel);
    UserServiceModel findByUsernameOrEmail(String username, String email);
    UserServiceModel findByUsernameAndPassword(String username, String password);
    void saveLoggedUser(UserServiceModel userServiceModel);
    UserServiceModel getLoggedUser();
}
