package simpl.parser.ast;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        return TypeResult.of(E.get(x));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value t=s.E.get(x);
        if (t instanceof RecValue)
            t=new Rec(((RecValue) t).x,((RecValue) t).e).eval(State.of(((RecValue) t).E, s.M , s.p));
        return t;
    }
}
