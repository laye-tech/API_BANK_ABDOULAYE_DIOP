package com.laye_tech.demo.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


@Table(name ="compte")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompteEntity implements Serializable {
    private static final long serialVersionUID = -5387827484974552092L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "compte_id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;
    @Column(name = "numero_de_compte")
    private String numeroCompte;

    @Column(name = "numero_telephone")
    private String numeroTelephone;

    @Column(name = "adresse_domicile")
    private String adresse;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

}
