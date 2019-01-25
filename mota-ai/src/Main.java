import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    //按照分支进行遍历，十分粗糙的做法
    //DFS+回溯，待优化
    //
    static Hero hero = new Hero(1000,10,10,0,0,new HashMap<>());
    static MapBranch[] mbs = init();
    public static void main(String[] args) {
        init();
        run(hero,mbs);
    }
    public static MapBranch[] init(){
        final int numsOfBranches = 4;
        MapBranch[] branches = new MapBranch[numsOfBranches];
        int[] items0 = {22,29};
        int[] need0 = {25};
        int[] mons0 = {31};
        //物品标号
        List<MapBranch[]> before0 = new ArrayList<>();
        branches[0] = new MapBranch(0,false,items0,mons0,need0,before0);
        /*
        暂时不写分支
         */
        return branches;
    }
    public static void run(Hero hero,MapBranch[] mbs){
        boolean allUsed = true;
        boolean getOne = false;
        for(MapBranch mapBranch:mbs){
            if(mapBranch.used == false){
                allUsed = false;
            }
            if(mapBranch.canGet(hero,mapBranch)){
                getOne = true;
            }
        }
        if(allUsed == true){
            System.out.println(hero.hp);
            return ;
        }//全部分支走通
        if(getOne == false){
            return ;
        }//此路不通

        Hero preHero = hero;
        MapBranch[] preMbs = mbs;
        for(MapBranch mb:mbs) {

            hero = mb.getBranch(hero,mb);
            mb.used = true;
            run(hero,mbs);
            mb.used = false;
            hero = preHero;
        }
        //DFS+回溯，考虑输出路线经过的分支
    }
}
