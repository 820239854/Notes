package PackageA;

public class TestSuperClass {
    protected static int A = 1;
}

//同包内所有类都能访问protedted域值
class TestClass{
    public void get(){
        System.out.println(TestSuperClass.A);
    }
    public static void main(String[] args) {
        System.out.println(TestSuperClass.A);
    }
}
