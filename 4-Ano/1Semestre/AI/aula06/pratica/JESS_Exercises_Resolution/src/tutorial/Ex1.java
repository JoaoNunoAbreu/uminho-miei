package tutorial;

import jess.JessException;
import jess.Rete;

public class Ex1 {

	public static void main(String[] args)
	{
		try
		{	
			Rete engine= new Rete();
			engine.batch("clp/ex1.clp");
			engine.reset();

			engine.executeCommand("(facts)");
			engine.run();
			engine.executeCommand("(facts)");
		}
		catch(JessException ex)
		{
			System.out.println(ex);
		}
	}
}
