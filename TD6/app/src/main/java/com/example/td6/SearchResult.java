package com.example.td6;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {

    private int total_count;
    private boolean incomplete_results;
    @SerializedName("items")
    private List<Repo> repos;
    public List<Repo> getRepos() {
        return repos;
    }

}
