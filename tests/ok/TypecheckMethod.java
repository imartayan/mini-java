class CheckMethod{
    public static void main(String[] a){
        System.out.println(42);
    }
}

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
