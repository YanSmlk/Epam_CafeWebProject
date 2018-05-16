package by.yan.cafe.test;


public class Klass implements Interf
{
    public int a;

    protected Klass(int a)
    {
        this.a=a;
    }

    public int cal=1;

    @Override
    public void meth()
    {

    }

    public void methKl()
    {
        System.out.println(cal);
    }

}
