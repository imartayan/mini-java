package mj.type_checker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mj.Couples;
import mj.syntax.ClassDeclaration;
import mj.syntax.Identifier;
import mj.syntax.MethodDeclaration;
import mj.syntax.Type;
import mj.syntax.VarDeclaration;
import mj.parser.Parser;
import mj.syntax.Program;

public class TypeChecker {
    protected Map<Identifier, Map<Identifier, Type>> classVariables = new Hashtable<>();
    protected Map<Identifier, Map<Identifier, Couples<Type, List<Type>>>> classMethods = new Hashtable<>();

    protected Map<Identifier, Identifier> inheritance = new Hashtable<>();

    protected Identifier currentClass;
    protected Map<Identifier, Type> currentVariables = new Hashtable<>();
    protected Map<Identifier, Boolean> initializedVariables = new Hashtable<>();

    // Simple methods to check whether the identifier corresponds to a class, a method or a variable
    public boolean isClass(Identifier classId) {
        return this.classVariables.containsKey(classId);
    }

    public boolean isMethod(Identifier classId, Identifier methodId) {
        return this.classVariables.get(classId).containsKey(methodId);
    }

    public boolean isVariable(Identifier varId) {
        return this.currentVariables.containsKey(varId);
    }

    // Checks whether the variable was declared locally or not
    public boolean isLocal(Identifier varId) {
        return this.initializedVariables.containsKey(varId);
    }

    // Checks whether the variable was already initialized or not
    public boolean isInitialized(Identifier varId) {
        return this.initializedVariables.get(varId);
    }

    // Checks if the first class inherits from the second class
    public boolean inheritsFrom(Identifier classId, Identifier superId) {
        Identifier parentId = classId;
        while (!parentId.equals(superId) && this.inheritance.containsKey(parentId)) {
            parentId = this.inheritance.get(parentId);
        }
        return parentId.equals(superId);
    }

    // Look up types associated to the given variable or method
    public Type lookupVar(Identifier varId) {
        return this.currentVariables.get(varId);
    }

    public Couples<Type, List<Type>> lookupMethod(Identifier classId, Identifier methodId) {
        return this.classMethods.get(classId).get(methodId);
    }

    // Getting or setting the current class
    public Identifier getCurrentClass() {
        return this.currentClass;
    }

    public void setCurrentClass(ClassDeclaration classDec) {
        this.currentClass = classDec.name;
    }

    // Adding or removing variables and their types to the set of current variables
    public void addVariable(Identifier varId, Type type) {
        this.currentVariables.put(varId, type);
    }

    public void addClassVariables(ClassDeclaration classDec) {
        this.classVariables.get(classDec.name).forEach((k, v) -> this.addVariable(k, v));
    }

    public void removeVariable(Identifier varId) {
        this.currentVariables.remove(varId);
    }

    public void removeClassVariables(ClassDeclaration classDec) {
        this.classVariables.get(classDec.name).forEach((k, v) -> this.removeVariable(k));
    }

    // Adding or removing variables to the set of local variables
    // and specifying whether they are initialized or not
    public void addLocalVariable(Identifier varId, boolean initialized) {
        this.initializedVariables.put(varId, initialized);
    }

    public void removeLocalVariable(Identifier varId) {
        this.initializedVariables.remove(varId);
    }

    // Initialize the inheritance table based on class declarations of the program
    public void getInheritance(List<ClassDeclaration> declarations) {
        for (ClassDeclaration classDec : declarations) {
            if (classDec.superClass.isPresent()) {
                this.inheritance.put(classDec.name, classDec.superClass.get());
            }
        }
    }

    // Initialize class attributes type from the class declaration
    // This method doesn't manage inherited attributes
    public void initAttributesTypes(ClassDeclaration classDec) {
        Map<Identifier, Type> varMap = new Hashtable<>();
        Map<Identifier, Couples<Type, List<Type>>> methodsMap = new Hashtable<>();

        for (VarDeclaration newVar : classDec.varDeclarations) {
            varMap.put(newVar.identifier, newVar.type);
        }

        for (MethodDeclaration newMethod : classDec.methodDeclarations) {
            List<Type> newParamsType = new LinkedList<>();
            for (VarDeclaration newParam : newMethod.params) {
                newParamsType.add(newParam.type);
            }
            methodsMap.put(newMethod.name, new Couples<>(newMethod.resType, newParamsType));
        }

        this.classVariables.put(classDec.name, varMap);
        this.classMethods.put(classDec.name, methodsMap);
    }

    // Copy attributes type from inherited classes when they exist
    public void inheritAttributesTypes(Identifier classId) {
        if (this.inheritance.containsKey(classId)) {
            Identifier parentId = this.inheritance.get(classId);
            // make sure that parent class has all of its attributes
            inheritAttributesTypes(parentId);
            // copy variables from parent class
            Map<Identifier, Type> classVarMap = this.classVariables.get(classId);
            this.classVariables.get(parentId).forEach((k, v) -> {
                // don't overwrite existing variables
                if (!classVarMap.containsKey(k)) {
                    classVarMap.put(k, v);
                }
            });
            // copy methods from parent class
            Map<Identifier, Couples<Type, List<Type>>> classMethMap = this.classMethods.get(classId);
            this.classMethods.get(parentId).forEach((k, v) -> {
                // don't overwrite existing methods
                if (!classMethMap.containsKey(k)) {
                    classMethMap.put(k, v);
                }
            });
        }
    }

    public static void main(String[] arg) {
        try {
            String filename;
            if (arg.length == 0) {
                filename = "tests/ok/BinaryTree.java";
            } else {
                filename = arg[0];
            }
            Program p = Parser.run(new FileInputStream(filename));
            TypeChecker t = new TypeChecker();
            p.typeCheck(t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TypeError e) {
            e.printStackTrace();
        }
    }
}
