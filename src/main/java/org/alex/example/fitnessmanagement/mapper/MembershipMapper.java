package org.alex.example.fitnessmanagement.mapper;

import org.alex.example.fitnessmanagement.dto.CreateMembershipDto;
import org.alex.example.fitnessmanagement.dto.MembershipResponseDto;
import org.alex.example.fitnessmanagement.entity.Membership;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDate.class})
public interface MembershipMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "purchaseDate", expression = "java(LocalDate.now())")
    @Mapping(target = "startDate", expression = "java(LocalDate.now())")
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "remainingVisits", ignore = true)
    Membership toEntity(CreateMembershipDto createMembershipDto);

    List<Membership> toEntityList(List<CreateMembershipDto> createMembershipDtoList);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(target = "clientFullName", expression = "java(membership.getClient().getFirstName() + \" \" + membership.getClient().getLastName())")
    MembershipResponseDto toDto(Membership membership);

    List<MembershipResponseDto> toDtoList(List<Membership> membershipList);

}
