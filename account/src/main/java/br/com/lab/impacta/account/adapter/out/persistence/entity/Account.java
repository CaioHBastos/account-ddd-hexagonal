package br.com.lab.impacta.account.adapter.out.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person customer;

    private Double balance;
}
