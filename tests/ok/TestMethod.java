class CheckMethod{
    public static void main(String[] a){
        System.out.println(42);
    }
}

// Check argument type verification
class CheckMethodA{
    public int sum(int a, boolean b, int c) {
        return a + c;
    }
    public int test() {
        int res;
        res = this.sum(1, true, 2);
        return res;
    }
}

class CheckMethodObject {
    int value;
    public int setValue(int val) {
        value = val;
        return value;
    }
}

class CheckMethodObjectPriority extends CheckMethodObject {
    int priority;
    public int setPriority(int p) {
        priority = p;
        return priority;
    }
}

// Check that argument is a subtype of the expected type
class CheckMethodB {
    public int sumValues(CheckMethodObject a, CheckMethodObject b) {
        int valA;
        int valB;
        valA = a.setValue(1);
        valB = b.setValue(2);
        return valA + valB;
    }
    public int test() {
        CheckMethodObjectPriority objA;
        CheckMethodObject objB;
        objA = new CheckMethodObjectPriority();
        objB = new CheckMethodObject();
        return this.sumValues(objA, objB);
    }
}
