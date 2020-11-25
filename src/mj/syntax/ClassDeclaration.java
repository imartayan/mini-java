package mj.syntax;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import mj.type_checker.TypeError;
import mj.type_checker.TypeChecker;

import mj.Printer;

public class ClassDeclaration {

	public final Identifier name;
	public final Optional<Identifier> superClass;
	public final List<VarDeclaration> varDeclarations;
	public final List<MethodDeclaration> methodDeclarations;

	public ClassDeclaration(Identifier name, Optional<Identifier> superClass, List<VarDeclaration> varDeclarations,
			List<MethodDeclaration> methodDeclarations) {
		this.name = name;
		this.superClass = superClass;
		this.varDeclarations = varDeclarations;
		this.methodDeclarations = methodDeclarations;
	}

	public void print(Printer pp) {
		pp.indent();
		System.out.print("class ");
		name.print();
		if (superClass.isPresent()) {
			System.out.print(" extends ");
			superClass.get().print();
		}
		System.out.println(" {");
		pp.incrLevel();
		for (VarDeclaration vd : varDeclarations) {
			pp.indent();
			vd.type.print();
			System.out.print(" ");
			vd.identifier.print();
			System.out.println(";");
		}
		for (MethodDeclaration md : methodDeclarations) {
			System.out.println("");
			md.print(pp);
		}
		pp.decrLevel();
		pp.indent();
		System.out.println("}");
	}

	public void typeCheck(TypeChecker t) throws TypeError {
        t.addClassVariables(this.name);
		for(MethodDeclaration newMethod : this.methodDeclarations) {
			newMethod.typeCheck(t);
        }
        t.removeClassVariables(this.name);
	}

}
