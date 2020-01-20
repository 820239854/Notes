> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://mp.weixin.qq.com/s/tKuCVRbWwUPeUEI3Vtnp6w

前言
--

整理了一下内部类的相关知识，算是比较全，比较基础的，希望大家一起学习进步。![](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGnn8yrPc7X5GvsQiadhUNfkTokcEka8HEHXnHTal8xePZpUTGbZC17HlYtdjFWfsNq97xvbzKaicWQ/640?wx_fmt=png)

### 一、什么是内部类？

在 Java 中，可以将一个类的定义放在另外一个类的定义内部，这就是**内部类**。内部类本身就是类的一个属性，与其他属性 定义方式一致。

一个内部类的例子：

```java
public class Outer {
    private int radius = 1;
    public static int count = 2;

    public Outer() {
    }

    class inner{
        public void visitOuter() {
            System.out.println("visit outer private member variable:" + radius);
            System.out.println("visit outer static variable:" + count);
        }
    }
}

```

### 二、内部类的种类

内部类可以分为四种：**成员内部类、局部内部类、匿名内部类和静态内部类**。

![](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGnn8yrPc7X5GvsQiadhUNfkbQnK8l9NZWIliauxwEiato7jA4z18WwoSTabEX5at59ksKHQb5eiaC2FQ/640?wx_fmt=png)

#### 静态内部类

定义在类内部的静态类，就是静态内部类。

```
public class Outer {
    private static int radius = 1;
    static class StaticInner {
        public void visit() {
            System.out.println("visit outer static  variable:" + radius);
        }
    }
}

```

静态内部类可以访问外部类所有的静态变量，而不可访问外部类的非静态变量；静态内部类的创建方式如下：

```
Outer.StaticInner inner = new Outer.StaticInner();
inner.visit();

```

#### 成员内部类

定义在类内部，成员位置上的非静态类，就是成员内部类。

```
public class Outer {
    private static  int radius = 1;
    private int count =2;
     class Inner {
        public void visit() {
            System.out.println("visit outer static  variable:" + radius);
            System.out.println("visit outer   variable:" + count);
        }
    }
}

```

成员内部类可以访问外部类所有的变量和方法，包括静态和非静态，私有和公有。成员内部类依赖于外部类的实例，它的创建方式如下：

```
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
inner.visit();

```

#### 局部内部类

定义在方法中的内部类，就是局部内部类。

```
public class Outer {
    private  int out_a = 1;
    private static int STATIC_b = 2;
    public void testFunctionClass(){
        int inner_c =3;
        class Inner {
            private void fun(){
                System.out.println(out_a);
                System.out.println(STATIC_b);
                System.out.println(inner_c);
            }
        }
        Inner  inner = new Inner();
        inner.fun();
    }
    public static void testStaticFunctionClass(){
        int d =3;
        class Inner {
            private void fun(){
                // System.out.println(out_a); 编译错误，定义在静态方法中的局部类不可以访问外部类的实例变量
                System.out.println(STATIC_b);
                System.out.println(d);
            }
        }
        Inner  inner = new Inner();
        inner.fun();
    }
}

```

定义在实例方法中的局部类可以访问外部类的所有变量和方法，定义在静态方法中的局部类只能访问外部类的静态变量和方法。局部内部类的创建方式，在对应方法内， `new内部类()`，如下：

```
 public static void testStaticFunctionClass(){
    class Inner {
    }
    Inner  inner = new Inner();
 }

```

#### 匿名内部类

匿名内部类就是没有名字的内部类，日常开发中使用的比较多。

```
public class Outer {
    private void test(final int i) {
        new Service() {
            public void method() {
                for (int j = 0; j < i; j++) {
                    System.out.println("匿名内部类" );
                }
            }
        }.method();
    }
 }
 //匿名内部类必须继承或实现一个已有的接口
 interface Service{
    void method();
}

```

除了没有名字，匿名内部类还有以下特点：

*   匿名内部类必须继承一个抽象类或者实现一个接口。

*   匿名内部类不能定义任何静态成员和静态方法。

*   当所在的方法的形参需要被匿名内部类使用时，必须声明为 final。

*   匿名内部类不能是抽象的，它必须要实现继承的类或者实现的接口的所有抽象方法。


匿名内部类创建方式：

```
new 类/接口{
  //匿名内部类实现部分
}

```

### 三、内部类的优点

