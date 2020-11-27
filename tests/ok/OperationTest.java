class OperationTest {
    public static void main(String[] args) {
        int a;
        int b;
        int[] t;

        a = 1;
        b = 2;
        t = new int[1];

        t[0] = b;
        b = a;
        a = t[0];
        b = a + b;
        t[0] = a * b;
        b = b - b;

        System.out.println(a);
    }
}
