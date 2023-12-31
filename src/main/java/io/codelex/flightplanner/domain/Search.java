package io.codelex.flightplanner.domain;

import java.util.List;

public class Search {
    private List<Flight> items;
    private Integer page;
    private Integer totalItems;

    public Search(List<Flight> items, Integer page, Integer totalItems) {
        this.items = items;
        this.page = page;
        this.totalItems = totalItems;
    }

    public List<Flight> getItems() {
        return items;
    }

    public void setItems(List<Flight> items) {
        this.items = items;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    @Override
    public String toString() {
        return "Search{" +
                "items=" + items +
                ", page=" + page +
                ", totalItems=" + totalItems +
                '}';
    }
}

