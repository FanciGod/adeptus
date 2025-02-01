package com.adeptus.adeptusfe.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    private List<T> content;
    private int totalElements;
    private int totalPages;
    private boolean last;
    private int size;
    private int number;
    private boolean first;
    private boolean empty;
    private int numberOfElements;
    private Sort sort;
    private Pageable pageable;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Pageable {
    private int pageNumber;
    private int pageSize;
    private Sort sort;
    private int offset;
    private boolean paged;
    private boolean unpaged;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Sort {
    private boolean empty;
    private boolean sorted;
    private boolean unsorted;

    // Getter và Setter
}
