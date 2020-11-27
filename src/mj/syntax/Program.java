package mj.syntax;

import java.util.List;

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
			context.initAttributesTypes(cdec);
        }

        for (ClassDeclaration cdec : this.declarations) {
            context.inheritAttributesTypes(cdec.name);
        }

        this.main.typeCheck(context);

		for (ClassDeclaration cdec : this.declarations) {
			cdec.typeCheck(context);
		}
	}
}
