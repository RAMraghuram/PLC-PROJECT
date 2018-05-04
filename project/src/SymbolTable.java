package src;

import java.util.Hashtable;

public class SymbolTable extends Hashtable<String,Object>{
    
//    public static class AddV {
//        public String id;
//        public int value;
//        
//        AddV(String id, int value){
//            this.id = id;
//            this.value = value;
//        }
//    }

    
    static SymbolTable globalTable;
    
    static {globalTable = new SymbolTable();}


    static void setValue(String id, int value){
        globalTable.put(id, value);
    }
    
    static void setValuet(String id, Atype value){
        globalTable.put(id, value);
    }
    
    static void setValuef(String id, float value){
        globalTable.put(id,value);
    }

    static Atype getValue(String id){
        return (Atype) globalTable.get(id);
    }
    
    static Float getValuef(String id){
        return (Float) globalTable.get(id);
    }
    
    static void setType(String type, String id){
        globalTable.put(type, id);
    }
    
    static String getType(String type){
        return (String) globalTable.get(type);
    }
}
