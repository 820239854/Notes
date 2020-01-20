避免字符转义的歧义性
>  System.out.println('\n');
  System.out.println("\\");

绝对路径与相对路径
* \\test  相当于  盘符:\test 的简便写法
* 打印当前工作目录  System.out.println(System.getProperty("user.dir"));
> 即绝对路径 = user.dir + 指定值
* 名称分隔符在 Microsoft Windows 系统上为'\\'。


File类是与实际文件相互平行的,只有文件读写时才交互

> File的构造方法
```java
    //File (String pathname) 创建File类对象
    File file = new File("d:\\a.txt");

    //d:\b.txt  => parenet: d:\   +  b.txt
    //File (String parent, Sting child)
    File file1 = new File("d:\\", "b.txt");

    //File (File parent, String child)
    File file2 = new File(new File("d:\\"), "b.txt");
```
