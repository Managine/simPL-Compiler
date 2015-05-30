package simpl.interpreter;

import static simpl.parser.Symbol.symbol;

import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.snd;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.interpreter.pcf.cons;

public class InitialState extends State {

    public InitialState() {
        super(initialEnv(Env.empty), new Mem(), new Int(0));
    }

    private static Env initialEnv(Env E) {
        Env tmp=new Env(E,symbol("fst"),new fst(symbol("x")));
        tmp=new Env(tmp,symbol("snd"),new snd(symbol("x")));
        tmp=new Env(tmp,symbol("hd"),new hd(symbol("x")));
        tmp=new Env(tmp,symbol("tl"),new tl(symbol("x")));
        tmp=new Env(tmp,symbol("iszero"),new iszero(symbol("x")));
        tmp=new Env(tmp,symbol("pred"),new pred(symbol("x")));
        tmp=new Env(tmp,symbol("succ"),new succ(symbol("x")));
        tmp=new Env(tmp,symbol("cons"),new cons(symbol("x"),symbol("y")));
        return tmp;
    }
}
