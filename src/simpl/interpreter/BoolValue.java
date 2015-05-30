package simpl.interpreter;

public class BoolValue extends Value {

    public final boolean b;

    public BoolValue(boolean b) {
        this.b = b;
    }

    public String toString() {
        return "" + b;
    }

    @Override
    public boolean equals(Object other) {
        try {
            BoolValue r=(BoolValue)other;
            if (this.b==r.b)
                return true;
            else
                return false;
        }
        catch(Exception e) {
            return false;
        }
    }
};