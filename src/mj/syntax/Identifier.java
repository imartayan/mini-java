package mj.syntax;

import mj.Heap;
import mj.Interpreter;
import mj.LocalVar;
import mj.Value;
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

	public Value eval(Interpreter interp, Heap heap, LocalVar vars) {
		return null; // TODO
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

    public void checkInitialization(TypeChecker context) throws TypeError {
        if (context.isLocal(this) && !context.isInitialized(this)) {
            throw new TypeError("Variable " + this.toString() + " is not initialized");
        }
    }

	@Override
	public Value defaultValue() {
		return null;
	}
}
