package mj;

import mj.syntax.*;

public interface Heap {	
	
	public Value allocArray(Int size) throws ExecError;
	public Int arrayLength(Value v);
	public Int arrayLookup(Value v, Int index) throws ExecError;
	public void arrayUpdate(Value v, Int index, Int i) throws ExecError;

	public Value allocObject(Identifier className) throws ExecError;
	public Value fieldLookup(Value v, Identifier field);
	public void fieldUpdate(Value v1, Identifier field, Value v2);
	public Type fieldType(Value v, Identifier field);
	public Identifier classname(Value v);

	public void gc();
}
