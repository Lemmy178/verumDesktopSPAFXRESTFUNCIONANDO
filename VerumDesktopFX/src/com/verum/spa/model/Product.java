/*=============================================================================
 |       Author:  Erick Ruben Ramos Vazquez
 |       Course:  Spa
 |     Due Date:  10/18/2019
 |  Description:  Product Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("prodId")
    @Expose
    private int prodId;
    @SerializedName("prodName")
    @Expose
    private String prodName;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("useCost")
    @Expose
    private Double useCost;
    @SerializedName("prodStatus")
    @Expose
    private int prodStatus;

    public Product() {
    }

    public Product(int prodId, String prodName, String brand, Double useCost,int prodStatus) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.brand = brand;
        this.useCost = useCost;
        this.prodStatus = prodStatus;
    }
    
    //add
    public Product(String prodName, String brand, Double useCost){
        this.prodName = prodName;
        this.brand = brand;
        this.useCost = useCost;
        
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(int prodStatus) {
        this.prodStatus = prodStatus;
    }

    public double getUseCost() {
        return useCost;
    }

    public void setUseCost(double useCost) {
        this.useCost = useCost;
    }

}
