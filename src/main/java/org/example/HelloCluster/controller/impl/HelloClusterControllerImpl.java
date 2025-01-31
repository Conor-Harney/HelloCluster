package org.example.HelloCluster.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.HelloCluster.controller.HelloClusterController;
import org.example.HelloCluster.data.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloClusterControllerImpl implements HelloClusterController {

    @Override
    public ResponseEntity<GenericResponse> helloCluster(){
        return new ResponseEntity<>(new GenericResponse("Hello Cluster."), HttpStatus.OK);
    }
}
