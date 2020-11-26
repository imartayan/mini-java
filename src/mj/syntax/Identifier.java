package mj.syntax;

import mj.ExecError;
import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
import mj.Int;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class Identifier implements Type, Expression {
	public final String name;
	public final int line; // for debug
	public final int col; // for debug

	public Identifier(String name) {
		this.name = name;
		this.line = -1;
		this.col = -1;
	}

	public Identifier(String name, int line, int col) {
		this.name = name;
		this.line = line;
		this.col = col;
	}

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) throws ExecError {
		if (interp.arrays.containsKey(this)) {
			return interp.arrays.get(this);
		} else if (interp.objects.containsKey(this)) {
			return interp.objects.get(this);
		} else if (vars.values.containsKey(this)) {
			return vars.values.get(this);
		} else {
			throw new ExecError("Identifier evaluation : undefined variable, array or object");
		}
	}

	public void print() {
		System.out.print(name);
	}

	public String toString() {
		return this.name;
	}

	public int hashCode() {
		return this.name.hashCode();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identifier other = (Identifier) obj;
		return name.equals(other.name);
	}

	public boolean isSubtypeOf(Type t, TypeChecker context) {
		Type thisType = context.lookup(this);
		if (thisType == this) {
			return context.inheritsFrom(this, t);
		}
		return thisType.isSubtypeOf(t, context);

	}

	public Type type(TypeChecker context) throws TypeError {
		return context.lookup(this);
	}

	@Override
	public Value defaultValue() {
		return null;
	}
}
