>https://mp.weixin.qq.com/s/ucWOYSND59bBH2sr3L887Q
#### 借助额外的变量  
最常用最普适的方法
![三个水桶](/assets/三个水桶.webp)
```JavaScript
var a = 1;
var b = 2;
var temp = a;
a = b;
b = temp;
```

#### 利用数字特性
* 利用加法
![加法暂存](/assets/加法暂存.webp)
>但是中间二者的和可能会发生溢出
```JavaScript
var a = 1;
var b = 2;
b = a + b;
a = b - a;
b = b - a;
```

* 利用减法
![减法暂存](/assets/减法暂存.webp)
>这种解法没有溢出的风险，理论上已经非常完美了
>这在两个数字都非常大，以至于两数之和无法用数字表示的时候非常有用。
```JavaScript
var a = 1;
var b = 2;
b = a - b;
a = a - b;
b = a + b;
```
#### 利用异或的特殊性
every element is its own additive inverse
>additive inverse 即互加相反数.也就是(a ^ a = 0)

证明:
异或位运算相同则为 0，不同则为 1
>零结合零为零,零结合一为一.
>也就是0与任何数字按位结合都为原数
>显然,位运算具有交换律和结合律
>~~交换律与结合律等价~~ 矩阵乘法可结合不可交换,(可交换不可结合的反例没想出)

于是对于两个数字，a 和 b。则有 a ^ a ^ b 就等于 b
异或的中间值就具有了暂存的效果
```JavaScript
var a = 1;
var b = 2;
b = a ^ b;
a = a ^ b;
b = a ^ b;
```
