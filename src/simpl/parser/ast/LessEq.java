package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class LessEq extends RelExpr {

    public LessEq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " <= " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        IntValue v1=(IntValue)l.eval(s);
        IntValue v2=(IntValue)r.eval(s);
        if (v1.n<v2.n || v1.n==v2.n)
            return new BoolValue(true);
        else
            return new BoolValue(false);
    }
}
