class TestAlloc{
    public static void main(String[] args){
        int a;
        boolean b;
        int[] t;
        TestAllocA obj;
        a = 1;
        b = false;
        t = new int[3];
        t[0] = 5;
        obj = new TestAllocA();
        a = obj.init();
    }
}

class TestAllocA {
    int a;
    boolean b;
    public int init() {
        a = 12;
        b = true;
        return a;
    }
}
