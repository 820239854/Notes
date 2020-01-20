Throws:
**IllegalMonitorStateException**
– if the current thread is not the owner of this object's monitor.
> You can't wait() on an object unless the current thread owns that object's monitor. To do that, you must synchronize on it
  The same rule applies to notify()/notifyAll() as well.
`So even a thread doesn't change anything,It also need to synchronize
 to notify others`

A thread becomes the owner of the object's monitor in one of three ways:
1. By executing a synchronized instance method of that object.
```java
public void add(int value){

  synchronized(this){
     this.count += value;   
  }
}
```
2. By executing the body of a synchronized statement that synchronizes on the object.
```Java
  public class MyClass {  
    public static void log2(String msg1, String msg2){
       synchronized(MyClass.class){
          log.writeln(msg1);
          log.writeln(msg2);  
       }
    }
  }
```
3. For objects of type Class, by executing a synchronized static method of that class.
```java
public static synchronized void add(int value){
    count += value;
}
```
>
synchronize运行时间和内存消耗都比用lock高?
synchronized原始采用的是CPU悲观锁机制，即线程获得的是独占锁。独占锁意味着其他线程只能依靠阻塞来等待线程释放锁。而在CPU转换线程阻塞时会引起线程上下文切换，当有很多线程竞争锁的时候，会引起CPU频繁的上下文切换导致效率很低。 而Lock用的是乐观锁方式。所谓乐观锁就是，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。

通过synchronized获得锁,之后进入wait状态的话,再次返回时将执行接下来的语句
必须用while循环每次确保状态通过
同时进入wait状态时锁将自动被释放

Ref:
[Java Synchronized Blocks]: url "http://tutorials.jenkov.com/java-concurrency/synchronized.html"
