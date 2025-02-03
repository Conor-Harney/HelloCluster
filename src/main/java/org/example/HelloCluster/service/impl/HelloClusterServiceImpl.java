package org.example.HelloCluster.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.HelloCluster.repo.entity.ConnectionHistoryEntity;
import org.example.HelloCluster.service.ConnectionHistoryService;
import org.example.HelloCluster.service.HelloClusterService;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HelloClusterServiceImpl implements HelloClusterService {
    private final ConnectionHistoryService connectionHistoryService;

    @Override
    public String getHelloClusterMessage() {
        List<ConnectionHistoryEntity> connectedHosts = connectionHistoryService.getAll();
        String myAddress;
        try {
            myAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        String finalMyAddress = myAddress;
        List<String> peers = connectedHosts.stream().map(ConnectionHistoryEntity::getHost).filter(host -> !host.equals(finalMyAddress)).toList();
        return String.format("Hello from %s. My friends are %s", myAddress, peers);
    }
}
