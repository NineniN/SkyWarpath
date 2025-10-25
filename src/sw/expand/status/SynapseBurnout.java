package sw.expand.status;

import mindustry.content.StatusEffects;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
import sw.expand.SWStat;

public class SynapseBurnout extends StatusEffect {
    public float burnoutTime;
    public float percentDamage;
    public float burnoutDamage;
    public StatusEffect reloadStatus = StatusEffects.freezing;
    public float bufferTime;

    public SynapseBurnout(String name){
        super(name);
        burnoutTime = 30 * 60;
        percentDamage = 0.2f;
        burnoutDamage = 1200;
        bufferTime = 10 * 60;
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.add(SWStat.burnoutThreshold,burnoutTime / 60);
        stats.add(SWStat.burnoutDamage,burnoutDamage);
        stats.add(SWStat.burnoutPercentDamage,percentDamage * 100 + "%");
        stats.add(SWStat.bufferTime,bufferTime);
    }

    /*
    @Override
    public void update(Unit unit, float time) {
        super.update(unit, time);

        if(unit.getDuration(this) > burnoutTime && !unit.statusBits().get(reloadStatus.id)){
            unit.damagePierce(unit.maxHealth * percentDamage + burnoutDamage);
            unit.apply(reloadStatus, bufferTime);
        }
    }
    */
}
