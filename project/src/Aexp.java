package src;

public class Aexp {

    public enum AexpType {
        INTEGER,        
        FLOAT,
        ID,
        BOOLEAN,
        CHAR,
        EXP
    }
    
    private final AexpType eType;
    private Integer inum;
    private Float fnum;
    private String id;
    private Boolean bl;
    private char character;
    private boolean error = false;
    
    
    private Args operands;
    private int operator;

    Aexp(Integer x) {
        eType = AexpType.INTEGER;
        inum = x;        

    }
    
    Aexp(Float x) {
        eType = AexpType.FLOAT;
        fnum = x;

    }
    
    Aexp(String x) {
        eType = AexpType.ID;
        id = x;        

    }
    
    Aexp(Boolean x) {
        eType = AexpType.BOOLEAN;
        bl = x;

    }
    
    Aexp(char x){
        eType = AexpType.CHAR;
        this.character = x;
    }
    

    Aexp(Args x, int op) {
        eType = AexpType.EXP;
        operands = x;
        operator = op;
    }

    public AexpType getEType(){
        return this.eType;
    }
    
    public boolean getErr(){
        return this.error;
    }
    
    public String getexp() {

        String s = "";
        switch (this.eType) {
            case INTEGER: s = "" + inum; break;
            case FLOAT: s = "" + fnum; break;
            case ID: s = id; break;
            case BOOLEAN: s = bl.toString(); break;
            case CHAR: s = "" + character; break;
            case EXP:
                switch (operator) {
                    case sym.PLUS:
                        s = "PLUS(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    case sym.MINUS:
                        s = "MINUS(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    case sym.TIMES:
                        s = "TIMES(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    case sym.DIVIDE:
                        s = "DIVIDE(" + operands.getfi().getexp() + "," + operands.getse().getexp() + ")";
                        break;
                    default: break;
                } break;
            default: break;
        }

        return s;
    }

    public Object getValue() {
        Object val = 0;
//        Float fval = 0.0f;
        switch (this.eType) {
            case INTEGER:
                // expression is a number
                val = new Atype(inum, false, "INTEGER"); break;
            case FLOAT:
                //expression is a number
//                val = (int)(Math.round(fnum)); break;
                val = new Atype(fnum, false, "FLOAT"); break;
//                System.out.print(fnum + " is float can't calculate");
//                System.exit(0);
            case ID:
                //expression is a variable
                val = SymbolTable.getValue(id);
                if (val == null) {
                    System.out.print(id + " was not declared");
                    System.exit(0);
                }   break;
            case BOOLEAN:
                //expression is a variable
                val = new Atype(bl, false, "BOOLEAN"); break;
            case EXP:
                Atype fi = (Atype)operands.getfi().getValue();
           
                Atype se = (Atype)operands.getse().getValue();
          
                //expression is a math expression
                switch (operator) {
                    case sym.PLUS:
                        if(operands.getfi().error || operands.getse().error ){
                            error = true;
                        }
                        else if((fi.value instanceof Integer && se.value instanceof Integer) ){
                            val = new Atype(((Integer)fi.value) + ((Integer)se.value), false,"INTEGER");
                        }
                        else if(fi.value instanceof Float && se.value instanceof Float){
                                
                            val = new Atype(new Float(fi.value.toString()) + new Float(se.value.toString()), false,"FLOAT");
                        }
                        else{
                            val = new Atype(0, true,"");
                            error = true;
                        }
//                        val = operands.getfi().getValue() + operands.getse().getValue();
                        break;
                    case sym.MINUS:
                        if(operands.getfi().error || operands.getse().error ){
                            error = true;
                        }
                        else if((fi.value instanceof Integer && se.value instanceof Integer) ){
                            val = new Atype(((Integer)fi.value) - ((Integer)se.value),false,"INTEGER");
                        }
                        else if(fi.value instanceof Float && se.value instanceof Float){
                                
                            val = new Atype(new Float(fi.value.toString()) - new Float(se.value.toString()), false,"FLOAT");
                        }
                        else{
                            val = new Atype(0, true,"");
                            error = true;
                        }
//                        val = operands.getfi().getValue() - operands.getse().getValue();
                        break;
                    case sym.TIMES:
                        if(operands.getfi().error || operands.getse().error ){
                            error = true;
                        }
                        else if((fi.value instanceof Integer && se.value instanceof Integer) ){
                            val = new Atype(((Integer)fi.value) * ((Integer)se.value),false,"INTEGER");
                        }
                        else if(fi.value instanceof Float && se.value instanceof Float){
                                
                            val = new Atype(new Float(fi.value.toString()) * new Float(se.value.toString()),false,"FLOAT");
                        }
                        else{
                             val = new Atype(0, true,"");
                            error = true;
                        }
//                        val = operands.getfi().getValue() * operands.getse().getValue();
                        break;
                    case sym.DIVIDE:
                        if(operands.getfi().error || operands.getse().error ){
                            error = true;
                        }
                        else if((fi.value instanceof Integer && se.value instanceof Integer) ){
                            val = new Atype(((Integer)fi.value) / ((Integer)se.value),false,"INTEGER");
                        }
                        else if(fi.value instanceof Float && se.value instanceof Float){
                                
                            val = new Atype(new Float(fi.value.toString()) / new Float(se.value.toString()),false,"FLOAT");
                        }
                        else{
                                 val = new Atype(0, true,"");
                            error = true;
                        }
//                        val = operands.getfi().getValue() / operands.getse().getValue();
                        break;
                    default:
                        break;
                } break;
            default: break;
        }
        return val;
    }
    
    public float getValuef() {
        Float fval = 0.0f;
        switch (this.eType) {
            case INTEGER:
                // expression is a number
                fval = (float)inum; break;
            case FLOAT:
                //expression is a number
                fval = fnum; break;
            case ID:
                //expression is a variable
                fval = SymbolTable.getValuef(id);
                if (fval == null) {
                    System.out.print(id + " was not declared");
                    System.exit(0);
                }   break;
            case BOOLEAN:
                //expression is a variable
                fval = SymbolTable.getValuef(bl.toString());
                if (fval == null) {
                    System.out.print(bl + " was not declared");
                    System.exit(0);
                }   break;
            case EXP:
                //expression is a math expression
                switch (operator) {
                    case sym.PLUS:
                        fval = operands.getfi().getValuef() + operands.getse().getValuef();
                        break;
                    case sym.MINUS:
                        fval = operands.getfi().getValuef() - operands.getse().getValuef();
                        break;
                    case sym.TIMES:
                        fval = operands.getfi().getValuef() * operands.getse().getValuef();
                        break;
                    case sym.DIVIDE:
                        fval = operands.getfi().getValuef() / operands.getse().getValuef();
                        break;
                    default:
                        break;
                } break;
            default: break;
        }
        return fval;
    }
        
    public String getType(){    
        String t = "";
            switch (this.eType) {
                case INTEGER: t = "Integer" ; break;
                case FLOAT: t = "Float"; break;
                case ID: t = "Char"; break;                
                case BOOLEAN: t = "Boolean"; break;           
                default: break;
            }        
        return t;
    }
    
}
