package by.yan.cafe.entity;

import by.yan.cafe.entity.food.impl.Dessert;
import by.yan.cafe.entity.food.impl.Drink;
import by.yan.cafe.entity.food.impl.Meal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order
{
    private int id;
    private String gettingTime;
    private String status;
    private byte totalPrice;
    private String clientName;

    private List<Drink> drinkArr=new ArrayList<>();
    private List<Dessert> dessertArr=new ArrayList<>();
    private List<Meal> mealArr=new ArrayList<>();

    public Order(){}

    public Order(int id, String gettingTime, byte totalPrice, String clientName)
    {
        this.id=id;
        this.gettingTime=gettingTime;
        this.totalPrice=totalPrice;
        this.clientName=clientName;
    }

    public Order(List<Drink> drinkArr,List<Dessert> dessertArr,List<Meal> mealArr)
    {
        this.drinkArr=drinkArr;
        this.dessertArr=dessertArr;
        this.mealArr=mealArr;
    }

    public Order(int id,String gettingTime,String status, byte totalPrice)
    {
        this.id=id;
        this.gettingTime=gettingTime;
        this.status=status;
        this.totalPrice=totalPrice;
    }

    public Order(int id,String gettingTime,String status, byte totalPrice,
                 List<Dessert> dessertArr, List<Drink> drinkArr, List<Meal> mealArr)
    {
        this.id=id;
        this.gettingTime=gettingTime;
        this.status=status;
        this.totalPrice=totalPrice;
        this.dessertArr=dessertArr;
        this.drinkArr=drinkArr;
        this.mealArr=mealArr;
    }


    public int getid()
    {
        return id;
    }

    public void setId(int id){this.id=id;}

    public String getGettingTime()
    {
        return gettingTime;
    }

    public void setGettingTime(String gettingTime)
    {
        this.gettingTime = gettingTime;
    }

    public String getStatus(){return status;}

    public void setStatus(String status){this.status=status;}

    public byte getTotalPrice()
    {
        if(drinkArr.isEmpty()&&mealArr.isEmpty()&&dessertArr.isEmpty())
        {
            return totalPrice;
        }
        totalPrice=0;
        Iterator<Meal> mealIterator=mealArr.iterator();

        while (mealIterator.hasNext())
        {
            totalPrice=(byte)(totalPrice+mealIterator.next().getPrice());
        }

        Iterator<Dessert> dessertIterator=dessertArr.iterator();
        while (dessertIterator.hasNext())
        {
            totalPrice=(byte) (totalPrice+dessertIterator.next().getPrice());
        }

        Iterator<Drink> drinkIterator=drinkArr.iterator();
        while (drinkIterator.hasNext())
        {
            totalPrice=(byte)(totalPrice+drinkIterator.next().getPrice());
        }
        return totalPrice;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public void setTotalPrice(byte totalPrice){this.totalPrice=totalPrice;}

    public List<Drink> getDrinkArr(){return drinkArr;}

    public List<Dessert> getDessertArr(){return dessertArr;}

    public List<Meal> getMealArr(){return mealArr;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (totalPrice != order.totalPrice) return false;
        if (gettingTime != null ? !gettingTime.equals(order.gettingTime) : order.gettingTime != null) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;
        if (clientName != null ? !clientName.equals(order.clientName) : order.clientName != null) return false;
        if (drinkArr != null ? !drinkArr.equals(order.drinkArr) : order.drinkArr != null) return false;
        if (dessertArr != null ? !dessertArr.equals(order.dessertArr) : order.dessertArr != null) return false;
        return mealArr != null ? mealArr.equals(order.mealArr) : order.mealArr == null;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (gettingTime != null ? gettingTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) totalPrice;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (drinkArr != null ? drinkArr.hashCode() : 0);
        result = 31 * result + (dessertArr != null ? dessertArr.hashCode() : 0);
        result = 31 * result + (mealArr != null ? mealArr.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "id=" + id +
                ", gettingTime='" + gettingTime + '\'' +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", clientName='" + clientName + '\'' +
                ", drinkArr=" + drinkArr +
                ", dessertArr=" + dessertArr +
                ", mealArr=" + mealArr +
                '}';
    }

}
