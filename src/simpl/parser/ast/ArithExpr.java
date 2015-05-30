package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t1=l.typecheck(E);
        Substitution s1=t1.s.compose(t1.t.unify(Type.INT));
        TypeEnv.compose(s1);
        TypeResult t2=r.typecheck(E);
        Substitution s2=t2.s.compose(t2.t.unify(Type.INT));
        TypeEnv.compose(s2);
        Substitution s=s1.compose(s2);
        return TypeResult.of(s,Type.INT);
    }
}
