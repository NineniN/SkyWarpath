package sw.expand.blocks.turret;

import arc.Core;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Strings;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.entities.bullet.BulletType;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.turrets.PowerTurret;

public class ChargePowerTurret extends PowerTurret {
    //特殊子弹类
    public BulletType specialBullet;
    //获得1点充能所需的时间（<0则视为不随时间回复）
    public int chargeInterval = -1;
    //每次攻击获得的充能
    public int getChargePerShot = 1;
    //最大充能层数
    public int specialChargeMax = 4;
    //强化攻击消耗层数
    public int chargeNeedPerShot = 4;

    public ChargePowerTurret(String name){
        super(name);
    }

    @Override
    public void setBars() {
        super.setBars();
        if(chargeInterval > 0) addBar("charge",(ChargePowerTurretBuild entity) -> new Bar(
                () -> Core.bundle.get("bar.chargetimer"),
                () -> Tmp.c1.set(Pal.darkerGray).lerp(Pal.surge, Mathf.clamp(entity.chargeTimer / chargeInterval)),
                () -> entity.heatReq / heatRequirement
        ));
    }

    public class ChargePowerTurretBuild extends PowerTurretBuild{
        public float chargeTimer = 0;
        public int chargeLevel = 0;
        @Override
        public void updateTile(){
            super.updateTile();

            chargeTimer += Time.delta;
            if(chargeInterval > 0 && chargeTimer > chargeInterval){
                chargeLevel++;
                chargeTimer -= chargeInterval;
            }
        }

        @Override
        protected void updateShooting() {
            if(reloadCounter >= reload && !charging() && shootWarmup >= minWarmup){
                BulletType type = peekAmmo();

                shoot(type);

                reloadCounter %= reload;
            }
            super.updateShooting();
        }

        @Override
        protected void shoot(BulletType type) {
            super.shoot(type);
            chargeLevel += getChargePerShot;
        }
    }
}
