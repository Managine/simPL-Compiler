package simpl.typing;

public final class ListType extends Type {

    public Type t;

    public ListType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        return t.isEqualityType();
    }
    
    @Override
    public boolean equalsType(Type t) {
        if (!(t instanceof ListType))
            return false;
        return this.t.equals(((ListType)t).t);
    }
    
    @Override
    public Substitution unify(Type t) throws TypeError {
        if (t instanceof ListType)
            return this.t.unify(((ListType)t).t);
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        if (t instanceof TypeVar) {
            String ts=((TypeVar)this.t).toString();
            if (ts.equals(tv.toString()))
                return true;
        }
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        return new ListType(this.t.replace(a, t));
    }

    public String toString() {
        return t + " list";
    }
}
