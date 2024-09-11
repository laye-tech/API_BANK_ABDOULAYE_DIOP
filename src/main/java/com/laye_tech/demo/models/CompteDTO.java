package com.laye_tech.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
//exclure les propriétés ayant des valeurs nulles / vides ou par défaut.
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompteDTO implements Serializable {

    private static final long serialVersionUID = -5387827484974552092L;

    @Schema(description = "L'id technique, généré au moment de persister l'objet", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nom;
    private String prenom;
    private String numeroCompte;
    private String numeroTelephone;
    private String adresse;
    private Date dateCreation;



//    private Long fonctionId;
//    private Long uniteId;
//    private Long echelonId;
//    private Long hierarchieId;
//    private Long echelonAttenduId;
//    private Long promotionId;
//    private Long corpsId;
//    private Long gradeId;

}
