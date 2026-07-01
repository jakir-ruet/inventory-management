package com.jakirbd.inventory_management.dto;

public class PurchaseLineResponse {

    private Long purchaseLineId;
    private Long productId;
    private String productCode;
    private String productName;
    private Integer quantity;
    private Double unitCost;
    private Double lineTotal;

    public PurchaseLineResponse() {
    }

    public Long getPurchaseLineId() {
        return purchaseLineId;
    }

    public void setPurchaseLineId(Long purchaseLineId) {
        this.purchaseLineId = purchaseLineId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(Double lineTotal) {
        this.lineTotal = lineTotal;
    }
}
