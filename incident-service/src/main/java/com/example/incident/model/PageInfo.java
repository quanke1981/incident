package com.example.incident.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.data.domain.Page;

import java.util.List;

public class PageInfo<R> {
    private List<R> data;
    private long totalElement;
    private int pageNumber;
    private int pageSize;

    @JsonCreator
    public PageInfo() {

    }

    public PageInfo(Page<R> page) {
        this.data = page.getContent();
        this.totalElement = page.getTotalElements();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();

    }

    public List<R> getData() {
        return data;
    }

    public void setData(List<R> data) {
        this.data = data;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @JsonGetter
    public int getTotalPages() {
        return pageSize == 0 ? 1 : (int) Math.ceil((double) totalElement / pageSize);
    }
}
