package mj.syntax;

public class VarDeclaration {
	public final Type type;
	public final Identifier identifier;
	
	public VarDeclaration(Type type, Identifier identifier) {
		this.type = type;
		this.identifier = identifier;
	}
}

