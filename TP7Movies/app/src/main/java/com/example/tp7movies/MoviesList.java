package com.example.tp7movies;

import java.util.List;

public class MoviesList {
    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        String result="";
        for (Movie v:
                results) {

            result+=" " + v.getTitle();

        }
        return result;
    }
}
