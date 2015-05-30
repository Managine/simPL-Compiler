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

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t1=e1.typecheck(E);
//        System.out.println(E.get(((Name)(((EqExpr)e1).l)).x));
        TypeResult t2=e2.typecheck(E);
        TypeResult t3=e3.typecheck(E);
        Substitution s1=t1.t.unify(Type.BOOL);
        Substitution s2=t2.t.unify(t3.t);
        TypeEnv.compose(s2);
        return TypeResult.of(t1.s.compose(t2.s.compose(t3.s.compose(s1.compose(s2)))),s2.apply(t2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        BoolValue b=(BoolValue)e1.eval(s);
        if (b.b)
            return e2.eval(s);
        else
            return e3.eval(s);
    }
}
