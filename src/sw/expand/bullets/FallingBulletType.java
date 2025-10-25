package sw.expand.bullets;

import arc.math.Rand;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import arc.util.Interval;
import arc.util.Nullable;
import mindustry.content.Bullets;
import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.Mover;
import mindustry.entities.bullet.BulletType;
import mindustry.game.Team;
import mindustry.gen.Bullet;
import mindustry.gen.Entityc;
import mindustry.gen.Teamc;

public class FallingBulletType extends BulletType {
    public static Rand rand = new Rand();
    //坠落弹体数目
    public int maxFallings = 5;
    //坠落间隔
    public float fallingInterval = 20;
    //爆炸生成范围
    public float fallingRadius = 240;
    //最小间隔
    public float minDistance = 20;
    //爆炸伤害
    public float fallingDamage = 1350;
    //爆炸伤害范围
    public float fallingDamageRadius = 80;
    //爆炸生成子弹
    public BulletType fallingSpawnBullet = Bullets.damageLightning;
    //爆炸特效
    public Effect explosionEffect = Fx.none;
    //本体生成时的特效
    public Effect spawnEffect = Fx.none;

    public FallingBulletType(){
        this.hittable = false;
        this.absorbable = false;
        this.reflectable = false;
        this.collides = false;
    }

    @Override
    public @Nullable Bullet create(
            @Nullable Entityc owner, @Nullable Entityc shooter, Team team, float x, float y, float angle, float damage, float velocityScl,
            float lifetimeScl, Object data, @Nullable Mover mover, float aimX, float aimY, @Nullable Teamc target
    ){
        spawnEffect.at(x,y);
        Bullet bullet = super.create(owner,shooter,team,x,y,angle,damage,velocityScl,lifetimeScl,data,mover,aimX,aimY,target);
        bullet.timer = new Interval(7);
        return bullet;
    }

    @Override
    public void update(Bullet b){
        super.update(b);

        updateFalling(b);
    }

    public void updateFalling(Bullet b){
        if(b.timer(6,fallingInterval)){
            Seq<Vec2> points = new Seq<>();
            for(int i = 0;i < maxFallings;i++) {
                Vec2 vec = findPoint(points,b,0);

                if(vec != null) points.add(vec);
            }

            points.each(point -> {
                Damage.damage(b.team,point.x,point.y,fallingDamageRadius,fallingDamage);
                explosionEffect.at(point.x,point.y);
                fallingSpawnBullet.create(b.owner,b.team,point.x,point.y,0);
            });
        }
    }

    private Vec2 findPoint(Seq<Vec2> points, Bullet b, int i){
        if(i >= 10) return null;
        Vec2 vec = Vec2.X.trns(rand.random(360), rand.random(fallingRadius)).add(b.x, b.y);

        for(Vec2 point : points){
            if(vec.dst(point) < minDistance){
                return findPoint(points, b, i + 1);
            }
        }

        return vec;
    }
}
