package com.enigma.enigmaboot.utils;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponseWrapper<T> {
    private List<T> content;
    private Long count;
    private Integer totalPages;
    private Integer page;
    private Integer size;

    public PageResponseWrapper(Page<T> page) {
        this.content = page.getContent();
        this.count = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber();
        this.size = page.getSize();
    }
}
