package com.codehustle.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "user_age",nullable = false)
    private Integer age;

    @Column(name = "user_phone",nullable = false)
    private String userPhone;
}
