package by.yan.cafe.entity.food.impl;

import by.yan.cafe.entity.food.BaseFood;

public class Dessert implements BaseFood
{
    private int id;
    private String name;
    private String descr;
    private byte price;
    private String imgPath;

    public Dessert(String name, byte price)
    {
        this.name=name;
        this.price=price;
    }

    public Dessert(int id, String name, String descr, byte price, String imgPath)
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

        Dessert dessert = (Dessert) o;

        if (id != dessert.id) return false;
        if (price != dessert.price) return false;
        if (name != null ? !name.equals(dessert.name) : dessert.name != null) return false;
        if (descr != null ? !descr.equals(dessert.descr) : dessert.descr != null) return false;
        return imgPath != null ? imgPath.equals(dessert.imgPath) : dessert.imgPath == null;
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
        return "Dessert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descr='" + descr + '\'' +
                ", price=" + price +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

}
