package simpl.interpreter.pcf;

import simpl.interpreter.FunValue;
import simpl.parser.Symbol;
import simpl.parser.ast.Cons;
import simpl.parser.ast.Fn;
import simpl.parser.ast.Name;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class cons extends FunValue {

    public cons(Symbol x, Symbol y) {
        this.E=null;
        this.x=x;
        Fn f=new Fn(x, new Fn(y,new Cons(new Name(x), new Name(y))));
        this.e=f;
    }
}