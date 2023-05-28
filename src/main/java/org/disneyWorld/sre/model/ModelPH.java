package org.disneyWorld.sre.model;

public class ModelPH {
    private String supplierName;
    private String role;

    public ModelPH(String supplierName, String role) {
        this.supplierName = supplierName;
        this.role = role;
    }

    public ModelPH(){

    }
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getRole(){
        return role;
    }
}
