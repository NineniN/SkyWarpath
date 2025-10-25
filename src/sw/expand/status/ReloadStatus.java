package sw.expand.status;

import mindustry.content.StatusEffects;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

public class ReloadStatus extends StatusEffect {
    public StatusEffect conflictStatus = StatusEffects.freezing;

    public ReloadStatus(String name){
        super(name);
    }

    /*
    @Override
    public void update(Unit unit, float time) {
        super.update(unit, time);

        if(unit.getDuration(conflictStatus) > 0){
            unit.unapply(conflictStatus);
        }
    }
    */
}
