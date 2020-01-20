package PackageB;
import PackageA.TestSuperClass;

//其他类无法在其方法体访问父类protected域值，即使中通过创建子类也无法间接访问
//这样的设计可以使得其他类只能访问同包子类的protected域值，同时让子类可以跨包连接父类
// This restriction is made so that you can’t abuse the protected mechanism
//     by forming subclasses just to gain access to the protected fields.
public class TestClass{
    public void get(){
        System.out.println(TestSuperClass.A);
    }
    public static void main(String[] args) {
        System.out.println(TestSuperClass.A);
    }
}

    // 子类在自身代码块中(main函数也是)可以跨包访问父类中protected域值
class TestSubClass1 extends TestSuperClass{
    public void get(){
        System.out.println(super.A);
    }

    public static void main(String[] args) {
        System.out.println(TestSuperClass.A);
    }
}
