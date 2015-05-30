package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

//untested
public class FunValue extends Value {

    public Env E;
    public Symbol x;
    public Expr e;

    public FunValue() {}
    
    public FunValue(Env E, Symbol x, Expr e) {
        this.E = E;
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "fun";
    }

    @Override
    public boolean equals(Object other) {
        try {
            FunValue r=(FunValue)other;
            String expR=r.e.toString();
            String expRchanged=expR.replaceAll(r.x.toString(), this.x.toString());
            String expL=this.E.toString();
            if (expL.equals(expRchanged))
                return true;
            else
                return false;
        }
        catch (Exception e) {
            return false;
        }
    }
}
