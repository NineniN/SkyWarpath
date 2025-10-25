package sw.content;

import arc.graphics.Color;
import arc.math.geom.Vec3;
import arc.util.noise.Simplex;
import mindustry.content.Planets;
import mindustry.game.Team;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.graphics.g3d.PlanetGrid;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.maps.planet.ErekirPlanetGenerator;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;
import mindustry.type.Sector;
import mindustry.world.meta.Attribute;

public class SWPlanets {
    public static Planet kleist,tekpose;

    public static void load(){
        kleist = new Planet("kleist", Planets.sun,1,2){{
            generator = new SerpuloPlanetGenerator(){{

            }
                @Override
                public void getColor(Vec3 position, Color out) {
                    super.getColor(position, out);
                    float noise = Simplex.noise3d(this.seed,5,0.3f,1 / 3,position.x,position.y,position.z);
                    if (noise > 0.88) out.set(Color.valueOf("ffffff"));
                    else if (noise > 0.68) out.set(Color.valueOf("8CA9E8"));
                    else out.set(Color.valueOf("272F4F"));
                }
            };
            visible = true;
            bloom = false;
            accessible = true;
            alwaysUnlocked = true;
            clearSectorOnLose = true;

            atmosphereRadIn = 0.2f;
            atmosphereRadOut = 0.3f;
            atmosphereColor = Color.valueOf("272F4F");
            lightColor = Color.valueOf("8CA9E8");

            camRadius = 0.5f;
            orbitRadius = 75;
            orbitSpacing = 2;
            orbitTime = 300 * 60;
            rotateTime = 5 * 60;

            defaultCore = SWBlocks.explorerCore;
            sectors.add(new Sector(this, PlanetGrid.Ptile.empty));
            startSector = 2;

            iconColor = Color.valueOf("8CA9E8");

            meshLoader = () -> new HexMesh(kleist,4);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(kleist,2,0.15f,0.14f,5,Color.valueOf("2724FF"),2,0.42f,1,0.45f),
                    new HexSkyMesh(kleist,1,0.6f,0.16f,5,Color.valueOf("D1EFFF"),2,0.45f,1,0.41f)
            );

            ruleSetter = r -> {
                r.attributes.set(Attribute.heat,-2);
                r.attributes.set(Attribute.light,-3);
                r.waveTeam = Team.blue;
                r.defaultTeam = Team.crux;
                r.placeRangeCheck = false;
                r.showSpawns = false;
                r.lighting  = false;
                r.coreDestroyClear = true;
                r.onlyDepositCore = true;
            };
        }};
    }
}
