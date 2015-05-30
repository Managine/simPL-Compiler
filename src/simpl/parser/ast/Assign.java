package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.Mem;
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

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t1=l.typecheck(E);
        TypeResult t2=r.typecheck(E);
        Substitution s=t1.s.compose(t2.s.compose(t1.t.unify(new RefType(t2.t))));
        TypeEnv.compose(((RefType)(t1.t)).t.unify(t2.t));
        return TypeResult.of(s,Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        RefValue p=(RefValue)l.eval(s);
        Value rv=r.eval(s);
        if (s.M.containsKey(p.p))
            s.M.remove(p.p);
        s.M.put(p.p, rv);
        return Value.UNIT;
    }
}
