package by.yan.cafe.entity;

import by.yan.cafe.constant.RoleType;

public class User
{
    private int id;
    private RoleType roleType;
    private String email;
    private String password;
    private String login;
    private int points;
    private int moneyAmount;

    public User(int id,RoleType roleType,String email,String password,String login,int points,int moneyAmount)
    {
        this.id=id;
        this.roleType=roleType;
        this.email=email;
        this.password=password;
        this.login=login;
        this.points=points;
        this.moneyAmount=moneyAmount;
    }

    public int getid()
    {
        return id;
    }

    public void setId(int id){this.id=id;}

    public RoleType getRoleType(){return roleType;}

    public void setRoleType(RoleType roleType){this.roleType=roleType;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){this.email=email;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){this.password=password;}

    public String getLogin(){return login;}

    public void setLogin(String login){this.login=login;}

    public int getPoints(){return points;}

    public void setPoints(int points){this.points=points;}

    public int getMoneyAmount(){return moneyAmount;}

    public void setMoneyAmount(int moneyAmount){this.moneyAmount=moneyAmount;}


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (points != user.points) return false;
        if (moneyAmount != user.moneyAmount) return false;
        if (roleType != user.roleType) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (roleType != null ? roleType.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + points;
        result = 31 * result + moneyAmount;
        return result;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", roleType=" + roleType +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", points=" + points +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
