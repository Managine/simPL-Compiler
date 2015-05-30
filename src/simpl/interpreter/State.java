package simpl.interpreter;

import java.util.Set;

public class State {

    public final Env E;
    public final Mem M;
    public final Int p;

    protected State(Env E, Mem M, Int p) {
        this.E = E;
        this.M = M;
        this.p = p;
    }

    public static State of(Env E, Mem M, Int p) {
        State s=new State(E, M, p);
        return s;
    }
    
    public void garbageCollect() {
        //for widow
        int i=0,r=p.get();
        for (;i<r;i++) {
            if (!M.containsKey(i)) {
                M.put(i,M.get(p.get()-1));
                p.set(p.get()-1);
            }
                
        }
        
        //for orphan
        Set<Integer> ps=M.keySet();
        boolean exist []=new boolean[ps.size()];
        int j;
        for (j=0;j<exist.length;j++) {
            if (j<p.get())
                exist[j]=true;
            else
                exist[j]=false;
        }
        for (j=0;j<r;j++)
            if (!exist[j])
                M.remove(j);
    }
}
