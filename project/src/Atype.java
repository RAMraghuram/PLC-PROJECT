/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author wanlipa
 */
public class Atype<V, Boolean> {
    
    public final V value;
    public final Boolean error;
    public String type;
    Atype (V value, Boolean error,String type){
        this.value = value;
        this.error = error;
        this.type = type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
 
    
    
}
