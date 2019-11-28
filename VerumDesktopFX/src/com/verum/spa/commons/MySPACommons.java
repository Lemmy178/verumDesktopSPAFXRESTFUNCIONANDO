package com.verum.spa.commons;

public class MySPACommons {
    public static final String URL_SERVER = "http://localhost:8080/VerumRESTSpa2/api/";
    
    
    /**
     * 
     * @param string
     * @return The same String but instead of every space contained this method puts "%20"
     * @important Only for url resquests use
     */
    public static String convertStringToURLPath(String string){
        String response = "";
        String [] splitedChain = string.split(" ");
        for (int i = 0; i < splitedChain.length; i++) {            
            response+=splitedChain[i].trim();
            if(i<splitedChain.length){
                response+="%20";
            }
        }                    
        return response;
    }
    
    
}
