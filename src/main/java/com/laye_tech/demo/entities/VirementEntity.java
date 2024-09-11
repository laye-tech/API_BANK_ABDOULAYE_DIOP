package com.laye_tech.demo.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;



@Table(name ="virement")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirementEntity implements Serializable {
    private static final long serialVersionUID = -5387827484974552092L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "virement_id")
    private Long id;

    @Column(name = "solde")
    private Double solde;

    @Column(name = "numeroCompteVirement")
    private String numeroCompteVirement;

    @Temporal(TemporalType.DATE)
    private Date dateVirement;

    @ManyToOne
    private CompteEntity compte;


}
