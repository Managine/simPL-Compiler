package simpl.typing;

import simpl.parser.Symbol;
import simpl.typing.Substitution;

public abstract class TypeEnv {

    public abstract Type get(Symbol x);
    public static Substitution sub=Substitution.IDENTITY; 

    public static TypeEnv of(final TypeEnv E, final Symbol x, final Type t) {
        return new TypeEnv() {
            public Type get(Symbol x1) {
                if (x == x1) {
                    if (sub.apply(t)==null) {
                        return t;
                    }
                    Type tt=sub.apply(t);
                    while (!tt.equalsType(tt)) {
                        tt=sub.apply(tt);
                    }
                    return tt;
                }
                else 
                    return E.get(x1);
            }

            public String toString() {
                return x + ":" + t + ";" + E;
            }
        };
    }

    public static void compose(final Substitution s) {
        sub=sub.compose(s);
    }
    
    public static final TypeEnv empty = new TypeEnv() {
        @Override
        public Type get(Symbol x) {
            return null;
        }
    };
}
