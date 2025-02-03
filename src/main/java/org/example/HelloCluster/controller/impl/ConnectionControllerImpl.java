package org.example.HelloCluster.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.HelloCluster.controller.ConnectionController;
import org.example.HelloCluster.repo.entity.ConnectionHistoryEntity;
import org.example.HelloCluster.service.ConnectionHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ConnectionControllerImpl implements ConnectionController {
    private final ConnectionHistoryService service;

    @Override
    public ResponseEntity<List<ConnectionHistoryEntity>> getConnections() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
