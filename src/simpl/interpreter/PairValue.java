package simpl.interpreter;

public class PairValue extends Value {

    public final Value v1, v2;

    public PairValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        return "pair@" + v1 + "@" + v2;
    }

    @Override
    public boolean equals(Object other) {
        try {
            PairValue r=(PairValue)other;
            if (this.v1.equals(r.v1) && this.v2.equals(r.v2))
                return true;
            else
                return false;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public Value getFirst() {
        return v1;
    }
    
    public Value getSecond() {
        return v2;
    }
}
