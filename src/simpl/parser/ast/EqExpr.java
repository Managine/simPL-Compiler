package simpl.parser.ast;

import simpl.typing.ListType;
import simpl.typing.PairType;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        TypeResult t1=l.typecheck(E);
        TypeResult t2=r.typecheck(E);
        Substitution s=t1.t.unify(t2.t);
        TypeEnv.compose(s);
        if (!(t1.t instanceof TypeVar) && !(t2.t instanceof TypeVar))
            if (!(t1.t.isEqualityType() && t2.t.isEqualityType()))
                throw new TypeError("not equality type");
        return TypeResult.of(t1.s.compose(t2.s.compose(s).compose(s)),Type.BOOL);
    }
}
