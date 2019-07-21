package com.fanghr.springdemo.controller;

import com.fanghr.springdemo.controller.request.UserRequest;
import com.fanghr.springdemo.model.User;
import com.fanghr.springdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User : fanghr
 * Date : 2019/7/20
 * Time : 8:34 PM
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/get/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "/get", params = "name")
    public List<User> getUsersByName(@RequestParam String name) {
        return userService.getUsersByName(name);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public int addUser(@Valid UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .build();
        return userService.addUser(user);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public int addUser(@Valid @RequestBody UserRequest userRequest, BindingResult result) {
        if (result.hasErrors()) {
            log.error("Bind Errors:{}",result );
            return 0;
        }
        User user = User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .build();
        return userService.addUser(user);
    }

    @PostMapping(value = "/batchAdd", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<User> batchAddUser(@RequestParam MultipartFile file) {
        List<User> users = new ArrayList<>();
        if (!file.isEmpty()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                String str = null;
                while ( (str = reader.readLine()) != null) {
                    String[] arr = str.split(" ");
                    User user = User.builder().name(arr[0]).age(arr[1]).build();
                    userService.addUser(user);
                    users.add(user);
                }
            } catch (IOException e) {
                log.error("file io exception: {}", e);
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }

        return users;
    }
}
