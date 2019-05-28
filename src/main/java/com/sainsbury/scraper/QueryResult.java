package com.sainsbury.scraper;
import java.util.List;

public class QueryResult {

    public QueryResult(){

    }
    public List<Item> getResultados() {
        return results;
    }

    public void setResults(List<Item> results) {
        this.results = results;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    private List<Item> results;
    private Total total;
}
