try {
    Integer.valueOf(new StringBuffer(num).toString());
}
catch(Exception e){
    res = Integer.MAX_VALUE;
}
