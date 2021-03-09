package com.exam.musicdbapp.services.Impl;

import com.exam.musicdbapp.model.entities.UserEntity;
import com.exam.musicdbapp.model.service.UserServiceModel;
import com.exam.musicdbapp.repositories.UserRepository;
import com.exam.musicdbapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserServiceModel userServiceModel;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createUser(UserServiceModel userServiceModel) {
        userRepository.save(modelMapper.map(userServiceModel, UserEntity.class));
    }

    @Override
    public UserServiceModel findByUsernameOrEmail(String username, String email) {
       return userRepository.findByUsernameOrEmail(username, email)
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void saveLoggedUser(UserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }

    @Override
    public UserServiceModel getLoggedUser() {
        return userServiceModel;
    }
}
