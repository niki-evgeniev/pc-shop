package com.example.pcproject.controller;

import com.example.pcproject.Service.TestRestService;
import com.example.pcproject.models.DTO.GetIp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/viewIp")
public class TestRest {

    private final TestRestService testRestService;

    public TestRest(TestRestService testRestService) {
        this.testRestService = testRestService;

    }

    @GetMapping()
    public ResponseEntity<List<GetIp>> getIpAddress(){
        List<GetIp> allIp = testRestService.getAllIp();


        System.out.println();
        return ResponseEntity.ok(allIp);
    }

}
