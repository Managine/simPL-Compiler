package simpl.interpreter;

public class NilValue extends Value {

    public NilValue() {
    }

    public String toString() {
        return "nil";
    }

    @Override
    public boolean equals(Object other) {
        String tmp=other.toString();
        if (tmp.equals("nil"))
            return true;
        else
            return false;
    }
}
