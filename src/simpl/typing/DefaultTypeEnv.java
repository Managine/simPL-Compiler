package simpl.typing;

import static simpl.parser.Symbol.symbol;
import simpl.interpreter.Env;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.snd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        TypeVar a=new TypeVar(true);
        TypeVar b=new TypeVar(true);
//        TypeVar c=new TypeVar(false);
//        TypeVar d=new TypeVar(false);
        TypeVar e=new TypeVar(true);
        TypeVar f=new TypeVar(true);
        TypeVar g=new TypeVar(true);
//        TypeVar f=new TypeVar(false);
        E=TypeEnv.of(TypeEnv.empty, symbol("fst"), new ArrowType(new PairType(a,b),a));
        E=TypeEnv.of(E, symbol("snd"), new ArrowType(new PairType(a,b),b));
        E=TypeEnv.of(E, symbol("hd"), new ArrowType(new ListType(e),e));
        E=TypeEnv.of(E, symbol("tl"), new ArrowType(new ListType(e),new ListType(e)));
        E=TypeEnv.of(E, symbol("iszero"), new ArrowType(Type.INT,Type.BOOL));
        E=TypeEnv.of(E, symbol("pred"), new ArrowType(Type.INT,Type.INT));
        E=TypeEnv.of(E, symbol("succ"), new ArrowType(Type.INT,Type.INT));
        E=TypeEnv.of(E, symbol("cons"), new ArrowType(g,new ArrowType(new ListType(g),new ListType(g))));
    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }
}
