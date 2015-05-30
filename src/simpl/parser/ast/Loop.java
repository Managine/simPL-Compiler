package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t1=e1.typecheck(E);
        Substitution s=t1.t.unify(Type.BOOL);
        TypeResult t2=e2.typecheck(E);
        return TypeResult.of(t1.s.compose(t2.s.compose(s)), Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
//        BoolValue b=(BoolValue)e1.eval(s);
//        Value v=e2.eval(s);
//        if (b.b)
//            return v;
//        else
//            return new UnitValue();
        BoolValue b=(BoolValue)e1.eval(s);
        while (b.b) {
            e2.eval(s);
            b=(BoolValue)e1.eval(s);
        }
        return Value.UNIT;
    }
}
