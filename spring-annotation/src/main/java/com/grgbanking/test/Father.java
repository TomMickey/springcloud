package com.grgbanking.test;

import com.grgbanking.annotation.MyTestAnnotation;

@MyTestAnnotation
public class Father {
}

class Son extends Father{

}

class Test{
    public static void main(String[] args) {
        Class<Son> sonClass = Son.class;
        MyTestAnnotation annotation = sonClass.getAnnotation(MyTestAnnotation.class);
        System.out.println(annotation.annotationType());
    }
}
