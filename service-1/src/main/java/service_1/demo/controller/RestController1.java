package service_1.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service_1.demo.dto.UserDto;
import service_1.demo.service.UserService;

@RestController
@RequestMapping("api/service1")
public class RestController1 {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN'})")
    @GetMapping("/getUser")
    public HttpEntity<?> get(@RequestParam String passport) {
        UserDto userDto = userService.findByPassportNum(passport);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/saveUser")
    public HttpEntity<?> saveUser(@RequestBody UserDto dto){
        userService.save(dto);
        return ResponseEntity.ok("Saqlandi");
    }
}
