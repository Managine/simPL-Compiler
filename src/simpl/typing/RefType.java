package simpl.typing;

public final class RefType extends Type {

    public Type t;

    public RefType(Type t) {
        this.t = t;
    }

    @Override
    public boolean equalsType(Type t) {
        if (!(t instanceof RefType))
            return false;
        return this.t.equals(((RefType)t).t);
    }
    
    @Override
    public boolean isEqualityType() {
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        if (t instanceof TypeVar)
            return t.unify(this);
        if (!(t instanceof RefType))
            throw new TypeMismatchError();
        return this.t.unify(((RefType)t).t);
    }

    @Override
    public boolean contains(TypeVar tv) {
        return t.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return new RefType(this.t.replace(a, t));
    }

    public String toString() {
        return t + " ref";
    }
}
