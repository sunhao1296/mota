import java.util.List;
import java.util.Map;

public class Hero {
    int hp,atk,def,mdef,money;
    Map<Integer,Integer> items;//英雄身上的物品以及对应的数量
    public Hero(int hp, int atk, int def, int mdef, int money,Map<Integer,Integer> items) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.mdef = mdef;
        this.money = money;
        this.items = items;
    }
    public Hero getItem(int itemId,Hero hero){
        switch(itemId){
            case 22:
                hero.hp+=200;break;
            case 23:
                hero.atk+=1;break;
            case 24:
                hero.def+=1;break;
            case 25:
                hero.mdef+=5;break;
                //以上四个为血瓶和三种宝石，其他为钥匙等其他物品
            default:
                hero.items.put(itemId,hero.items.getOrDefault(items,0)+1);break;
        }
        return hero;
    }
}
