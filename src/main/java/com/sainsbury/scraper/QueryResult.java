package com.sainsbury.scraper;

import java.util.List;

public class QueryResult {
    public List<Item> getResultados() {
        return resultados;
    }

    public void setResultados(List<Item> resultados) {
        this.resultados = resultados;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    private List<Item> resultados;
    private Total total;
}
