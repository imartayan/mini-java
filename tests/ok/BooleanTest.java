class BooleanTest {
    public static void main(String[] args) {
        boolean a;
        boolean b;
        boolean c;
        int[] t;

        t = new int[0];

        // testing LowerThan and And
        a = (1 < 2);
        b = true;
        c = a && b;
        
        // testing IfStatement
        if (c) {
            System.out.println(1);
        } else {
            System.out.println(t[1]);
        }

        // Finishing tests on LowerThan, And and Not
        b = (2 < 1);
        c = a && b;
        if (!c) {
            System.out.println(1);
        } else {
            System.out.println(t[1]);
        }

    }
}
