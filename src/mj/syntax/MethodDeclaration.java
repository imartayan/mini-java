package mj.syntax;

import java.util.List;

import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

import mj.Printer;

public class MethodDeclaration {

	public final Type resType;
	public final Identifier name;
	public final List<VarDeclaration> params;
	public final List<VarDeclaration> declarations;
	public final Statement body;
	public final Expression result;


	public MethodDeclaration(Type resType, Identifier name, List<VarDeclaration> params,
			List<VarDeclaration> declarations, Statement body, Expression result) {
		this.resType = resType;
		this.name = name;
		this.params = params;
		this.declarations = declarations;
		this.body = body;
		this.result = result;
	}

	public void print(Printer pp) {
		pp.indent();
		System.out.print("public ");
		resType.print();
		System.out.print(" ");
		name.print();
		System.out.print("(");
		int i = 0 ;
		for (VarDeclaration vd: params) {
			if (i++ > 0) System.out.print(", ");
			vd.type.print();
			System.out.print(" ");
			vd.identifier.print();
		}
		System.out.println(") {");
		assert (body instanceof Block);
		Block b = (Block) body;
		pp.incrLevel();
		for (VarDeclaration vd: declarations) {
			pp.indent();
			vd.type.print();
			System.out.print(" ");
			vd.identifier.print();
			System.out.println(";");
		}
		for (Statement stmt: b.statements)
			stmt.print(pp);
		pp.indent();
		System.out.print("return ");
		result.print();
		System.out.println(";");
		pp.decrLevel();
		pp.indent();
		System.out.println("}");
	}

	public void typeCheck(TypeChecker context) throws TypeError {
		for(VarDeclaration newParam : this.params) {
			context.addVariable(newParam.identifier, newParam.type);
		}
		for(VarDeclaration newVar : this.declarations) {
			context.addVariable(newVar.identifier, newVar.type);
            context.addInitVariable(newVar.identifier, false);
        }
		this.body.typeCheck(context);
		Type returnType = this.result.type(context);
		if(!returnType.isSubtypeOf(this.resType, context)) {
			throw new TypeError("Type mismatch : cannot convert from " + this.resType.toString() + " to " + returnType.toString());
		}
        for (VarDeclaration newParam : this.params) {
            context.removeVariable(newParam.identifier);
        }
        for (VarDeclaration newVar : this.declarations) {
            context.removeVariable(newVar.identifier);
            context.removeInitVariable(newVar.identifier);
        }
	}
}
