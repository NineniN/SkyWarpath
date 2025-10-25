package sw.content;

import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.type.Weapon;

public class SWWeapons {
    public static Weapon firevilGun,firevilHeavyArtillery;
    public static void load(){
        firevilGun = new Weapon(){{
            rotate = true;
            rotateSpeed = 3;

            reload = 40;
            shoot = new ShootAlternate(){{
                shots = 3;
                shotDelay = 6;
            }};
            bullet = new BasicBulletType(8,250){{
                lifetime = 24;
            }};
        }};

        firevilHeavyArtillery = new Weapon(){{
            rotate = true;
            rotateSpeed = 3;

            reload = 40;
            recoil = 12;

            bullet = new BasicBulletType(12,650){{
                lifetime = 20;

                pierce = true;
                pierceCap = 2;
            }};
        }};
    }
}
