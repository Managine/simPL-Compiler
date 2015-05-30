package simpl.parser.ast;

import java.util.Set;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t=e.typecheck(E);
        return TypeResult.of(t.s,new RefType(t.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        Value v=e.eval(s);
        Integer p=new Integer(-1);
        Set<Integer> t=s.M.keySet();
        for (Integer i:t) {
            if (s.M.get(i).equals(v)) {
                p=i;
                break;
            }
        }
        if (p==-1 && (v instanceof IntValue) || (v instanceof BoolValue)) {
            s.M.put(s.p.get(), v);
            p=s.p.get();
            s.p.set(s.p.get()+1);
        } 
        return new RefValue(p);
    }
}
