package com.ckaradimitriou.dietplanapp.data.model;

import java.util.List;

public class RecipeResponse {

    private int from;
    private int to;
    private int count;
    private List<RecipeItem> hits;

    public RecipeResponse(int from, int to, int count, List<RecipeItem> hits) {
        this.from = from;
        this.to = to;
        this.count = count;
        this.hits = hits;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RecipeItem> getHits() {
        return hits;
    }

    public void setHits(List<RecipeItem> hits) {
        this.hits = hits;
    }
}
