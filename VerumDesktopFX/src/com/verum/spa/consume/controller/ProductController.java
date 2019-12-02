
/*=============================================================================
 |       Author:  Erick Ruben Ramos Vazquez
 |       Course:  Spa
 |     Due Date:  10/31/2019
 |  Description:  Product Controller
 |                
 | Deficiencies:  Ninguna detectada. Se hace el control de entrada del usuario  
                  antes de mandar valores a BD
 *===========================================================================*/
package com.verum.spa.consume.controller;

import com.verum.spa.consumeREST.ProductConsumeREST;
import com.verum.spa.model.Product;
import java.io.IOException;
import java.util.ArrayList;

public class ProductController {
    
    private static Product pro = new Product();
    private static ProductConsumeREST proREST = new ProductConsumeREST();
    private static int code;

    public static boolean emptyFieldsValidation(String prodName, String prodBrand, double proPrice) {
        if (prodName.trim().length() > 0 && prodName.trim().length() < 255 || prodBrand.trim().length() > 0
                && prodBrand.trim().length() < 255 || proPrice != 0) {
            return true;
        }
        return false;
    }

    public static String addProductController(String proName, String brand, double useCost) {
        pro.setProdName(proName);
        pro.setBrand(brand);
        pro.setUseCost(useCost);
        code = proREST.addProduct(pro);
        if (code != 200) {
            return "Error";
        } else {
            return "Producto agregado.";
        }
    }

    public static String modifyProductController(String proName, String brand, double useCost, int prodStatus,int proID) {
        pro.setProdName(proName);
        pro.setBrand(brand);
        pro.setUseCost(useCost);
        pro.setProdStatus(prodStatus);
        pro.setProdId(proID);
        code = proREST.modifyProduct(pro);
        if (code != 200) {
            return "Error";
        } else {
            return "Producto modificado.";
        }
    }

    public static String logicalDelteController(int proID) {
        pro.setProdId(proID);
        code = proREST.logicalDeleteProduct(pro);
        if (code != 200) {
            return "Error";
        } else {
            return "Producto eliminado.";
        }
    }

    public ArrayList<Product> productList() throws IOException {
        ArrayList<Product> datosProduct = new ArrayList<>();
        datosProduct = proREST.listProduct();
        return datosProduct;
    }

}
