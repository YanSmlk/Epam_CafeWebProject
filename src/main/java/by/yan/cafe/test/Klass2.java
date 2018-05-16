package by.yan.cafe.test;

public class Klass2 extends Klass
{
    public int cal=2;

    public void test(){}

    public Klass2(int a){super(a);}

    public Klass2()
    {
        super(42);
        this.a=42;

    }

    public void methKl()
    {
        System.out.println(cal);
    }

}
