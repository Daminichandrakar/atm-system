package com.bridgelabz.atmsystem.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Purpose : To implement atm-System program
 *
 * @author : DAMINI CHANDRAKAR
 * @since : 03-12-2021
 */
@Entity
@Table(name = "atm")
@Data
public class AtmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String cardNumber;
    private String cardName;
    private String cvv;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
