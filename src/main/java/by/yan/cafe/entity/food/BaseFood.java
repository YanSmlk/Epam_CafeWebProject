package by.yan.cafe.entity.food;

public interface BaseFood
{
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    String getDescr();
    void setDescr(String descr);
    byte getPrice();
    void setPrice(byte price);
    String getImgPath();
    void setImgPath(String imgPath);
}