我们为什么要使用内部类呢？因为它有以下优点：

*   一个内部类对象可以访问创建它的外部类对象的内容，包括私有数据！

*   内部类不为同一包的其他类所见，具有很好的封装性；

*   内部类有效实现了 “多重继承”，优化 java 单继承的缺陷。

*   匿名内部类可以很方便的定义回调。


#### 一个内部类对象可以访问创建它的外部类对象的内容，包括私有数据！

```
public class Outer {
    private  int radius = 1;
    protected void test(){
        System.out.println("我是外部类方法");
    }
    class Inner {
        public void visit() {
            System.out.println("访问外部类变量" + radius);
            test();
        }
    }
}

```

我们可以看到，内部类 Inner 是可以访问外部类 Outer 的私有变量 radius 或者方法 test 的。

#### 内部类不为同一包的其他类所见，具有很好的封装性

当内部类使用 private 修饰时，这个类就对外隐藏了。当内部类实现某个接口，并且进行向上转型，对外部来说，接口的实现已经隐藏起来了，很好体现了封装性。

```
//提供的接口
interface IContent{
    String getContents();
}
public class Outer {
     //私有内部类屏蔽实现细节
     private class PContents implements IContent{
         @Override
         public String getContents() {
             System.out.println("获取内部类内容");
             return "内部类内容";
         }
     }
    //对外提供方法
    public IContent getIContent() {
        return new PContents();
    }
    public static void main(String[] args) {
        Outer outer=new Outer();
        IContent a1=outer.getIContent();
        a1.getContents();
    }
}

```

我们可以发现，Outer 外部类对外提供方法 getIContent，用内部类实现细节，再用 private 修饰内部类，屏蔽起来，把 Java 的封装性表现的淋漓尽致。

#### 内部类有效实现了 “多重继承”，优化 java 单继承的缺陷。

我们知道 Java 世界中，一个类只能有一个直接父类，即以单继承方式存在。但是内部类让 “多继承” 成为可能：

> *   一般来说，内部类继承某个类或者实现某个接口，内部类的代码操作创建它的外围类的对象。内部类提供了某种进入其外围类的窗口。
>     
> *   每个内部类都可以队里的继承自一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类没有影响
>     
> *   接口解决了部分问题，一个类可以实现多个接口，内部类允许继承多个非接口类型（类或抽象类）。
>     

一份来自 Java 编程思想，内部类实现 “多继承” 的温暖如下：

```
class D {}
abstract class E{}
class Z extends D {
E makeE(){ return new E() {}; }
}
public class MultiImplementation {
static void takesD(D d) {}
static void takesE(E e) {}
public static void main(String[] args){
Z z = new Z();
takesD(z);
takesE(z.makeE());
}
}

```

代码中出现了一个类 D，一个抽象类 E。然后，用类 Z 继承 D，内部类构造返回 E。因此，当你不管要的是 D 还是 E，Z 都可以应付，“多继承” 的特点完美表现出来。

#### 匿名内部类可以很方便的定义回调。

什么是**回调**？假设有两个类 A 和 B，在 A 中调用 B 的一个方法 b，而 b 在执行又调用了 A 的方法 c，则 c 就称为回调函数。

![](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGnn8yrPc7X5GvsQiadhUNfkkibftgAu3brLlJy0ufGhpF5IicicTks9uPXfwBmDhckyIibHFysQNxT31w/640?wx_fmt=png)当然，回调函数也可以是 a 函数，这就是**同步回调**，最简单的回调方式。回调应用场景挺多的，如 android 中的事件监听器。匿名内部类可以很方便的定义回调，看个例子

```
//定义一个CallBack接口
public interface CallBack {
    void execute();
}
public class TimeTools {
    /**
     * 测试函数调用时长，通过定义CallBack接口的execute方法
     * @param callBack
     */
    public   void  testTime(CallBack callBack) {
        long  beginTime = System.currentTimeMillis(); //记录起始时间
        callBack.execute(); ///进行回调操作
        long  endTime = System.currentTimeMillis(); //记录结束时间
        System.out.println("[use time]:"  + (endTime - beginTime)); //打印使用时间
    }
    public   static   void  main(String[] args) {
        TimeTools tool = new  TimeTools();
        tool.testTime(new  CallBack(){
            //匿名内部类，定义execute方法
            public   void  execute(){
                TestTimeObject testTimeObject = new TestTimeObject();
                testTimeObject.testMethod();
            }
        });
    }
}

```

