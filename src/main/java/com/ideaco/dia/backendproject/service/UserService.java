package com.ideaco.dia.backendproject.service;

import com.ideaco.dia.backendproject.repository.UserRepository;
import com.ideaco.dia.backendproject.model.UserModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String manipulateString(String value) {
        return "Manipulated String = " + value;
    }

    public boolean createPerson(String name, String password, String email, String phone, String address, String resume, LocalDateTime create_at, LocalDateTime update_at, Boolean status) {
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setPassword(password);
        userModel.setEmail(email);
        userModel.setPhone(phone);
        userModel.setAddress(address);
        userModel.setResume(resume);
        userModel.setCreate_at(create_at);
        userModel.setUpdate_at(update_at);
        userModel.setStatus(status);

        userRepository.save(userModel);
        return true;
    }

    //Tugas Day-2
    public Boolean loginValidated(String password, String email){
        UserModel user = userRepository.getUsers(email, password);
        if (user != null){
            return true;
        }else{
            return false;
        }
    }

    public Boolean signupValidated(String name, String password, String email, String phone, String address, String resume, Boolean status){
        UserModel user = userRepository.findByEmail(email);
        if (user != null){
            return false;
        }else{
            UserModel userModel = new UserModel();
            userModel.setName(name);
            userModel.setPassword(password);
            userModel.setEmail(email);
            userModel.setPhone(phone);
            userModel.setAddress(address);
            userModel.setResume(resume);
            userModel.setStatus(status);
            userRepository.save(userModel);
            return true;
        }
    }

    public UserModel findById(int id) {
        Optional<UserModel> user = userRepository.findById(id);
        if( user != null){
            return user.get();
        }else{
            return null;
        }
    }

    public List<UserModel> getAllPerson() {
        return userRepository.findAll();
    }

}
