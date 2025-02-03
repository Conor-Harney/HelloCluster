package org.example.HelloCluster.service;

import org.example.HelloCluster.repo.entity.ConnectionHistoryEntity;

import java.util.List;

public interface ConnectionHistoryService {
    void add();

    List<ConnectionHistoryEntity> getAll();
}
