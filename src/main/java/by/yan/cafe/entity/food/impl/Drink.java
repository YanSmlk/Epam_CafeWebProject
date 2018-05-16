package by.yan.cafe.entity.food.impl;

import by.yan.cafe.entity.food.BaseFood;

public class Drink implements BaseFood
{
    private int id;
    private String name;
    private String descr;
    private byte price;
    private String imgPath;

    public Drink(String name, byte price)
    {
        this.name=name;
        this.price=price;
    }

    public Drink(int id, String name, String descr, byte price, String imgPath)
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

        Drink drink = (Drink) o;

        if (id != drink.id) return false;
        if (price != drink.price) return false;
        if (name != null ? !name.equals(drink.name) : drink.name != null) return false;
        if (descr != null ? !descr.equals(drink.descr) : drink.descr != null) return false;
        return imgPath != null ? imgPath.equals(drink.imgPath) : drink.imgPath == null;
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
        return "Drink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", price=" + price +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

}
