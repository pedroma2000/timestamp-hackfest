package org.drools.hackfest2022.model;

public class Item {
    private String barcode;
    private String shortdesc;
    private ItemCategory category;
    private double price;
    private double weight;
    private String sn;
    
    public Item() {
        // empty constructor.
    }
    public Item(String barcode, String shortdesc, ItemCategory category, double price, double weight) {
        this(barcode, shortdesc, category, price, weight, null);
    }
    public Item(String barcode, String shortdesc, ItemCategory category, double price, double weight, String sn) {
        this.barcode = barcode;
        this.shortdesc = shortdesc;
        this.category = category;
        this.price = price;
        this.weight = weight;
        this.sn = sn;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getShortdesc() {
        return shortdesc;
    }
    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }
    public ItemCategory getCategory() {
        return category;
    }
    public void setCategory(ItemCategory category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public String getSn() {
        return sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    @Override
    public String toString() {
        return "Item [barcode=" + barcode + ", shortdesc=" + shortdesc + ", category=" + category + ", price=" + price
                + ", weight=" + weight + ", sn=" + sn + "]";
    }
}
