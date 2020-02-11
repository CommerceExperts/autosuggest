package com.search.suggestion.data;

import com.search.suggestion.interfaces.RawRequestInterface;

import java.util.HashMap;
import java.util.Map;

public class RawSeachRequest implements RawRequestInterface{
    private String query;
    private Map<String,Integer> filter = new HashMap<String,Integer>();
    private Map<String,Bucket> bucket = new HashMap<>();
    private int limit = 0;

    public Map<String, Bucket> getBucket() {
        return bucket;
    }

    public void setBucket(Map<String, Bucket> bucket) {
        this.bucket = bucket;
    }

    private Map<String,Integer> sort = new HashMap<String,Integer>();
    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, Integer> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Integer> filter) {
        this.filter = filter;
    }
}
