package org.drools.hackfest2022;

import org.drools.hackfest2022.model.Alert;
import org.drools.hackfest2022.model.Coupon;
import org.drools.hackfest2022.model.Item;
import org.kie.kogito.rules.DataSource;
import org.kie.kogito.rules.DataStore;
import org.kie.kogito.rules.RuleUnitData;
import org.kie.kogito.rules.SingletonStore;

public class BillUnitData implements RuleUnitData {
    private DataStore<Item> items;
    private DataStore<Coupon> coupons;
    private String loyaltyID;
    private DataStore<Alert> alerts = DataSource.createStore();
    private DataStore<String> logs = DataSource.createStore();
    private SingletonStore<Number> subtotal;
    private SingletonStore<Number> discount;
    private SingletonStore<Number> total;
	public boolean isCheckout;

    public BillUnitData() {
        this(DataSource.createStore(), DataSource.createStore(), null);
    }
    public BillUnitData(DataStore<Item> items, DataStore<Coupon> coupons, String loyaltyID) {
        this.items = items;
        this.coupons = coupons;
        this.loyaltyID = loyaltyID;

        this.subtotal = DataSource.createSingleton();
        this.discount = DataSource.createSingleton();
        this.total = DataSource.createSingleton();
        this.subtotal.set(0.0);
        this.discount.set(0.0);
        this.total.set(0.0);
    }
    public DataStore<Item> getItems() {
        return items;
    }
    public void setItems(DataStore<Item> items) {
        this.items = items;
    }
    public DataStore<Alert> getAlerts() {
        return alerts;
    }
    public void setAlerts(DataStore<Alert> alerts) {
        this.alerts = alerts;
    }
    public DataStore<Coupon> getCoupons() {
        return coupons;
    }
    public void setCoupons(DataStore<Coupon> coupons) {
        this.coupons = coupons;
    }
    public String getLoyaltyID() {
        return loyaltyID;
    }
    public void setLoyaltyID(String loyaltyID) {
        this.loyaltyID = loyaltyID;
    }
    public DataStore<String> getLogs() {
        return logs;
    }
    public void setLogs(DataStore<String> logs) {
        this.logs = logs;
    }
    public SingletonStore<Number> getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(SingletonStore<Number> subtotal) {
        this.subtotal = subtotal;
    }
    public SingletonStore<Number> getDiscount() {
        return discount;
    }
    public void setDiscount(SingletonStore<Number> discount) {
        this.discount = discount;
    }
    public SingletonStore<Number> getTotal() {
        return total;
    }
    public void setTotal(SingletonStore<Number> total) {
        this.total = total;
    }

}
