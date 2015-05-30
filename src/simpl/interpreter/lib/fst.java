package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.App;
import simpl.parser.ast.BooleanLiteral;
import simpl.parser.ast.Name;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class fst extends FunValue {

    public fst(Symbol x) {
        this.E=null;
        this.x=x;
        this.e=new App(new Name(x),new BooleanLiteral(true));
    }
}
