package com.prm392.dacare.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> {
    private int page;
    private int pageSize;
    private int totalPages;
    private int totalItem;
    private List<T> data;
}
