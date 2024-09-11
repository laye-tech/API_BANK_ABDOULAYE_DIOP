package com.laye_tech.demo.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import static javax.persistence.TemporalType.TIMESTAMP;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name ="balance")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceEntity implements Serializable {
    private static final long serialVersionUID = -5387827484974552092L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "balance_id")
    private Long id;

    @Column(name = "solde")
    private Double solde;

    @Temporal(TemporalType.DATE)
    private Date dateTransaction;

    @ManyToOne
    private CompteEntity compte;


}
