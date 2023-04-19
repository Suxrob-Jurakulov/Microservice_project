package com.company.controller;

import com.company.dto.UserDTO;
import com.company.entity.UserEntity;
import com.company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestParam("name") String username,
                                         @RequestBody UserDTO userDTO) {
        String response = userService.update(username, userDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get")
    public ResponseEntity<UserDTO> get(@RequestHeader("name") String username) {
        UserDTO response = userService.findUser(username);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("name") String username){
        String response = userService.delete(username);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO dto = userService.addUser(userDTO);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/find_by_username/{username}")
    public ResponseEntity<UserEntity> findByUsername(@PathVariable("username") String username){
        UserEntity response = userService.getEntity(username);
        return ResponseEntity.ok().body(response);
    }
}
