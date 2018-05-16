package by.yan.cafe.entity.food.impl;

import by.yan.cafe.entity.food.BaseFood;

public class Meal implements BaseFood
{
    private int id;
    private String name;
    private String descr;
    private byte price;
    private String imgPath;

    public Meal(String name, byte price)
    {
        this.name=name;
        this.price=price;
    }

    public Meal(int id, String name, String descr, byte price, String imgPath)
    {
        this.id=id;
        this.name=name;
        this.descr=descr;
        this.price=price;
        this.imgPath=imgPath;
    }

    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public void setId(int id){this.id=id;}

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name){this.name=name;}

    @Override
    public String getDescr()
    {
        return descr;
    }

    @Override
    public void setDescr(String descr){this.descr=descr;}

    @Override
    public byte getPrice()
    {
        return (byte)(Math.abs(price));
    }

    @Override
    public void setPrice(byte price){this.price=price;}

    @Override
    public String getImgPath()
    {
        return imgPath;
    }

    @Override
    public void setImgPath(String imgPath)
    {
        this.imgPath = imgPath;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal meal = (Meal) o;

        if (id != meal.id) return false;
        if (price != meal.price) return false;
        if (name != null ? !name.equals(meal.name) : meal.name != null) return false;
        if (descr != null ? !descr.equals(meal.descr) : meal.descr != null) return false;
        return imgPath != null ? imgPath.equals(meal.imgPath) : meal.imgPath == null;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (int) price;
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", price=" + price +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

}