在调用 testTime() 测时间的时候，用匿名内部类实现一个方法 execute()，在该方法内搞事情（执行目标函数），执行完后，又回到 testTime 方法，很好了实现测试函数调用时长的功能。显然，**匿名内部类让回调实现变得简单**。

### 四、内部类的底层

#### 内部类标志符

每个内部类都会产生一个. class 文件，其中包含了如何创建该类型的对象的全部信息。内部类也必须生成一个. class 文件以包含它们的 Class 对象信息。内部类文件的命名有严格规则：外围类的名字 +$+ 内部类的名字。

一个简单例子：

```
public class Outer {
    class Inner{
    }
}

```

javac Outer.java 编译完成后， 生成的 class 文件如下：

![](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGnn8yrPc7X5GvsQiadhUNfkR4hX2R503licE7xfADVY0btK3HJNxThibBTPoAmicLKBaaIlDf1ibA4icQA/640?wx_fmt=png)

如果内部类是匿名的，编译器会简单地产生一个数字作为其标识符。如果内部类是嵌套在别的内部类之中（静态内部类），只需直接将它们的名字加在其外围类标志符与 “$” 的后面。

#### 为什么内部类可以访问外部类的成员，包括私有数据？

由上一小节，我们知道内部类可以访问外部类的成员，包括私有数据。那么它是怎么做到的呢？接下来揭晓答案。

先看这个简单地例子：

```
public class Outer {
    private int i = 0;
    class Inner{
        void method(){
            System.out.println(i);
        }
    }
}

```

一个外部类 Outer，一个外部类私有属性 i，一个内部类 Inner，一个内部类方法 method。内部类方法访问了外部类属性 i。

先编译，javac Outer.java，生成. class 文件，如下：

![](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGnn8yrPc7X5GvsQiadhUNfk2JHvH1FhePJpPJdv0dczib1H0icY2Oz4RiaPYBfjnNRKYbkMqJdyvlJpg/640?wx_fmt=png)

用命令 `javap-classpath.-vOuter$Inner`，反编译 Outter$Inner.class 文件得到以下信息：

![](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGnn8yrPc7X5GvsQiadhUNfk2nBxM0fHialgTeHZotDyOO62LTgXbL32DVXZQtxEArlS6XTrsZxL7Bw/640?wx_fmt=png)

我们可以看到这一行，它是一个指向外部类对象的指针：

```
final innerclass.Outer this$0;

```

虽然编译器在创建内部类时为它加上了一个指向外部类的引用， 但是这个引用是怎样赋值的呢？**编译器会为内部类的构造方法添加一个参数，进行初始化**， 参数的类型就是**外部类的类型**，如下：

```
innerclass.Outer$Inner(innerclass.Outer);

```

成员内部类中的 Outter this&0 指针便指向了外部类对象，因此可以在成员内部类中随意访问外部类的成员。

#### 局部内部类和匿名内部类访问局部变量的时候，为什么变量必须要加上 final？

局部内部类和匿名内部类访问局部变量的时候，为什么变量必须要加上 final 呢？它内部原理是什么呢？

先看这段代码：

```
public class Outer {
    void outMethod(){
        final int a =10;
        class Inner {
            void innerMethod(){
                System.out.println(a);
            }
        }
    }
}

```

反编译（Outer$1Inner）得到以下信息![](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGnn8yrPc7X5GvsQiadhUNfk7NqQwm2uQZmmNneN1Fqas2dw6Qp04xicepdN1GB5mHZ3K7W7KIhkLbA/640?wx_fmt=png)

我们在内部类 **innerMethod** 方法中，可以看到以下这条指令：

```
3: bipush   10

```

*   它表示将常量 10 压入栈中，表示使用的是一个本地局部变量。

*   其实，如果一个变量的值在编译期间可以确定（demo 中确定是 10 了），则编译器会默认在匿名内部类（局部内部类）的常量池中添加一个内容相等的字面量或直接将相应的字节码嵌入到执行字节码中。

*   酱紫可以确保局部内部类使用的变量与外层的局部变量区分开，它们只是值相等而已。


