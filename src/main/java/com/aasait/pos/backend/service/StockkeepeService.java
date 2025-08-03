package com.aasait.pos.backend.service;

import com.aasait.pos.backend.dto.request.AddItemDTO;
import com.aasait.pos.backend.dto.request.AddOrderDTO;
import com.aasait.pos.backend.dto.response.ItemWithBatchesDTO;

import java.util.List;

public interface StockkeepeService {
    void saveItem(AddItemDTO addItemDTO);

    List<String> getItemDetails();

    void saveMultipleOrders(List<AddOrderDTO> orders);

    List<ItemWithBatchesDTO> getItemsWithBatches();
}
