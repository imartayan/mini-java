package mj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import mj.syntax.*;

public class SimpleHeap implements Heap {

	// TODO ajouter des champs ici

	public SimpleHeap(Interpreter interp) {
		// TODO
	}

	public Value allocObject(Identifier className) {
		// Imported java.util.Collections just for that :
		return new ObjectRef(className, Collections.emptyList());
	}

	public Value allocArray(Int size) {
		return new ArrayRef(size);
	}

	private class ArrayRef implements Value {
		int[] ref;

		public ArrayRef(Int size) {
			this.ref = new int[size.val];
		}		
	}

	public Int arrayLength(Value v) {
		return new Int(((ArrayRef) v).ref.length);
	}

	public Int arrayLookup(Value v, Int index) {
		return new Int(((ArrayRef) v).ref[index.val]);
	}

	public void arrayUpdate(Value v, Int index, Int i) {
		((ArrayRef) v).ref[index.val] = i.val;
	}

	private class ObjectRef implements Value {
		LocalVar ref;
		Identifier classname;

		public ObjectRef(Identifier cl, List<VarDeclaration> fieldDecls) {
			this.classname = cl;
			this.ref = new LocalVar(fieldDecls);
		}
	}

	public Value fieldLookup(Value v, Identifier field) {
		return ((ObjectRef) v).ref.read(field);
	}

	public void fieldUpdate(Value v1, Identifier field, Value v2) {
		((ObjectRef) v1).ref.store(field, v2);
	}

	public Type fieldType(Value v, Identifier field) {
		return ((ObjectRef) v).ref.type(field);
	}

	public Identifier classname(Value v) {
		return ((ObjectRef) v).classname;
	}

	public static void main(String[] arg) {
		try {
			String filename;
			if (arg.length == 0) {
				filename = "tests/ok/BinaryTree.java";
			} else {
				filename = arg[0];
			}
			Program prog = mj.parser.Parser.run(new FileInputStream(filename));
			Interpreter interp = new Interpreter(prog);
			interp.run(new SimpleHeap(interp));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ExecError e) {
			e.printStackTrace();
		}
	}

	public void gc() {
		// TODO Auto-generated method stub

	}

}

