package sw.content;

import mindustry.type.SectorPreset;

public class SWSectorPresets {
    public static SectorPreset
            //kleist
            startingLake,desolateStrait,vastValley,clampedStrait,snagsFortress;
            //texboss
    public static void load(){
        //kleist
        //伊始之湖
        startingLake = new SectorPreset("starting-lake",SWPlanets.kleist,2){{
            planet = SWPlanets.kleist;
            alwaysUnlocked = true;
            addStartingItems = false;

            captureWave = 11;
            difficulty = 2;
        }};
        //荒芜海峡
        desolateStrait = new SectorPreset("desolate-strait",SWPlanets.kleist,10){{
            planet = SWPlanets.kleist;

            captureWave = 15;
            difficulty = 2;
        }};
        //-广阔河谷
        vastValley = new SectorPreset("vast-vally",SWPlanets.kleist,8){{
            planet = SWPlanets.kleist;

            captureWave = 20;
            difficulty = 4;
        }};
        //-双子海峡
        clampedStrait = new SectorPreset("clamped-strait",SWPlanets.kleist,12){{
            planet = SWPlanets.kleist;

            captureWave = 18;
            difficulty = 5;
        }};
        //暗礁要塞
        snagsFortress = new SectorPreset("snags-fortress",SWPlanets.kleist,20){{
            planet = SWPlanets.kleist;

            difficulty = 5;
        }};
    }
}
