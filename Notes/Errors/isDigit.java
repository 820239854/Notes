//错因: str.charAt(0) < '0' && str.charAt(0) > '9' 永远不会被触发
if (str.charAt(0) != '-' && str.charAt(0) != '+'
         && str.charAt(0) < '0' && str.charAt(0) > '9'){
    return 0;


// !Character.isDigit(str.charAt(0)) 的
//  等价命题为    str.charAt(0) >= '0' && str.charAt(0) <= '9'
//  逆否命题应该为str.charAt(0) < '0' || str.charAt(0) > '9'(应该是并而不是交集(&&))
if (str.charAt(0) != '-' && str.charAt(0) != '+'
         && !Character.isDigit(str.charAt(0))){
    return 0;
