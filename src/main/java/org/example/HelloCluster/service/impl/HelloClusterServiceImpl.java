package org.example.HelloCluster.service.impl;

import org.example.HelloCluster.service.HelloClusterService;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class HelloClusterServiceImpl implements HelloClusterService {
    @Override
    public String getHelloClusterMessage() {
        String returnMessage = "Hello Cluster";

        try {
            InetAddress localHost = InetAddress.getLocalHost();
            returnMessage = String.format("%s from %s", returnMessage, localHost.getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return returnMessage;
    }
}
