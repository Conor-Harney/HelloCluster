package org.example.HelloCluster.repo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "connection_history")
@Getter
@Setter
@NoArgsConstructor
public class ConnectionHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String host;
    private Date joined;
}
