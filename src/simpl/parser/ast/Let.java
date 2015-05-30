package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Let extends Expr {

    public Symbol x;
    public Expr e1, e2;

    public Let(Symbol x, Expr e1, Expr e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(let " + x + " = " + e1 + " in " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t1=e1.typecheck(E);
        E=TypeEnv.of(E, x, t1.t);
        TypeResult t2=e2.typecheck(E);
        return TypeResult.of(t1.s.compose(t2.s),t2.t);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value t=e1.eval(s);
        Env et=new Env(s.E,x,t);
        return e2.eval(State.of(et,s.M,s.p));
    }
}
