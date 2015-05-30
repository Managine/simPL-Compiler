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

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t1=l.typecheck(E);
        Substitution s1=t1.s.compose(t1.t.unify(Type.BOOL));
        TypeResult t2=r.typecheck(E);
        Substitution s2=t2.s.compose(t2.t.unify(Type.BOOL));
        Substitution s=s1.compose(s2);
        return TypeResult.of(s,Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        BoolValue result;
        BoolValue lh=(BoolValue)l.eval(s);
        if (!lh.b) {
            result=new BoolValue(false);
        }
        else {
            BoolValue rh=(BoolValue)r.eval(s);
            if (rh.b) {
                result=new BoolValue(true);
            }
            else {
                result=new BoolValue(false);
            }
        }
        return result;
    }
}
