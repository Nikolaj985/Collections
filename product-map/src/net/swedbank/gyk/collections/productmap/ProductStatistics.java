package net.swedbank.gyk.collections.productmap;

import java.time.LocalDateTime;

public class ProductStatistics {
    private LocalDateTime firstSaleOn;
    private LocalDateTime lastSaleOn;
    private double salesAmount;
    /*
    Add fields to ProductStatistics:
    firstSaleOn (LocalDateTime type)
    lastSaleOn (LocalDateTime type)
    salesAmount (double)
     */


    // implement constructor


    public LocalDateTime getFirstSaleOn() {
        return firstSaleOn;
    }

    public LocalDateTime getLastSaleOn() {
        return lastSaleOn;
    }

    public double getSalesAmount() {
        return salesAmount;
    }

    public ProductStatistics(LocalDateTime saleOn, double salesAmount) {
        this.firstSaleOn = saleOn;
        this.lastSaleOn = saleOn;
        this.salesAmount = salesAmount;
    }

    private LocalDateTime getMinDate(LocalDateTime d1, LocalDateTime d2) {
        if (d1.compareTo(d2) < 0) return d1;
        return d2;
    }

    private LocalDateTime getMaxDate(LocalDateTime d1, LocalDateTime d2) {
        if (d1.compareTo(d2) > 0) return d1;
        return d2;
    }

    //implement this
    public void updateSalesAmount(double amount) {
        salesAmount+=amount;
    }

    //implement this
    public void updateFirstSalesDate(LocalDateTime dt) {
        this.lastSaleOn = getMinDate(this.lastSaleOn, dt);
    }

    //implement this
    public void updateLastSalesDate(LocalDateTime dt) {
        this.lastSaleOn = getMaxDate(this.lastSaleOn, dt);
    }
}
