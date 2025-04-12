package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.domain.Address;
import com.mygitgor.restaurant.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressDto addressDto);
    AddressDto toAddressDto(Address address);
}