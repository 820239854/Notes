//经过delete以后,原有元素的index改变
//  但是for循环中的i每次都单向加一,类似于刻舟求剑
for (int i=0; i<str.length(); i++){
    if (str.charAt(i) == ' '){
        str = str.deleteCharAt(i);
    }
}


// 删除元素后会导致索引改变
// 每一步只有元素不变才能继续向前,否则只能原地踏步
int i = 0;
while (i < str.length()){
    if (str.charAt(i) == ' '){
        str.deleteCharAt(i);
    }else {
        i++;
    }
}
