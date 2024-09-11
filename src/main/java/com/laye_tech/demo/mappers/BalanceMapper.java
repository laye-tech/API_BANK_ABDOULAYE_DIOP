package com.laye_tech.demo.mappers;

import com.laye_tech.demo.entities.BalanceEntity;
import com.laye_tech.demo.entities.CompteEntity;
import com.laye_tech.demo.models.BalanceDTO;
import com.laye_tech.demo.models.CompteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Objects;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BalanceMapper extends EntityMapper<BalanceDTO, BalanceEntity> {



       @Mapping(target = "compte", source = "compteId",qualifiedByName ="getCompte")
//    @Mapping(target = "document", source = "documentIds",qualifiedByName ="mapDocument")
       BalanceEntity asEntity(BalanceDTO dto);


//    @Mapping(source = "agent.id", target = "agentId")
//@Mapping(source = "compte", target = "compteId")
BalanceDTO asDto(BalanceEntity balance);


@Named("getCompte")
    default CompteEntity builtCompte(Long compteId) {
        if(Objects.nonNull(compteId)){
            return CompteEntity.builder().id(compteId).build();
        }
        return null;
    }
//
//    @Named("getTypeSexe")
//    default SexType getTypeSexe(String stringType) {
//        if(StringUtils.isNotEmpty(stringType)){
//            return SexType.valueOf(stringType);
//        }
//        return null;
//    }

//    @Named("getFamilyRelashion")
//    default FamilyRelashionshipType getTypeFamilyRelashion(String stringType) {
//        if(StringUtils.isNotEmpty(stringType)){
//            return FamilyRelashionshipType.valueOf(stringType);
//        }
//        return null;
//    }


}
