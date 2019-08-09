package userregist.itcast.cn.userregist;
import java.io.Serializable;
public class ItemInfo implements Serializable{
    private String name;
    private int acctack;
    private int life;
    private int speed;
    public ItemInfo(String name, int acctack, int life, int speed){
        this.name = name;
        this.acctack = acctack;
        this.life = life;
        this.speed = speed;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void getAccack(){
        return ;
    }

    public void setAcctack(int acctack) {
        this.acctack = acctack;
    }
    public void getLife(){
        return ;
    }

    public void setLife(int life) {
        this.life = life;
    }
    public void getSpeed(){
        return ;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
