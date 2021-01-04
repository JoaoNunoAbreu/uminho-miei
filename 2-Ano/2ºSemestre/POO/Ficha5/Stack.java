import java.util.List;
import java.util.ArrayList;
/**
 * Escreva a descrição da classe Stack aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Stack{
    private List<String> stack;
    public Stack(){
        this.stack = new ArrayList<>();
    }
    public Stack(List<String> stack){
        setStack(stack);
    }
    public Stack(Stack outraStack){
        this.stack = outraStack.getStack();
    }
    public List<String> getStack(){
        //return this.stack; ERRADO
        List<String> res = new ArrayList<>();
        stack.stream().forEach(s -> res.add(s));
        return res;
    }
    public void setStack(List<String> a){
        this.stack = new ArrayList<>();
        for(String s : a)
            this.stack.add(s);
    }
    public Stack clone(){
        return new Stack(this);
    }
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null | o.getClass() != this.getClass()) return false;
        Stack s = (Stack) o;
        return this.stack.equals(s.getStack());
    }
    public String toString(){
        return this.stack.toString();
    }
    // (a)
    public String top(){
        String s;
        if(!empty()) // (d)
            s = this.stack.get(0);
        else s = "";    
        return s;
    }
    // (b)
    public void push(String s){
        this.stack.add(0,s);
    }
    // (c)
    public void pop(){
        if(!empty())
            this.stack.remove(0);
    }
    // (d)
    public boolean empty(){
        return this.stack.isEmpty();
    }
    // (e)
    public int length(){
        return this.stack.size();
    }
}
