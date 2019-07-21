package com.fanghr.springdemo.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User : fanghr
 * Date : 2019/7/20
 * Time : 8:37 PM
 */
@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  implements Serializable {

    private long id;
    private String name;
    private String age;

}
