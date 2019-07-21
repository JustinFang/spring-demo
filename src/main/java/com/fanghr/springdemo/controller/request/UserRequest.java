package com.fanghr.springdemo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * Created by IntelliJ IDEA.
 * User : fanghr
 * Date : 2019/7/20
 * Time : 9:04 PM
 */
@Setter
@Getter
@ToString
public class UserRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String age;
}
