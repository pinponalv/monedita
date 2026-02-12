package com.example.monedita.controller;

import com.example.monedita.entity.Role;
import com.example.monedita.entity.UserSec;
import com.example.monedita.service.IRoleService;
import com.example.monedita.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<List<UserSec>> getAllUsers() {
        List<UserSec> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSec> getUserById(@PathVariable Long id) {
        Optional<UserSec> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserSec> createUser(@RequestBody UserSec userSec) {
        Set<Role> roleList = new HashSet<Role>();
        Role readRole;

        //encriptamos password
        userSec.setPassword(userService.encriptPassword(userSec.getPassword()));

        //get permissions
        for (Role role : userSec.getRoleList()) {
            readRole = roleService.findById(role.getId()).orElse(null);
            if(readRole != null){
                roleList.add(readRole);
            }
        }

        if(!roleList.isEmpty()){
            userSec.setRoleList(roleList);

            UserSec newUser = userService.save(userSec);
            return ResponseEntity.ok(newUser);
        }
        return null;
    }


}
