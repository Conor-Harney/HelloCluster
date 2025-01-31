package org.example.HelloCluster.controller;

import org.example.HelloCluster.data.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


public interface HelloClusterController {
    @GetMapping("/hello")
    @ResponseBody ResponseEntity<GenericResponse> helloCluster();
}
