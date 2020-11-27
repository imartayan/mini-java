class TestProgram{
    public static void main(String[] a){
        TestProgramC obj;
        boolean res;
        obj = new TestProgramC();
        res = obj.initC();
    }
}

class TestProgramA{
    int a;
    public boolean initA() {
        a = 1;
        return true;
    }
}

// Test inheritance from super class
class TestProgramB extends TestProgramA {
    int b;
    public boolean initB() {
        boolean res;
        res = this.initA();
        b = a + 1;
        return res;
    }
}

// Test inheritance when the attribute is not
// directly defined in the super class
class TestProgramC extends TestProgramB {
    int c;
    public boolean initC() {
        boolean res;
        res = this.initA();
        c = a + 2;
        return res;
    }
}
