package servise_2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import servise_2.demo.dto.ClientDto;
import servise_2.demo.service.SurveyService;

@RestController
@RequestMapping("api/service2")
public class RestController2 {
    @Autowired
    SurveyService ss;

    @PostMapping("/addSurvey")
    public HttpEntity<?> save(@RequestBody ClientDto dto){
        String save = ss.save(dto);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(ss.findAll());
    }
}
