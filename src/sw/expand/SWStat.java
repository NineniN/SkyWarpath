package sw.expand;

import mindustry.world.meta.Stat;

public class SWStat {
    public static final Stat
            percentDamage = new Stat("percent-damage"),
            percentHeal = new Stat("percent-heal"),
            burnoutThreshold = new Stat("burn-threshold"),
            burnoutDamage = new Stat("burnout-damage"),
            burnoutPercentDamage = new Stat("burnout-percent-damage"),
            bufferTime = new Stat("buffer-time"),

            //BetterWall
            destroyCollision = new Stat("destroy-collision"),
            flatDamageReduction1 = new Stat("flat-damage-reduction1"),
            flatDamageReduction2 = new Stat("flat-damage-reduction2"),
            percentDamageReduction = new Stat("percent-damage-reduction"),
            maximumDamageTaken = new Stat("maximum-damage-taken"),
            retaliation = new Stat("retaliation"),
            percentRetaliation = new Stat("percent-retaliation"),
            retaliationHeal = new Stat("retaliation-heal"),
            percentRetaliationHeal = new Stat("percent-retaliation-heal"),
            healOnHit = new Stat("heal-on-hit"),
            percentHealOnHit = new Stat("percent-heal-on-hit")
            ;
}
