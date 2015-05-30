package simpl.interpreter;

public class IntValue extends Value {

    public final int n;

    public IntValue(int n) {
        this.n = n;
    }

    public String toString() {
        return "" + n;
    }

    @Override
    public boolean equals(Object other) {
        try {
            IntValue r=(IntValue)other;
            if (this.n==r.n)
                return true;
            else
                return false;
        }
        catch (Exception e) {
            return false;
        }
    }
}
