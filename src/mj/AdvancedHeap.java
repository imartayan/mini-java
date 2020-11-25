package mj;

import java.util.LinkedList;
import java.util.List;

import mj.syntax.*;
import mj.Value;

public class AdvancedHeap implements Heap {

	private final Value[] memory;
	private final Interpreter interpreter;


	private Freelist freelist; 
	// adresse du premier objet de la freelist
	// null ssi la freelist est vide


	private class Ptr implements Value {
		public final int offset;
		public Ptr(int offset) {
			this.offset = offset;
		}
	}

	private class ObjectType implements Value {
		public final Identifier id;
		public ObjectType(Identifier id) {
			this.id = id;
		}
	}

	private class ArraySize implements Value {
		public final int size;
		public ArraySize(int size) {
			this.size = size;
		}
	}

	private class Freelist implements Value {
		public final int offset;
		public Freelist(int offset) {
			this.offset = offset;
		}
	}

	private boolean freelistIsEmpty() {
		return false; //TODO
	}

	private void freelistPush(int offset) {
		//TODO
	}

	private Freelist freelistPop() throws ExecError {
		//TODO		
		return null;
	}

	private final static int markField = 0;
	private final static int nextField = 1;
	private final static int typeField = 1;
	private final static int firstField = 2;
	private final static int maxNumberObjects = 10;
	private final static int objectMaxSize = 10;

	private void dumpCell(int i, String annot) {
		System.out.printf("%3d: %8s%s\n",i,memory[i],annot);
	}

	public void dump() {
		// à adapter à vos besoins
		System.out.print("DUMPING MEMORY (freelist ");
		if (freelistIsEmpty()) System.out.println("is empty)");
		else System.out.printf("starts at %d)\n", freelist.offset);
		for (int i = 0; i<maxNumberObjects; i++) {
			dumpCell(i * (objectMaxSize+firstField), "  // markField");
			dumpCell(i * (objectMaxSize+firstField) +1, "  // nextField or sizeField");
			for (int j=0;j<objectMaxSize;j++)
				dumpCell(i * (objectMaxSize+firstField)+j+2, "");
		}
	}

	private boolean marked(Ptr ptr) {
		// TODO
		return false;
	}	

	private void mark(Ptr p) {
		// TODO
	}

	private void unmark(Ptr p) {
		// TODO
	}

	private void visit(Ptr ptr) {
		// TODO
	}

	public void gc() {
		System.err.print("GC starts... ");
		System.err.print("marking... ");
		for (Ptr ptr : buildRoots()) {
			if (!marked(ptr)) visit(ptr);
		}
		System.err.print("sweeping... ");
		int countUnmarked = 0;
		for (int i = 0; i<maxNumberObjects; i++) {
			Ptr ptr =  new Ptr(i * (objectMaxSize+firstField));
			if (marked(ptr)) unmark(ptr);
			else {
				countUnmarked++;
				freelistPush(ptr.offset);
			}
		}
		System.err.printf("%d objects have been pushed in the freelist\n",countUnmarked);
	}

	// Construit la liste des racines dans l'état actuel de l'interpréteur
	// Pensez à mettre à jour la callstack dans votre interpréteur
	private List<Ptr> buildRoots() {
		LinkedList<Ptr> roots = new LinkedList<>();
		for (LocalVar vars : interpreter.callstack)
			for (Value v : vars.values.values())
				if (v instanceof Ptr) roots.add((Ptr) v);
		return roots;
	}

	public AdvancedHeap(Interpreter interpreter) {
		this.interpreter = interpreter;
		memory = new Value[maxNumberObjects * (objectMaxSize+firstField)];
		// TODO
	}

	public Value allocObject(Identifier className)throws ExecError {
		// TODO
		return null;
	}

	public Value allocArray(Int size)throws ExecError {
		return null; // TODO
	}

	public Int arrayLength(Value v) {
		return null; // TODO
	}

	public Int arrayLookup(Value v, Int index)throws ExecError {
		return null; // TODO
	}

	public void arrayUpdate(Value v, Int index, Int i)throws ExecError {
		// TODO
	}

	public Value fieldLookup(Value v, Identifier field) {
		return null; // TODO
	}

	public void fieldUpdate(Value v1, Identifier field, Value v2) {
		// TODO
	}

	public Type fieldType(Value v, Identifier field) {
		return null; // TODO
	}

	public Identifier classname(Value v) {
		// TODO
		return null;	
	}
}
