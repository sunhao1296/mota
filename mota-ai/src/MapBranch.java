import java.util.List;

public class MapBranch {
    /*
    分支记录的是可互相抵达的宝物集合
     */
    int id;
    //分支无关道具，不考虑毒衰，尚未加入商店系统
    boolean used;//记录此分支是否已走过
    int[] items;
    //记录可获得的东西，如钥匙，血瓶
    int[] mons;//记录怪物
    int[] need;//记录要用的钥匙
    List<MapBranch[]> beforeId;//记录分支之前走的不同路线所经历的分支；

    public MapBranch(int id, boolean used, int[] items, int[] mons, int[] need, List<MapBranch[]> beforeId) {
        this.id = id;
        this.used = used;
        this.items = items;
        this.mons = mons;
        this.need = need;
        this.beforeId = beforeId;
    }
    public int branchLost(Hero hero,MapBranch mapBranch){
        int lost = 0;
        for(int mon : mapBranch.mons){
            Monster monster = Monster.getMonster(mon);
            lost += monster.lost(hero,monster);
        }
        return lost;
    }
    public boolean canGet(Hero hero,MapBranch mapBranch){
        //这条分支能不能走
        if(mapBranch.used == true){
            return false;
        }
        if(mapBranch.beforeId.size()>0){
            boolean br = false;
            for(MapBranch[] before:mapBranch.beforeId){
                boolean temp = true;
                for(MapBranch tempBr:before){
                    if(tempBr.used == false) temp = false;
                }
                if(temp == true) {
                    br = true;
                    break;
                }
            }//不同分支走通的判断
            if(br == false) return false;
        }
        for(int item:mapBranch.need){
            if(hero.items.getOrDefault(item,0)==0){
                return false;
            }
            else{
                hero.items.put(item,hero.items.get(item)-1);
            }
        }//不满足则判定分支不通
        if(hero.hp > branchLost(hero,mapBranch)) return false;
        else return false;
    }
    public Hero getBranch(Hero hero, MapBranch mapBranch){
        //走这条分支
        hero.hp -= branchLost(hero,mapBranch);
        for(int item:mapBranch.need){
            hero.items.put(item,hero.items.get(item)-1);
        }
        for(int item:mapBranch.items){
            hero = hero.getItem(item,hero);
        }
        return hero;
    }
}
