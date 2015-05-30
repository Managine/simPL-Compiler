package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    private int cLength() {
        int length=1;
        if (v2.equals(new NilValue()))
            return length;
        else if (v2 instanceof ConsValue)
            return length+((ConsValue)v2).cLength();
        else
            return length+1;
    }
    
    public String toString() {
        String t=String.valueOf(this.cLength());
        return "list@"+t;
    }

    @Override
    public boolean equals(Object other) {
        try {
            ConsValue r=(ConsValue)other;
            if (this.v1.equals(r.v1) && this.v2.equals(r.v2))
                return true;
            else
                return false;
        }
        catch (Exception e) {
            return false;
        }
    }
}
