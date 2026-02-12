package com.example.monedita.controller;

import com.example.monedita.entity.Permission;
import com.example.monedita.entity.Role;
import com.example.monedita.service.IPermissionService;
import com.example.monedita.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Set<Permission> permissionList = new HashSet<Permission>();
        Permission readPermission;

        for(Permission permission : role.getPermissions()) {
            readPermission = permissionService.findById(permission.getId()).orElse(null);
            if(readPermission != null) {
                permissionList.add(readPermission);
            }
        }
        role.setPermissions(permissionList);
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Role rol = roleService.findById(id).orElse(null);
        if(rol != null) {
            rol = role;
        }

        roleService.update(rol);
        return ResponseEntity.ok(rol);
    }


}
