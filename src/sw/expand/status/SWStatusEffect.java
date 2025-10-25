package sw.expand.status;

import arc.func.Cons;
import arc.util.Time;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
import mindustry.world.meta.StatUnit;
import sw.expand.SWStat;

public class SWStatusEffect extends StatusEffect {
    //是否启用根据剩余时间提升伤害
    public boolean timeStatusEffect = false;
    //百分比伤害以及治疗
    public float percentDamage = 0;
    public Cons<Unit> update;

    public SWStatusEffect(String name){
        super(name);
    }

    @Override
    public void setStats(){
        super.setStats();
        if(percentDamage > 0)stats.add(SWStat.percentDamage,percentDamage * 100, StatUnit.percent);
        else if(percentDamage < 0)stats.add(SWStat.percentHeal,percentDamage * -100, StatUnit.percent);
    }

    /*
    @Override
    public void update(Unit u,float t){
        super.update(u,t);

        u.apply(this,u.getDuration(this) + Time.delta);

        if(percentDamage > 0){
            u.damageContinuousPierce(u.maxHealth * percentDamage);
        } else {
            u.heal(-1 * u.maxHealth * percentDamage * Time.delta);
        }
    }
    */
}
