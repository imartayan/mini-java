package mj;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import mj.syntax.Identifier;
import mj.syntax.Type;
import mj.syntax.VarDeclaration;

public class LocalVar {
	public final Map<Identifier, Value> values = new Hashtable<Identifier, Value>();
	public final Map<Identifier, Type> types = new Hashtable<Identifier, Type>();

	public Type type(Identifier id) {
		return types.get(id);
	}

	public Value read(Identifier id) {
		if (values.containsKey(id))
			return values.get(id);
		else
			return new Int(0);
	}

	public void store(Identifier id, Value v) {
		values.put(id, v);
	}

	public void init(Identifier id, Type t, Value v) {
		types.put(id, t);
		values.put(id, v);
	}

	public LocalVar(List<VarDeclaration> decls) {
		for (VarDeclaration vd : decls)
			this.types.put(vd.identifier, vd.type);
	}
}