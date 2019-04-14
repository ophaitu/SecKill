package com.lhl.domain;

public class T {
    static T t=new T("a");
    private String a;
    static{
        System.out.println("父类静态代码");
    }
    public T(){
        System.out.println("父类构造");
    }
    public T(String a){
        this.a=a;
        System.out.println("父类构造"+this.getClass().getSimpleName());
    }

    public String getA() {
        return a;
    }

    public void cla(){
        System.out.println(getClass().getSimpleName());
    }
    public static void main(String[] args) {
        Tc tc = new Tc();
        System.out.println(tc.getA());
    }
}
class Tc extends T{
    static{
        System.out.println("子类静态代码");
    }
    public Tc(){
        super("s");
    }
}
