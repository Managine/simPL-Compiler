package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Add;
import simpl.parser.ast.Expr;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;
import simpl.parser.ast.Sub;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class succ extends FunValue {

    public succ(Symbol x) {
        Name xo=new Name(x);
//        IntegerLiteral zero=new IntegerLiteral(0);
//        Eq e1=new Eq(xo,zero);
//        Sub e3=new Sub(xo,new IntegerLiteral(1)));
//        Cond e0=new Cond(e1,zero,e3);
        Add e0=new Add(xo,new IntegerLiteral(1));
        this.e=e0;
        this.x=x;
        this.E=null;
    }
}
