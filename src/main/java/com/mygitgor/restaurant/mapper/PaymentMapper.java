package com.mygitgor.restaurant.mapper;

import com.mygitgor.restaurant.infrastructure.database.entity.PaymentEntity;
import com.mygitgor.restaurant.model.domain.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends ConfigMapper<Payment, PaymentEntity> {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
}
