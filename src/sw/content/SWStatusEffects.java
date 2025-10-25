package sw.content;

import arc.graphics.Color;
import mindustry.entities.effect.ParticleEffect;
import mindustry.type.StatusEffect;
import sw.expand.status.ReloadStatus;
import sw.expand.status.SynapseBurnout;

public class SWStatusEffects {
    public static StatusEffect combust,root,hullBreach,irradiated,chill,synapseBurnout,synapseBurnoutBuffer,firevilStage2;

    public static void load(){
        combust = new StatusEffect("combust"){{
            color = Color.valueOf("E05438");
            damage = 0.3f;
            effect = new ParticleEffect(){{
                lifetime = 20;
                particles = 1;
                baseLength = 0;
                length = 0;
                sizeFrom = 6;
                sizeTo = 0;
                colorFrom = Color.valueOf("ffffff");
                colorTo = Color.valueOf("E05438");
            }};
        }};
        root = new StatusEffect("root"){{
            color = Color.valueOf("4A4B53");
            speedMultiplier = 0.3f;
            effect = new ParticleEffect(){{
                lifetime = 20;
                particles = 1;
                baseLength = 0;
                length = 0;
                sizeFrom = 6;
                sizeTo = 0;
                colorFrom = Color.valueOf("4A4B53");
                colorTo = Color.valueOf("4A4B53");
            }};
        }};
        hullBreach = new StatusEffect("hull-breach"){{
            color = Color.valueOf("4A4B53");
            speedMultiplier = 0.3f;
            damage = 0.4f;
        }};
        irradiated = new StatusEffect("irradiated"){{
            color = Color.valueOf("5e998d60");
            damage = 0.3f;
            effect = new ParticleEffect(){{
                lifetime = 20;
                particles = 1;
                baseLength = 0;
                length = 0;
                sizeFrom = 6;
                sizeTo = 0;
                colorFrom = Color.valueOf("5e998d60");
                colorTo = Color.valueOf("5e998d60");
            }};
        }};
        chill = new StatusEffect("chill"){{
            color = Color.valueOf("99F8FF");
            speedMultiplier = 0.3f;
            effect = new ParticleEffect(){{
                lifetime = 20;
                particles = 1;
                baseLength = 0;
                length = 0;
                sizeFrom = 6;
                sizeTo = 0;
                colorFrom = Color.valueOf("ffffff");
                colorTo = Color.valueOf("99F8FF");
            }};
        }};
        synapseBurnout = new SynapseBurnout("synapse-burnout"){{
            color = Color.valueOf("99F8FF");
            speedMultiplier = 0.3f;
            effect = new ParticleEffect(){{
                lifetime = 20;
                particles = 1;
                baseLength = 0;
                length = 0;
                sizeFrom = 6;
                sizeTo = 0;
                colorFrom = Color.valueOf("ffffff");
                colorTo = Color.valueOf("99F8FF");
            }};

            reloadStatus = synapseBurnoutBuffer;
        }};

        synapseBurnoutBuffer = new ReloadStatus("synapse-burnout-buffer"){{
            color = Color.valueOf("99F8FF");
            speedMultiplier = 0.3f;
            effect = new ParticleEffect(){{
                lifetime = 20;
                particles = 1;
                baseLength = 0;
                length = 0;
                sizeFrom = 6;
                sizeTo = 0;
                colorFrom = Color.valueOf("ffffff");
                colorTo = Color.valueOf("99F8FF");
            }};

            conflictStatus = synapseBurnout;
        }};

        firevilStage2 = new StatusEffect("firevil-stage2"){{
            speedMultiplier = 2;
            damageMultiplier = 10;

        }};
    }
}
