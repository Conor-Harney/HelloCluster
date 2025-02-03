package org.example.HelloCluster.controller;

import org.example.HelloCluster.repo.entity.ConnectionHistoryEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface ConnectionController {
    @GetMapping("/connections")
    @ResponseBody
    ResponseEntity<List<ConnectionHistoryEntity>> getConnections();

}
