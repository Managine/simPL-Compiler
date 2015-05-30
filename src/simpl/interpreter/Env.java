package simpl.interpreter;

import simpl.parser.Symbol;

public class Env {

    private Env E;
    private Symbol x;
    private Value v;

    private Env() {
        E = null;
        x = null;
        v = null;
    }

    public static Env empty = new Env() {
        public Value get(Symbol y) {
            return null;
        }

        public Env clone() {
            return this;
        }
    };

    public Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
    }

    public Value get(Symbol y) {
        String xt=x.toString();
        String yt=y.toString();
        if (xt.equals(yt))
            return v;
        else {
            if (this.E==null)
                return null;
            else
                return E.get(y);
        }
    }

    public Env clone() {
        if (this==null)
            return null;
        Env tmp=new Env(this.E.clone(),this.x,this.v);
        return tmp;
    }
    
    private Boolean change(Env E,Symbol y,Value v) {
        if (E==null)
            return false;
        String xt=this.x.toString();
        String yt=y.toString();
        if (xt.equals(yt))
        {
            this.v=v;
            return true;
        }
        else
            return change(E.E,y,v);
    }
    
    public Env add(Symbol y,Value v) {
        Env tmp=this.clone();
        Boolean flag=change(tmp,y,v);
        if (!flag)
            tmp=new Env(tmp,y,v);
        return tmp;
    }
}
