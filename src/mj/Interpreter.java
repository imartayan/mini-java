package mj;

// import java.io.FileInputStream;
// import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mj.syntax.*;

public class Interpreter {

	public final Map<Identifier, ClassDeclaration> classes = new Hashtable<>();
	public final Map<Identifier, Map<Identifier, MethodDeclaration>> methods = new Hashtable<>();
	public final Program program;
	public final List<LocalVar> callstack = new LinkedList<>(); // utile pour AdvancedHeap
	public Map<Identifier, Value> objects = new Hashtable<>();
	public Map<Identifier, Value> arrays = new Hashtable<>();
	public Identifier currentObject = new Identifier("main");

	public Interpreter(Program program) {
		this.program = program;
		for (ClassDeclaration c : program.declarations) {
			classes.put(c.name, c);
			Map<Identifier, MethodDeclaration> m = new Hashtable<>();
			methods.put(c.name, m);
			for (MethodDeclaration md: c.methodDeclarations)
				m.put(md.name, md);
		}
	}

	public void run(Heap heap)throws ExecError  {
		LocalVar vars = new LocalVar(program.main.declarations);
		program.main.body.eval(this, heap, vars);
	}

}
