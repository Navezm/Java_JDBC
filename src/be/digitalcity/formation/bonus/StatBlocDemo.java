package be.digitalcity.formation.bonus;

public class StatBlocDemo {

    static class Test {
        static int i = 0;

        static {
            i = 10;
            System.out.println("passage par le bloc");
        }
    }

    public static void main(String[] args) {
        System.out.println(Test.i);
    }

}
