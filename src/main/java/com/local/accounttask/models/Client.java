package com.local.accounttask.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String clientLogin;
    private int numberOfAccount;
    private String hashPassword;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;
    @OneToMany(mappedBy = "client")
    private List<Account> accounts = new ArrayList<>();
    @OneToMany(mappedBy = "client")
    List<Token> tokens = new ArrayList<>();

}
