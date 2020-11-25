package mj.syntax;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import mj.type_checker.TypeChecker;
import mj.type_checker.TypeError;

import mj.Printer;

public class Program {
	public final MainClass main;
	public final List<ClassDeclaration> declarations;

	public Program(MainClass main, List<ClassDeclaration> declarations) {
		this.main = main;
		this.declarations = declarations;
	}

	public void print() {
		Printer pp = new Printer();
		main.print(pp);
		for (ClassDeclaration cd : declarations) {
			System.out.println();
			cd.print(pp);
		}
	}

	public void typeCheck(TypeChecker context) throws TypeError {
        context.getInheritance(this.declarations);

		for (ClassDeclaration cdec : this.declarations) {
			context.getClassAttributesTypes(cdec);

		}
		for (ClassDeclaration cdec : this.declarations) {
			Map<Identifier, Type> localVars = context.getClassVars(cdec.name);
			cdec.typeCheck(context);
			context.removeVariables(localVars);
		}
	}
}
