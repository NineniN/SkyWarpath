package sw.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.MissileBulletType;
import mindustry.entities.part.HoverPart;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.gen.EntityMapping;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.weapons.BuildWeapon;
import sw.expand.units.*;

public class SWUnitTypes {
    public static int segmentID,firevilID;
    static {
        segmentID = EntityMapping.register("cronus", SegmentEntity::new);
        //firevilID = EntityMapping.register("firevil", EliteUnitEntity::new);
    }
    public static UnitType cirrhinus,cronus,firevil;

    public static void load(){
        cirrhinus = new UnitType("cirrhinus"){{
            constructor = () -> UnitTypes.elude.constructor.get();
            alwaysUnlocked = true;
            isEnemy = false;
            lowAltitude = true;
            hovering = true;
            mineWalls = true;
            canBoost = true;

            health = 450;
            armor = 1;
            hitSize = 18;
            speed = 2;
            accel = 0.07f;
            drag = 0.045f;
            rotateSpeed = 14;
            trailLength = 30;

            parts.addAll(
                    new HoverPart(){{
                        mirror = false;
                        x = 0;
                        y = 0;
                        radius = 11;
                        phase = 90;
                        stroke = 2;
                        layerOffset = - 0.001f;
                        color = Color.valueOf("DEEDFF");
                    }}
            );
            immunities.addAll(StatusEffects.wet);
            weapons.addAll(
                    new BuildWeapon("cirrhinus-builder"){{
                        mirror = true;
                        top = false;
                        rotate = true;

                        x = 4.7f;
                        y = -1;
                        shootY = 6;
                        rotateSpeed = 0.5f;
                        layerOffset = -0.001f;
                    }},
                    new Weapon(){{
                        mirror = false;
                        rotate = false;

                        x = y = 0;

                        inaccuracy = 6;
                        reload = 32;
                        recoil = 0;
                        reload = 32;

                        shootCone = 360;
                        //shootSound = Sounds.missile;
                        shoot = new ShootAlternate(){{
                            shots = 4;
                            shotDelay = 6;
                        }};

                        bullet = new MissileBulletType(){{
                            lifetime = 50;
                            speed = 1;
                            drag = -0.02f;

                            frontColor = Color.valueOf("FFD37F");
                            backColor = Color.valueOf("FFD37F");
                            hitColor = Color.valueOf("FFD37F");

                            damage = 14;
                            healPercent = 3;
                            splashDamage = 4;
                            splashDamageRadius = 32;
                            splashDamagePierce = true;

                            weaveMag = 3;
                            weaveScale = 5;

                            hitEffect = SWFx.cirrhinusHit;
                        }};
                    }}
            );
        }};
        cronus = new SegmentType("cronus"){{
            constructor = () -> new SegmentEntity(){{}};
            flying = true;
            speed = 2.8f;

            health = 165000;
            hitSize = 30;
            rotateSpeed = 1.8f;
            maxLinkDistance = 64;

            bodies = 30;

            headParts.addAll(
                    new ShapePart(){{
                        circle = true;
                        progress = PartProgress.life.sin((float) Math.PI * 4,0.3f);

                        radius = 4;
                        radiusTo = 2;
                        color = Color.black;
                        colorTo = Color.black;

                        layer = Layer.flyingUnit;
                    }},
                    new ShapePart(){{
                        circle = true;
                        progress = PartProgress.life.sin((float) Math.PI * 4,0.3f);

                        radius = 6;
                        radiusTo = 3;
                        color = Color.white;
                        colorTo = Color.white;

                        layer = Layer.effect;
                    }}

            );
            bodyParts.addAll(
                    new ShapePart(){{
                        circle = true;
                        progress = PartProgress.life.sin((float) Math.PI * 4,0.3f);

                        y = -4;
                        radius = 4;
                        radiusTo = 2;
                        color = Color.black;
                        colorTo = Color.black;

                        layer = Layer.flyingUnit;
                    }},
                    new ShapePart(){{
                        circle = true;
                        progress = PartProgress.life.sin((float) Math.PI * 4,0.3f);

                        y = -4;
                        radius = 6;
                        radiusTo = 3;
                        color = Color.white;
                        colorTo = Color.white;

                        layer = Layer.effect;
                    }}
            );
            tailParts.addAll(
                    new ShapePart(){{
                        circle = true;
                        progress = PartProgress.life.sin((float) Math.PI * 4,0.3f);

                        radius = 4;
                        radiusTo = 2;
                        color = Color.black;
                        colorTo = Color.black;

                        layer = Layer.flyingUnit;
                    }},
                    new ShapePart(){{
                        circle = true;
                        progress = PartProgress.life.sin((float) Math.PI * 4,0.3f);

                        radius = 6;
                        radiusTo = 3;
                        color = Color.white;
                        colorTo = Color.white;

                        layer = Layer.effect;
                    }}
            );
        }};
    }
}
