class TestMethod{
    public static void main(String[] a){
        TestMethodA objA;
        TestMethodB objB;
        int sA;
        int sB;
        objA = new TestMethodA();
        objB = new TestMethodB();
        sA = objA.test();
        sB = objA.test();
    }
}

// Test argument type verification
class TestMethodA{
    public int sum(int a, boolean b, int c) {
        return a + c;
    }
    public int test() {
        int res;
        res = this.sum(1, true, 2);
        return res;
    }
}

class TestMethodObject {
    int value;
    public int setValue(int val) {
        value = val;
        return value;
    }
}

class TestMethodObjectPriority extends TestMethodObject {
    int priority;
    public int setPriority(int p) {
        priority = p;
        return priority;
    }
}

// Test that argument is a subtype of the expected type
class TestMethodB {
    public int sumValues(TestMethodObject a, TestMethodObject b) {
        int valA;
        int valB;
        valA = a.setValue(1);
        valB = b.setValue(2);
        return valA + valB;
    }
    public int test() {
        TestMethodObjectPriority objA;
        TestMethodObject objB;
        objA = new TestMethodObjectPriority();
        objB = new TestMethodObject();
        return this.sumValues(objA, objB);
    }
}
