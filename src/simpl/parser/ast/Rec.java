package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeVar tv=new TypeVar(false);
        E=TypeEnv.of(E, x, tv);
        TypeResult et=e.typecheck(E);
        Substitution s=tv.unify(et.t);
        TypeEnv.compose(s);
        return TypeResult.of(et.s.compose(s),TypeEnv.sub.apply(et.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        RecValue r=new RecValue(s.E,x,e);
        Env Et=new Env(s.E,x,r);
        return e.eval(State.of(Et,s.M,s.p));
    }
}
