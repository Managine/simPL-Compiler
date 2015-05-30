package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.PairType;
import simpl.typing.BoolType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t1=l.typecheck(E);
        TypeResult t2=r.typecheck(E);
        if (t1.t instanceof TypeVar) {
            Substitution s1=t1.t.unify(new ArrowType(t2.t,new TypeVar(false)));
            TypeEnv.compose(s1);
            return TypeResult.of(t1.s.compose(t2.s.compose(s1)),TypeEnv.sub.apply(((ArrowType)s1.apply(t1.t)).t2));
        }
        if (t1.t instanceof ArrowType) {
            Substitution s2=((ArrowType)t1.t).t1.unify(t2.t);
            TypeEnv.compose(s2);
            return TypeResult.of(t1.s.compose(t2.s.compose(s2)), ((ArrowType)s2.apply((ArrowType)t1.t)).t2);
        }
        throw new TypeError("not match");
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value t=l.eval(s);
        Value v=r.eval(s);
        if (t instanceof FunValue) {
            FunValue e1=(FunValue)t;
            e1.E=new Env(e1.E,e1.x,v);
            s.M.put(s.p.get(),v);
            s.p.set(s.p.get()+1);
            return e1.e.eval(State.of(e1.E,s.M,s.p));
        }
        else if (t instanceof PairValue && v instanceof BoolValue) {
            PairValue t0=(PairValue)t;
            BoolValue v0=(BoolValue)v;
            if (v0.b)
                return t0.v1;
            else
                return t0.v2;
        }
        else if (t instanceof ConsValue){
            ConsValue t0=(ConsValue)t;
            BoolValue v0=(BoolValue)v;
            if (v0.b)
                return t0.v1;
            else
                return t0.v2;
        }   
        else
            throw new RuntimeError("Error!");
    }
}
