package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.api.controller.DTOs.AddressDto;
import com.mygitgor.restaurant.model.domain.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AddressDtoMapper{
    Address toDomain(AddressDto dto);
    AddressDto toDto(Address address);
    List<AddressDto> toDto(List<Address> addresses);
}
