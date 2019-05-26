package com.sainsbury.scraper;

public class Item {
    private String title;
    private float priceUnit;
    private int energy;
    private String description;

    public Item() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(float priceUnit) {
        this.priceUnit = priceUnit;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        return "Product:{" +
                "title=" + title + '\'' +
                "PricePerUnit=" + priceUnit +
                "Calories=" + energy +
                "Description=" + description + '\'' +
                "}";
    }
}
