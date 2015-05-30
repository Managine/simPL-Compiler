package simpl.interpreter;

public class RefValue extends Value {

    public final int p;

    public RefValue(int p) {
        this.p = p;
    }

    public String toString() {
        return "ref@" + p;
    }

    @Override
    public boolean equals(Object other) {
        try {
            RefValue r=(RefValue)other;
            if (this.p==r.p)
                return true;
            else
                return false;
        }
        catch(Exception e) {
            return false;
        }
    }
}
