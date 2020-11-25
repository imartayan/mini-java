package mj.syntax;

import java.util.List;

import mj.Printer;
import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

public class MainClass {
	public final Identifier name;
	public final Identifier param;
	public final List<VarDeclaration> declarations;
	public final Statement body;

	public MainClass(Identifier name, Identifier param, List<VarDeclaration> declarations, Statement body) {
		this.name = name;
		this.param = param;
		this.declarations = declarations;
		this.body = body;
	}

	public void typeCheck(TypeChecker context) throws TypeError {
		for (VarDeclaration varDec : declarations) {
            context.addVariable(varDec.identifier, varDec.type);
        }
        this.body.typeCheck(context);
		for (VarDeclaration varDec : declarations) {
            context.removeVariable(varDec.identifier);
        }
	}

	public void print(Printer pp) {
		System.out.print("class ");
		name.print();
		System.out.println(" {");
		pp.incrLevel();
		pp.indent();
		System.out.print("public static void main(String[] ");
		param.print();
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
		for (Statement stmt: b.statements) {
			pp.indent();
			stmt.print(pp);
		}
		pp.decrLevel();
		pp.indent();
		System.out.println("}");
	}

}
