package org.example.HelloCluster.repo;

import org.example.HelloCluster.repo.entity.ConnectionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionHistoryRepository extends JpaRepository<ConnectionHistoryEntity, Long> {
}
