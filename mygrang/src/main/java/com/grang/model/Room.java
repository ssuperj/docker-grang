package com.grang.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Room", uniqueConstraints = {@UniqueConstraint(name = "constraintName", columnNames = {"sendId", "recvId"})})
@SequenceGenerator(name = "USER_SEQ_GENERATOR4", sequenceName = "USER_SEQ4", allocationSize = 1)
public class Room {

    @Id
    @GeneratedValue(generator = "USER_SEQ_GENERATOR4", strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sendId")
    private User sendUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recvId")
    private User recvUser;

    @CreationTimestamp
    private Timestamp timestamp;
}
