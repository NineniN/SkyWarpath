package sw.expand.units.EliteUnit;

import arc.func.Cons;
import mindustry.gen.Unit;

public class StageData {
    //转阶段需求的时间
    public float enterTime = 0;
    //转阶段需求的血量
    public float enterHealth = 99999;
    //转阶段后的无敌时间
    public float invincibleTime = 0;


    public Cons<Unit> onEnter;
    public Cons<Unit> update;
}
