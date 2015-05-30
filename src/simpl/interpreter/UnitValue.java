package simpl.interpreter;

class UnitValue extends Value {

    public UnitValue() {
    }

    public String toString() {
        return "unit";
    }

    @Override
    public boolean equals(Object other) {
        String tmp=other.toString();
        if (tmp.equals("unit"))
            return true;
        else
            return false;
    }
}
