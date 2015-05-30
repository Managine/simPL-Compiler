package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t=e.typecheck(E);
        if (t.t instanceof RefType)
            return TypeResult.of(t.s,((RefType)t.t).t);
        if (t.t instanceof TypeVar) {
            TypeVar tv=new TypeVar(false);
            Substitution s=t.s.compose(t.t.unify(new RefType(tv)));
            return TypeResult.of(s,tv);
        }
        throw new TypeError("not match");
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Integer p=((RefValue)e.eval(s)).p;
        Value t=s.M.get(p);
        return t;
    }
}
