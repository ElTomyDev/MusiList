package com.heavydelay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.model.dto.role.RoleUpdateDto;
import com.heavydelay.model.dto.role.RoleReturnDto;
import com.heavydelay.model.payload.MessageResponse;
import com.heavydelay.service.IRole;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRole roleService;


    @PostMapping("/add")
    public ResponseEntity<?> addNewRole(@RequestBody @Valid RoleUpdateDto newRole){
        RoleReturnDto role = roleService.addNewRole(newRole);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Role created successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(role)
            .build(), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteRoleById(@PathVariable Integer id){
        roleService.deleteRoleById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Role deleted successfully")
            .status(HttpStatus.OK.value())
            .objectResponse("Role with ID '" + id + "'' delete")
            .build(), HttpStatus.OK
        );
    }

    @PutMapping("{id}/update-name")
    public ResponseEntity<?> changeRoleNameById(@PathVariable Integer id, @RequestBody @Valid RoleUpdateDto newRoleName) {
        RoleReturnDto role = roleService.changeRoleNameById(id, newRoleName);
        
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Role name updated successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(role)
            .build(), HttpStatus.CREATED
        );
    }

    @GetMapping("/roles")
    public ResponseEntity<?> showAllRoles() {
        List<RoleReturnDto> roles = roleService.showAllRoles();
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("All roles obtained correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(roles)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showRoleById(@PathVariable Integer id) {
        RoleReturnDto role = roleService.showRoleById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Role successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(role)
            .build(), HttpStatus.OK
        );
    }
    
}
