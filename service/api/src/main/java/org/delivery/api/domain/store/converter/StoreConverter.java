package org.delivery.api.domain.store.converter;

import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;

import java.math.BigDecimal;
import java.util.Optional;

@Converter
public class StoreConverter {

    public StoreEntity toEntity(StoreRegisterRequest request){
        return  Optional.ofNullable(request)
                .map(it -> {
                    return StoreEntity.builder()
                            .name(it.getName())
                            .address(it.getAddress())
                            .category(it.getStoreCategory())
                            .minimumAmount(it.getMinimumAmount())
                            .minimumDeliveryAmount(it.getMinimumDeliveryAmount())
                            .thumbnailUrl(it.getThumbnailUrl())
                            .phoneNumber(it.getPhoneNumber())
                            .build();
                })
                .orElseThrow(
                        () -> new ApiException(ErrorCode.NULL_POINT)
                );
    }

    public StoreResponse toResponse(StoreEntity storeEntity){
        return Optional.ofNullable(storeEntity)
                .map(it -> {
                    return StoreResponse.builder()
                            .id(it.getId())
                            .name(it.getName())
                            .address(it.getAddress())
                            .status(it.getStatus())
                            .category(it.getCategory())
                            .star(it.getStar())
                            .thumbnailUrl(it.getThumbnailUrl())
                            .minimumAmount(it.getMinimumAmount())
                            .minimumDeliveryAmount(it.getMinimumDeliveryAmount())
                            .phoneNumber(it.getPhoneNumber())
                            .build();
                })
                .orElseThrow(
                        () -> new ApiException(ErrorCode.NULL_POINT)
                );
    }
}