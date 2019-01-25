public class Monster {
    /*
    魔塔中的怪物，尚未加入属性的思路
     */
    int id,hp,atk,def;
    int ability;
    public Monster(int Id,int Hp,int Atk,int Def,int Ability){
        id = Id; hp = Hp; atk = Atk; def = Def; ability = Ability;
    }
    public static Monster getMonster(int Id){
        Monster mon = new Monster(0,0,0,0,0);
        switch (Id){
            case 30:
                mon.id=30;mon.hp=300;mon.atk=37;mon.def=4;mon.ability=0;break;
            default:
                mon.id=0;break;
        }
        return mon;
    }
    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                ", ability=" + ability +
                '}';
    }
    public int lost(Hero hero,Monster monster){//怪物伤害计算
        if(hero.atk <= monster.def){
            return 999999;
        }
        double turns = (double)monster.hp / (hero.atk-monster.def);
        int realTurns = (int)Math.ceil(turns);
        return Math.max((realTurns-1)*(monster.atk - hero.def) - hero.mdef, 0);
    }
}
