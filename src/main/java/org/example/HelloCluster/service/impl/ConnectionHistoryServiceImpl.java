package org.example.HelloCluster.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.HelloCluster.repo.ConnectionHistoryRepository;
import org.example.HelloCluster.repo.entity.ConnectionHistoryEntity;
import org.example.HelloCluster.service.ConnectionHistoryService;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConnectionHistoryServiceImpl implements ConnectionHistoryService {
    private final ConnectionHistoryRepository repository;

    @PostConstruct
    public void add() {
        ConnectionHistoryEntity connectionHistory = new ConnectionHistoryEntity();
        try {
            connectionHistory.setHost(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        connectionHistory.setJoined(new Date());
        repository.save(connectionHistory);
    }

    @Override
    public List<ConnectionHistoryEntity> getAll() {
        return repository.findAll();
    }
}