以上例子，为什么要加 final 呢？是因为**生命周期不一致**， 局部变量直接存储在栈中，当方法执行结束后，非 final 的局部变量就被销毁。而局部内部类对局部变量的引用依然存在，如果局部内部类要调用局部变量时，就会出错。加了 final，可以确保局部内部类使用的变量与外层的局部变量区分开，解决了这个问题。

我们再来看一段代码，其实就是把变量 a 挪到传参方式进来

```
public class Outer {
    void outMethod(final int a){
        class Inner {
            void innerMethod(){
                System.out.println(a);
            }
        }
    }
}

```

反编译可得![](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGnn8yrPc7X5GvsQiadhUNfk9wxdwbfwhLU3vMYAlwic60C2BtEWqn86p49HkyJAdibSmy7mmhFD3CEg/640?wx_fmt=png)我们看到匿名内部类 Outer$1Inner 的构造器含有两个参数，一个是指向外部类对象的引用，一个是 int 型变量，很显然，这里是将变量 innerMethod 方法中的形参 a 以参数的形式传进来对匿名内部类中的拷贝（变量 a 的拷贝）进行赋值初始化。

那么，新的问题又来了，既然在 innerMethod 方法中访问的变量 a 和 outMethod 方法中的变量 a 不是同一个变量，当在 innerMethod 方法中修改 a 会怎样？那就会造成**数据不一致的问题**了。

怎么解决呢？使用 **final 修饰符**，final 修饰的引用类型变量，不允许指向新的对象，这就解决数据不一致问题。**注意：** 在 Java8 中，被局部内部类引用的局部变量，默认添加 final，所以不需要添加 final 关键词。

### 五、内部类的应用场景。

一般我们在哪些场景下使用内部类呢？

#### 场景之一：一些多算法场合

一些算法多的场合，也可以借助内部类，如：

```
Arrays.sort(emps,new Comparator(){
  Public int compare(Object o1,Object o2)
  {
   return ((Employee)o1).getServedYears()-((Employee)o2).getServedYears();
  }
})；

```

#### 场景二：解决一些非面向对象的语句块。

如果一些语句块，包括 if…else 语句，case 语句等等比较多，不好维护扩展，那么就可以借助内部类 + 设计模式解决。

#### 场景之三：适当使用内部类，使得代码更加灵活和富有扩展性。

适当的使用内部类，可以使得你的代码更加灵活和富有扩展性。如 JDK 的 lamda 表达式，用内部类非常多，代码优雅很多。如下

```
// JDK8 Lambda表达式写法
new Thread(() -> System.out.println("Thread run()")).start();

```

#### 场景四：当某个类除了它的外部类，不再被其他的类使用时。

如果一个类，不能为其他的类使用；或者出于某种原因，不能被其他类引用。那我们就可以考虑把它实现为内部类。数据库连接池就是这样一个典型例子。

### 六、内部类常见面试题

最后，我们来看一道经典内部类面试题吧。

```
public class Outer {
    private int age = 12;
    class Inner {
        private int age = 13;
        public void print() {
            int age = 14;
            System.out.println("局部变量：" + age);
            System.out.println("内部类变量：" + this.age);
            System.out.println("外部类变量：" + Outer.this.age);
        }
    }
    public static void main(String[] args) {
        Outer.Inner in = new Outer().new Inner();
        in.print();
    }
}

```

运行结果：![](https://mmbiz.qpic.cn/mmbiz_png/sMmr4XOCBzGnn8yrPc7X5GvsQiadhUNfkOCxaxLAjQFB3XdmxUnEeawrY80MADhPKQMaadpiaGc0SXp79mmQkPnQ/640?wx_fmt=png)

### 参考与感谢

*   《Java 编程思想》

*   Java 中的内部类（回调）

*   Java 进阶 ——— 局部内部类访问局部变量为什么必须加 final 关键字

*   Java 内部类详解

*   幕后英雄的用武之地——浅谈 Java 内部类的四个应用场景


### 个人公众号

![](https://mmbiz.qpic.cn/mmbiz_jpg/sMmr4XOCBzGnn8yrPc7X5GvsQiadhUNfk8vvz8TY1OZInqPW1RFlnGJRe2tMvD6luPcV8jezF8OLfiawibMQ1icdtg/640?wx_fmt=jpeg)

*   如果你是个爱学习的好孩子，可以关注我公众号，一起学习讨论。

*   如果你觉得本文有哪些不正确的地方，可以评论，也可以关注我公众号，私聊我，大家一起学习进步哈。
