package org.delivery.api.domain.store.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreCategory;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreBusiness {

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    public StoreResponse register(StoreRegisterRequest request){
        var storeEntity = storeConverter.toEntity(request);
        var newStoreEntity = storeService.register(storeEntity);
        var response = storeConverter.toResponse(newStoreEntity);
        return response;
    }

    public List<StoreResponse> searchCategory(StoreCategory storeCategory){
        var storeEntityList = storeService.searchByCategory(storeCategory);
        var response = storeEntityList.stream()
                .map(storeConverter::toResponse)
                .collect(Collectors.toList());
        return response;
    }
}
