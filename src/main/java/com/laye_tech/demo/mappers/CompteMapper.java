package com.laye_tech.demo.mappers;

import com.laye_tech.demo.entities.CompteEntity;
import com.laye_tech.demo.models.CompteDTO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CompteMapper extends EntityMapper<CompteDTO, CompteEntity> {



//    @Mapping(target = "agent", source = "agentId",qualifiedByName ="getAgent")
//    @Mapping(target = "document", source = "documentIds",qualifiedByName ="mapDocument")
    CompteEntity asEntity(CompteDTO dto);


//    @Mapping(source = "agent.id", target = "agentId")
    CompteDTO asDto(CompteEntity compte);


//med("getAgent")
//    default AgentEntity builtAgent(Long agentId) {
//        if(Objects.nonNull(agentId)){
//            return AgentEntity.builder().id(agentId).build();
//        }
//        return null;
//    }
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
