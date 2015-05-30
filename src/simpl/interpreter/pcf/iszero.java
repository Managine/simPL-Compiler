package simpl.interpreter.pcf;

import simpl.interpreter.BoolValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.BooleanLiteral;
import simpl.parser.ast.Cond;
import simpl.parser.ast.Eq;
import simpl.parser.ast.Expr;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class iszero extends FunValue {

    public iszero(Symbol x) {
        this.E=null;
        this.x=x;
        Name xo=new Name(x);
        IntegerLiteral zero=new IntegerLiteral(0);
        Eq e1=new Eq(xo,zero);
        BooleanLiteral e2=new BooleanLiteral(true);
        BooleanLiteral e3=new BooleanLiteral(false);
        Cond e0=new Cond(e1,e2,e3);
        this.e=e0;
    }
}
