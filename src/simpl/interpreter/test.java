package simpl.interpreter;

public class test {
    public static void main(String args[]) {
        ConsValue m=new ConsValue(new BoolValue(true),new BoolValue(false));
        ConsValue n=new ConsValue(new BoolValue(true),new ConsValue(new BoolValue(true),new BoolValue(false)));
        PairValue o=new PairValue(new BoolValue(true),new BoolValue(false));
        
        System.out.println(m.toString());
        System.out.println(n.toString());
        System.out.println(o.toString());
        
        System.out.println(' ');
        
        Boolean t1=m.equals(n);
        Boolean t2=m.equals(o);
        
        System.out.println(t1);
        System.out.println(t2);
    }
}