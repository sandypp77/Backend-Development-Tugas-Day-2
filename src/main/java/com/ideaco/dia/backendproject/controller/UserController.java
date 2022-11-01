package com.ideaco.dia.backendproject.controller;

import com.ideaco.dia.backendproject.model.UserModel;
import com.ideaco.dia.backendproject.service.UserService;
import com.ideaco.dia.backendproject.utils.DataResponse;
import com.ideaco.dia.backendproject.utils.HandlerResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/first")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return userService.manipulateString("Welcome");
    }

    @PostMapping("/person")
    public String createPerson(@RequestParam("name") String name,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               @RequestParam("address") String address,
                               @RequestParam("resume") String resume,
                               @RequestParam("create_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime create_at,
                               @RequestParam("update_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime update_at,
                               @RequestParam("status") Boolean status) {
        if (userService.createPerson(name, password, email, phone, address, resume, create_at, update_at, status)) {
            return "Success";
        } else {
            return "Failed";
        }

    }

    //Tugas Day-2
    @PostMapping("/login")
    public Boolean loginUser(@RequestParam("password") String password,
                             @RequestParam("email") String email) {
        Boolean loginUser = userService.loginValidated(password, email);
        return loginUser;
    }

    @PostMapping("/signup")
    public String createUser(@RequestParam("name") String name,
                             @RequestParam("password") String password,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             @RequestParam("address") String address,
                             @RequestParam("resume") String resume,
                             @RequestParam("status") Boolean status) {
        if (userService.signupValidated(name, password, email, phone, address, resume, status)) {
            return "Data berhasil disimpan";
        } else {
            return "Email atau name sudah terdaftar didatabase, Gunakan email atau name lain";
        }
    }

    @GetMapping("/userById/{id}")
    public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
        UserModel user = userService.findById(id);
        if(user != null){
            DataResponse<UserModel> data = new DataResponse<>();
            data.setData(user);
            HandlerResponse.responseSuccessWithData(response, data);
        }else{

        }
    }

    @GetMapping("/allUser")
    public List<UserModel> getAllPerson() {
        return userService.getAllPerson();
    }


}
