package com.sainsbury.scraper;

public class Total {
    public float getGross() {
        return gross;
    }

    public void setGross(float gross) {
        this.gross = gross;
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    private float gross;
    private float vat;
}


