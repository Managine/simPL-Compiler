package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class RecValue extends Value {

    public Env E;
    public Symbol x;
    public Expr e;

    public RecValue(Env E, Symbol x, Expr e) {
        this.E = E;
        this.x = x;
        this.e = e;
    }

    @Override
    public boolean equals(Object other) {
        try {
            RecValue r=(RecValue)other;
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
