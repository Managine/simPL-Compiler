package simpl.typing;

public final class PairType extends Type {

    public Type t1, t2;

    public PairType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public boolean equalsType(Type t) {
        if (!(t instanceof PairType))
            return false;
        PairType tt=(PairType)t;
        return t1.equals(tt.t1) && t2.equals(tt.t2);
    }
    
    @Override
    public boolean isEqualityType() {
        return t1.isEqualityType() && t2.isEqualityType();
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        if (t instanceof PairType) {
            PairType tmp=(PairType)t;
            return t1.unify(tmp.t1).compose(t2.unify(tmp.t2));
        }
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        return t1.contains(tv) || t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return new PairType(t1.replace(a, t),t2.replace(a, t));
    }

    public String toString() {
        return "(" + t1 + " * " + t2 + ")";
    }
}
