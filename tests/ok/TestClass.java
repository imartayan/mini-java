class TestClass{
    public static void main(String[] a){
        TestClassA objA;
        TestClassB objB;
        TestClassC objC;
        boolean res;
        objA = new TestClassA();
        objB = new TestClassB();
        objC = new TestClassC();
        res = objA.initA();
        res = objB.initB();
        res = objC.initC();
    }
}

class TestClassA{
    int a;
    public boolean initA() {
        a = 1;
        return true;
    }
}

// Test inheritance from super class
class TestClassB extends TestClassA {
    int b;
    public boolean initB() {
        boolean res;
        res = this.initA();
        b = a + 1;
        return res;
    }
}

// Test that other class attributes are not kept in memory
class TestClassC extends TestClassA {
    boolean b;
    public boolean initC() {
        boolean res;
        res = this.initA();
        b = false;
        return res;
    }
}
