package simpl.typing;

import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean equalsType(Type t) {
        if (!(t instanceof TypeVar))
            return false;
        return this.name.equals(((TypeVar)t).name);
    }
    
    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        if (!(t instanceof TypeVar))
            if (t.contains(this))
                throw new TypeCircularityError();
        Substitution s=Substitution.of(this, t);
        try {
            Type tmp=TypeEnv.sub.apply(this);
            if (tmp!=this) {
                Substitution s2=tmp.unify(t);
                return s.compose(s2);
            }
        }
        catch (Exception e) {}
        return s;
    }

    public String toString() {
        return "" + name;
    }

    @Override
    public boolean contains(TypeVar tv) {
        if ((tv.toString()).equals(this.name.toString())) 
            return true;
        else
            return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        if (this.contains(a)) {
            this.equalityType=t.isEqualityType();
            return t;
        }
        else
            return this;
    }
}
