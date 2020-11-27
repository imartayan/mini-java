class TestInherit{
    public static void main(String[] a){
        TestInheritC obj;
        boolean res;
        obj = new TestInheritC();
        res = obj.initC();
    }
}

class TestInheritA{
    int a;
    public boolean initA() {
        a = 1;
        return true;
    }
}

// Test inheritance from super class
class TestInheritB extends TestInheritA {
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
class TestInheritC extends TestInheritB {
    int c;
    public boolean initC() {
        boolean res;
        res = this.initA();
        c = a + 2;
        return res;
    }
}
