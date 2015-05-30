package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Seq extends BinaryExpr {

    public Seq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " ; " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t1=l.typecheck(E);
        TypeResult t2=r.typecheck(E);
        return TypeResult.of(t1.s.compose(t2.s),t2.t);
        
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v1=l.eval(s);
        Value v2=r.eval(s);
        return v2;
    }
}
