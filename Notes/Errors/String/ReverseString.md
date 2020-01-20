```Java
class ReverseString {
  String reverse(String inputString) {
    int length = inputString.length();
    for (int i=0; i<length/2; i++) {
      char tmp;
      tmp = inputString.charAt(i);      
      inputString.charAt(length-1 - i) = tmp;
    }
    return inputString;
  }
}
```

```Java
public char charAt(int index) {
    if ((index < 0) || (index >= value.length)) {
        throw new StringIndexOutOfBoundsException(index);
    }
    return value[index];
}
```
charAt返回的是字符值而不是字符变量,所以 *inputString.charAt* 只能作为getter,不能用作变量修改


>另解 利用StringBuilder

```java
    StringBuilder builder = new StringBuilder();        
    for (int i = inputString.length() - 1; i >= 0; i--) {
        char ch = inputString.charAt(i);
        builder.append(ch);
    }
```
