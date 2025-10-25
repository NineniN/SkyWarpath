package sw.expand.blocks.turret;

import mindustry.world.blocks.defense.turrets.ItemTurret;

public class PreheatTurret extends ItemTurret {
    //暖机时间
    public float preheatTime;
    //使用时间或者是次数
    public boolean isUseTime = true;
    //开火时间
    public float fireTime;
    //开火次数
    public float fireTimes;

    public PreheatTurret(String name){
        super(name);
    }


}
