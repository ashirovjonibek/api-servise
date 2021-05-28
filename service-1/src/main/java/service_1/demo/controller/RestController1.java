package service_1.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/service1")
public class RestController1 {
    @GetMapping("/get")
    public HttpEntity<?> get(){
        return ResponseEntity.ok("ulandi");
    }
}
