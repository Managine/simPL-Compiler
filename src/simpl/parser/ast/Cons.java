package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ListType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t1=l.typecheck(E);
        TypeResult t2=r.typecheck(E);
        Substitution s=t2.t.unify(new ListType(t1.t));
        TypeEnv.compose(s);
        return TypeResult.of(s.compose(t1.s.compose(t2.s)), new ListType(t1.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v1=l.eval(s);
        Value v2=r.eval(s);
        return new ConsValue(v1,v2);
    }
}
