package sw.expand.blocks.defense;

import arc.Events;
import mindustry.entities.Damage;
import mindustry.game.EventType;
import mindustry.gen.Bullet;
import mindustry.gen.Healthc;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.StatUnit;
import sw.expand.SWStat;

public class BetterWall extends Wall {
    //是否受击移除攻击子弹
    public boolean destroyCollision = false;
    //是否限伤
    public boolean limitDamage = false;
    //最大受伤
    public float maximumDamageTaken = 325;
    //折前固定伤害减免
    public float flatDamageReduction1 = 0;
    //折后固定伤害减免
    public float flatDamageReduction2 = 0;
    //百分比伤害减免
    public float percentDamageReduction = 0;
    //固定反击伤害(<0 会治愈敌方)
    public float retaliation = 0;
    //百分比反击伤害(<0 会治愈敌方)
    public float percentRetaliation = 0;
    //受击恢复血量
    public float healOnHit = 0;
    //受击百分比恢复血量
    public float percentHealOnHit = 0;


    public BetterWall(String name){
        super(name);
    }

    @Override
    public void setStats() {
        super.setStats();

        if(destroyCollision) stats.add(SWStat.destroyCollision,destroyCollision);

        if(limitDamage) stats.add(SWStat.maximumDamageTaken,maximumDamageTaken);

        if(flatDamageReduction1 != 0)stats.add(SWStat.flatDamageReduction1,flatDamageReduction1);
        if(flatDamageReduction2 != 0)stats.add(SWStat.flatDamageReduction2,flatDamageReduction2);
        if(percentDamageReduction != 0)stats.add(SWStat.percentDamageReduction,percentDamageReduction);

        if(retaliation > 0) stats.add(SWStat.retaliation,retaliation);
        else if (retaliation < 0) stats.add(SWStat.retaliationHeal,retaliation);
        if(percentRetaliation > 0)stats.add(SWStat.percentRetaliation,percentRetaliation * 100,StatUnit.percent);
        else if (percentRetaliation < 0) stats.add(SWStat.percentRetaliationHeal,percentRetaliation * 100, StatUnit.percent);

        if(healOnHit != 0) stats.add(SWStat.healOnHit,healOnHit);
        if(percentHealOnHit != 0)stats.add(SWStat.percentHealOnHit,percentHealOnHit,StatUnit.percent);
    }

    public class BetterWallBuild extends WallBuild{
        @Override
        public boolean collision(Bullet other){
            boolean wasDead = this.health <= 0.0F;
            float damage = (other.type.buildingDamage(other) - flatDamageReduction1) * percentDamageReduction - flatDamageReduction2;
            if (!other.type.pierceArmor) {
                damage = (Damage.applyArmor(damage, this.block.armor) - flatDamageReduction1) * percentDamageReduction - flatDamageReduction2;
            }
            damage = Math.max(limitDamage ? Math.min(damage,maximumDamageTaken) : damage,0);
            this.damage(other, other.team, limitDamage ? Math.min(damage,maximumDamageTaken) : damage);

            if (this.health <= 0.0F && !wasDead) {
                Events.fire(new EventType.BuildingBulletDestroyEvent(this, other));
            }

            if(destroyCollision) other.remove();

            if(other.owner instanceof Healthc h){
                if(retaliation > 0) h.damagePierce(retaliation);
                else if(retaliation < 0) h.heal(-retaliation);

                if(percentRetaliation > 0) h.damagePierce(other.damage * percentRetaliation);
                else if(percentRetaliation < 0) h.heal(other.damage * (-percentRetaliation));
            }

            if(healOnHit > 0) this.heal(healOnHit);
            else if(healOnHit < 0) this.damagePierce(-healOnHit);
            if(percentHealOnHit > 0) this.heal(this.maxHealth * percentHealOnHit);
            else if(percentHealOnHit < 0) this.damagePierce(this.maxHealth * (-percentHealOnHit));

            return true;
        }
        @Override
        public void damage(float damage){
            super.damage((damage - flatDamageReduction1) * (1 - percentDamageReduction) - flatDamageReduction2);
        }
    }
}
