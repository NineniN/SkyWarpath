package sw.content;

import arc.graphics.Color;
import arc.math.Interp;
import arc.struct.ObjectMap;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.WrapEffect;
import mindustry.entities.part.FlarePart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.BuildTurret;
import mindustry.world.blocks.defense.Radar;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ContinuousTurret;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.blocks.production.Incinerator;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.RepairTower;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import sw.expand.blocks.defense.BetterWall;
import sw.expand.blocks.turret.ChargePowerTurret;
import sw.expand.bullets.FallingBulletType;

public class SWBlocks {
    public static Block
            //Environment
            heavySnow,iceSand,lightSnow,pollutedIce,
            pollutedVent,pollutedWall,pollutedFloor,pollutedSand,
            dirtSand,dirtSandWall,
            lava,pollutedShoals,pollutedWater,reef,taintedWater,toxicSludge,volcanicVent,
            ashEarth, scorchedRock, scorchedWall,
            oreBisemutum,oreLithium,oreTellurium,oreUranium,wallOreIndium,wallOreLithium,wallOreUranium,
            sulfurFloor,sulfurWall,
            iceCrystal, witheredTree,basaltBoulder,

            //Transport
            //--tekpose
            connector,deliverer,ejector,mechanicalUnloader,tungstenConveyor,tungstenConveyorBridge,tungstenSeparator,

            //Liquid
            //--tekpose
            armoredJar,carbonCirculator,carbonConduitBridge,carbonRouter,suctionPump,vacuumPump,

            //Production
            //--tekpose
            bombarder,burner,destroyer,disintergrator,iceCrusher,scavengers,stamper,

            //Power
            //--tekpose
            blastingGenerator,combustionGenerator,electricHub,electricNode,nitrogenWell,nuclearReactor,rotorGenerator,thermalStation,thermoelectricGenerator,tidalGenerator,

            //Crafter
            //--tekpose
            combustionHeater,condensationChamber,cyanidePyroFilter,decayChamber,electrolysisChamber,fusedSilicaFurnace,graphitePress,hydraulicGraphitePress,laserIncinerator,oxidationChamber,silicaFusionFurnace,solarThermalConcentrator,surgeAlloyForge,wrightAlloyFoundry,atmosphericExtractor,heatSeparator,heatTransmitter,phaseBraider,
            //--kleist

            //Defense
            //--tekpose
            armoredDoor,cobaltWall,cobaltWallLarge,mixedTungstenWall,superProMaxPlusPlusWall,
            maintenanceStation,restorer,signalLight,

            //Storge
            explorerCore, sentinelCore,basicWarehouse,skyEye,horizonEye,

            //Unit
            //--tekpose
            advancedReconstructionWorkshop,crawlWorkshop,creeperWorkShop,firefightingTerminal,hoverWorkshop,mechWorkshop,mechaWorkshop,navyPort,navyWorkshop,reconstructionWorkShop,

            //Turrets
            engulf,guardianWisp,spine,starLight,flux,emberFall,
            ew,starFall,gungnir,

            //Effect
            //--tekpose
            axis,component,gear,squint;


    public static void load(){
        //Environment
        heavySnow = new StaticWall("heavy-snow");

        iceSand = new StaticWall("ice-sand");

        lightSnow = new StaticWall("light-snow");

        pollutedIce = new StaticWall("polluted-ice");

        pollutedVent = new SteamVent("polluted-vent"){{
            attributes.set(Attribute.steam,1);
        }};

        pollutedWall = new StaticWall("polluted-wall");

        pollutedFloor = new Floor("polluted-floor"){{
            variants = 3;
            mapColor = Color.valueOf("4B5D50");
            attributes.set(Attribute.water,-1);
        }};

        pollutedSand = new Floor("polluted-sand"){{
            variants = 3;
            mapColor = Color.valueOf("4B5D50");
            attributes.set(Attribute.water,-1);
        }};

        dirtSand = new Floor("dirt-sand"){{
            playerUnmineable = true;
            itemDrop = Items.sand;
            mapColor = Color.valueOf("9D7F7F");
            attributes.set(Attribute.water,-1);
            attributes.set(Attribute.oil,0.5f);
        }};

        dirtSandWall = new StaticWall("dirt-sand-wall"){{
            mapColor = Color.valueOf("74533E");
        }};

        lava = new Floor("lava"){{
            isLiquid = true;
            shallow = false;
            supportsOverlay = true;
            placeableOn = true;
            variants = 0;
            speedMultiplier = 0.5f;
            albedo = 2;
            liquidDrop = Liquids.slag;
            liquidMultiplier = 1;
        }};

        pollutedShoals = new Floor("polluted-shoals"){{
            isLiquid = true;
            shallow = false;
            supportsOverlay = true;
            placeableOn = true;
            cacheLayer = CacheLayer.water;
        }};

        reef = new Floor("reef"){{
            isLiquid = true;
            shallow = false;
            supportsOverlay = true;
            placeableOn = true;
            cacheLayer = CacheLayer.water;

            liquidDrop = Liquids.water;
            liquidMultiplier = 1;
            speedMultiplier = 0.05f;
            status = SWStatusEffects.hullBreach;
            statusDuration = 200;
        }};

        taintedWater = new Floor("tainted-water"){{
            isLiquid = true;
            shallow = false;
            supportsOverlay = true;
            placeableOn = true;
            cacheLayer = CacheLayer.water;

            liquidDrop = Liquids.water;
            liquidMultiplier = 1;
            status = SWStatusEffects.irradiated;
            statusDuration = 100;
        }};

        pollutedWater = new Floor("polluted-water"){{
            isLiquid = true;
            shallow = false;
            supportsOverlay = true;
            placeableOn = true;
            cacheLayer = CacheLayer.water;

            liquidDrop = SWLiquids.radioactiveWater;
            liquidMultiplier = 1;
            speedMultiplier = 0.4f;
            status = SWStatusEffects.irradiated;
            statusDuration = 150;
        }};

        toxicSludge = new Floor("toxic-sludge"){{
            isLiquid = true;
            shallow = false;
            supportsOverlay = true;
            placeableOn = true;
            cacheLayer = CacheLayer.water;

            liquidDrop = SWLiquids.radioactiveWater;
            liquidMultiplier = 0.2f;
            status = SWStatusEffects.irradiated;
            statusDuration = 250;
        }};

        volcanicVent = new SteamVent("volcanic-vent"){{
            attributes.set(Attribute.steam,1);
            isLiquid = true;
            placeableOn = true;

            variants = 0;
            mapColor = Color.valueOf("474BB2");
            cacheLayer = CacheLayer.water;

            liquidDrop = Liquids.water;
            liquidMultiplier = 1;
            status = StatusEffects.burning;
            statusDuration = 250;
        }};

        ashEarth = new Floor("ash-earth"){{
            attributes.set(Attribute.water,-1);
            variants = 3;
            mapColor = Color.valueOf("1B1B1B");
        }};

        scorchedRock = new Floor("scorched-rock"){{
            attributes.set(Attribute.water,-5);
            attributes.set(Attribute.heat,1.2f);
            attributes.set(Attribute.oil,0.1f);
            variants = 3;
            mapColor = Color.valueOf("1B1B1B");
        }};

        scorchedWall = new StaticWall("scorched-wall"){{
            mapColor = Color.valueOf("292928");
        }};

        oreBisemutum = new OreBlock("ore-bisemutum"){{
            oreDefault = true;

            itemDrop = SWItems.Bi;
            mapColor = Color.valueOf("4A4B53");
            oreScale = 16;
        }};

        oreLithium = new OreBlock("ore-lithium"){{
            oreDefault = true;

            itemDrop = SWItems.Li;
            //mapColor = Color.valueOf("4A4B53");
            oreScale = 16;
        }};

        oreTellurium = new OreBlock("ore-tellurium"){{
            oreDefault = true;

            itemDrop = SWItems.Bi;
            mapColor = Color.valueOf("4A4B53");
            oreScale = 16;
        }};

        oreUranium = new OreBlock("ore-uranium"){{
            oreDefault = true;

            itemDrop = SWItems.U;
            mapColor = Color.valueOf("4A4B53");
            oreScale = 16;
        }};

        wallOreIndium = new OreBlock("wall-ore-indium",SWItems.In){{
            wallOre = true;
        }};

        wallOreLithium = new OreBlock("wall-ore-lithium",SWItems.Li){{
            wallOre = true;
        }};

        wallOreUranium = new OreBlock("wall-ore-uranium",SWItems.U){{
            wallOre = true;
        }};

        sulfurFloor = new Floor("sulfur-floor"){{
            attributes.set(Attribute.water,-1);

            variants = 3;
            mapColor = Color.valueOf("BB7D46");
        }};

        sulfurWall = new StaticWall("sulfur-wall"){{
            mapColor = Color.valueOf("5F5D54");
        }};

        basaltBoulder = new TallBlock("basalt-boulder"){{
            variants = 3;
            shadowAlpha = 1;
            shadowOffset = 3;
        }};

        iceCrystal = new TallBlock("ice-crystal"){{
            variants = 2;
            shadowAlpha = 1;
            shadowOffset = 3;
        }};

        witheredTree = new TallBlock("withered-tree"){{
            variants = 1;
            shadowAlpha = 1;
            shadowOffset = 3;
        }};


        //Transport
        connector = new Router("connector"){{
            requirements(Category.distribution,ItemStack.with(SWItems.Bi,5));

            health = 60;
            speed = 1;
            size = 1;
        }};

        deliverer = new MassDriver("deliverer"){{
            requirements(Category.distribution,ItemStack.with(SWItems.Bi,10,Items.silicon,10,Items.graphite,5,Items.tungsten,5,Items.oxide,3));

            health = 300;
            size = 3;

            minDistribute = 30;
            itemCapacity = 180;
            reload = 25;
            range = 400;

            consumePower(0.1f);
        }};

        ejector = new MassDriver("ejector"){{
            requirements(Category.distribution,ItemStack.with(SWItems.Bi,5));
            hasPower = false;

            health = 150;
            size = 2;

            minDistribute = 10;
            itemCapacity = 60;
            reload = 40;
            range = 150;
        }};

        mechanicalUnloader = new DirectionalUnloader("mechanical-unloader"){{
            requirements(Category.distribution,ItemStack.with(SWItems.Bi,30,Items.silicon,20));
            underBullets = true;
            solid = false;

            health = 150;
            size = 1;
            speed = 0.03f;
        }};

        tungstenConveyor = new StackConveyor("tungsten-conveyor"){{
            requirements(Category.distribution,ItemStack.with(SWItems.Bi,5,Items.tungsten,2));
            hasPower = true;
            insulated = true;

            health = 200;
            speed = 0.03f;
            itemCapacity = 10;
        }};

        tungstenConveyorBridge = new DuctBridge("tungsten-conveyor-bridge"){{
            requirements(Category.distribution,ItemStack.with(SWItems.Bi,20,Items.tungsten,10));
            hasPower = false;
            insulated = true;
            squareSprite = false;

            health = 600;
            size = 1;
            speed = 2;
            range = 6;
            itemCapacity = 10;
        }};

        tungstenSeparator = new StackRouter("tungsten-separator"){{
            requirements(Category.distribution,ItemStack.with(SWItems.Bi,10,Items.tungsten,10));
            hasPower = false;
            insulated = true;
            underBullets = true;

            health = 600;
            size = 1;
            speed = 0.01f;
            itemCapacity = 10;
        }};


        //Storge
        explorerCore = new CoreBlock("explorer-core"){{
            requirements(Category.effect, ItemStack.with(SWItems.Bi,400));
            alwaysUnlocked = true;

            health = 3000;
            armor = 1;
        }};

        sentinelCore = new CoreBlock("sentinel-core"){{
            requirements(Category.effect, ItemStack.with(Items.oxide,200,Items.tungsten,400,SWItems.Bi,500,SWItems.laiteAlloy,200));
            alwaysUnlocked = true;

            health = 5000;
            armor = 10;
        }};




        //Crafter
        condensationChamber = new ThermalGenerator("condensation-chamber"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,50, Items.graphite,40));
            attributes.set(Attribute.steam,1);
            hasLiquids = true;
            displayEfficiency = false;
            group = BlockGroup.liquids;

            health = 250;
            size = 3;

            outputLiquid = new LiquidStack(Liquids.water,0.09f);
            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawLiquidRegion(){{
                        drawLiquid = Liquids.water;
                    }},
                    new DrawBlurSpin(){{
                        suffix = "-rotator";
                        rotateSpeed = 6;
                    }}
            );

            effectChance = 0.4f;
            //ambientSound = Sounds.hum;
            ambientSoundVolume = 1;
        }};

        fusedSilicaFurnace = new GenericCrafter("fused-silica-furnace"){{
            requirements(Category.crafting,ItemStack.with(Items.oxide,30,Items.graphite,185,Items.tungsten,85,SWItems.Bi,185,SWItems.In,150));
            hasLiquids = true;
            hasPower = true;

            health = 850;
            size = 4;

            craftTime = 140;
            itemCapacity = 60;
            liquidCapacity = 60;
            outputItem = new ItemStack(Items.silicon,6);

            drawer = new DrawMulti(
                    new DrawRegion(){{
                       suffix = "-bottom";
                    }},
                    new DrawLiquidRegion(){{
                        drawLiquid = Liquids.water;
                    }},
                    new DrawDefault(),
                    new DrawGlowRegion(){{
                        color = Color.valueOf("D1EFFF");
                    }},
                    new DrawCrucibleFlame(){{
                        flameColor = Color.valueOf("D1EFFF");
                    }}
            );

            consumeItems(ItemStack.with(Items.sand,6));
            consumeLiquids(LiquidStack.with(Liquids.hydrogen,0.5f));
            consumePower(5);
        }};

        silicaFusionFurnace = new HeatCrafter("silica-fusion-furnace"){{
            requirements(Category.crafting,ItemStack.with(Items.graphite,100,SWItems.Bi,40,SWItems.In,80));
            hasLiquids = true;
            hasPower = true;

            health = 450;
            size = 3;

            craftTime = 180;
            itemCapacity = 20;
            liquidCapacity = 24;
            outputItem = new ItemStack(Items.silicon,6);

            drawer = new DrawMulti(
                    new DrawRegion(){{
                        suffix = "-bottom";
                    }},
                    new DrawArcSmelt(),
                    new DrawDefault()
            );

            heatRequirement = 3;
            consumeItems(ItemStack.with(Items.sand,5,Items.coal,4));
            consumeLiquids(LiquidStack.with(Liquids.hydrogen,0.5f));
            consumePower(5);
        }};

        solarThermalConcentrator = new HeatProducer("solar-thermal-concentrator"){{
            requirements(Category.crafting,ItemStack.with(Items.graphite,70,SWItems.Bi,100));

            health = 250;
            size = 2;

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawHeatOutput(),
                    new DrawHeatInput("-heat")
            );

            heatOutput = 3;
        }};

        combustionHeater = new HeatProducer("combustion-heater"){{
            requirements(Category.crafting,ItemStack.with(Items.graphite,50,Items.silicon,20,Items.oxide,20,SWItems.Bi,50));

            health = 400;
            size = 3;

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawHeatOutput(),
                    new DrawHeatInput("-heat")
            );

            heatOutput = 7;
            consumeItem(Items.coal,1);
        }};

        graphitePress = new GenericCrafter("graphite-press"){{
            requirements(Category.crafting,ItemStack.with(Items.oxide,30,Items.graphite,185,Items.tungsten,85,SWItems.Bi,185,SWItems.In,150));
            hasLiquids = true;
            hasPower = true;

            health = 850;
            size = 4;

            craftTime = 140;
            itemCapacity = 30;
            liquidCapacity = 60;
            outputItem = new ItemStack(Items.graphite,6);

            /*
            drawer = new DrawMulti(
                    new DrawRegion(){{
                        suffix = "-bottom";
                    }},
                    new DrawLiquidRegion(){{
                        drawLiquid = Liquids.water;
                    }},
                    new DrawDefault(),
                    new DrawGlowRegion(){{
                        color = Color.valueOf("D1EFFF");
                    }},
                    new DrawCrucibleFlame(){{
                        flameColor = Color.valueOf("D1EFFF");
                    }}
            );
            */

            consumeItems(ItemStack.with(Items.coal,5));
            consumeLiquids(LiquidStack.with(Liquids.water,0.5f));
        }};

        surgeAlloyForge = new HeatCrafter("surge-alloy-forge"){{
            requirements(Category.crafting,ItemStack.with(Items.oxide,40,Items.silicon,120,Items.tungsten,120,SWItems.In,40));
            hasLiquids = true;

            health = 850;
            size = 4;

            itemCapacity = 30;
            liquidCapacity = 30;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(){{
                        drawLiquid = Liquids.slag;
                    }},
                    new DrawDefault(),
                    new DrawHeatInput("-beat")
            );

            craftTime = 180;
            heatRequirement = 12;
            outputItem = new ItemStack(Items.surgeAlloy,2);
            consumePower(3);
            consumeItems(ItemStack.with(Items.silicon,2,SWItems.In,2));
            consumeLiquids(LiquidStack.with(Liquids.slag,0.3f,Liquids.hydrogen,0.2f));
        }};

        wrightAlloyFoundry = new HeatCrafter("wright-alloy-foundry"){{
            requirements(Category.crafting,ItemStack.with(Items.oxide,40,Items.silicon,120,Items.tungsten,120,SWItems.In,40));
            hasLiquids = true;

            health = 850;
            size = 4;

            itemCapacity = 30;
            liquidCapacity = 30;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(){{
                        drawLiquid = Liquids.slag;
                    }},
                    new DrawDefault(),
                    new DrawHeatInput("-beat")
            );

            craftTime = 180;
            heatRequirement = 8;
            outputItem = new ItemStack(Items.surgeAlloy,2);
            consumePower(3);
            consumeItems(ItemStack.with(Items.silicon,2,SWItems.In,2));
            consumeLiquids(LiquidStack.with(Liquids.slag,0.3f,Liquids.hydrogen,0.2f));
        }};

        oxidationChamber = new HeatProducer("oxidation-chamber"){{
            requirements(Category.crafting,ItemStack.with(Items.silicon,65,Items.tungsten,100,SWItems.In,240,SWItems.Bi,180));

            health = 850;
            size = 4;

            itemCapacity = 20;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(){{
                        drawLiquid = Liquids.ozone;
                    }},
                    new DrawDefault(),
                    new DrawHeatInput("-heat"),
                    new DrawRegion("-top"),
                    new DrawHeatOutput(),
                    new DrawGlowRegion(){{
                        color = Color.valueOf("D1EFFF");
                    }}
            );

            craftTime = 120;
            heatOutput = 7;
            outputItem = new ItemStack(Items.oxide,1);
            consumePower(1.3f);
            consumeItem(SWItems.Bi,2);
            consumeLiquid(Liquids.ozone,0.2f);
        }};

        decayChamber = new HeaterGenerator("decay-chamber"){{
            requirements(Category.crafting,ItemStack.with(Items.silicon,65,Items.tungsten,100,SWItems.In,240,SWItems.Bi,180));

            health = 850;
            size = 4;

            itemCapacity = 20;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawCrucibleFlame(){{
                        flameColor = Color.valueOf("92DD7E");
                        flameRadiusScl = 5;
                        flameRadiusMag = 5;
                    }},
                    new DrawDefault(),
                    new DrawHeatOutput()
            );

            consumeItem(SWItems.U,2);
        }};

        electrolysisChamber = new GenericCrafter("electrolysis-chamber"){{
            requirements(Category.crafting,ItemStack.with(Items.silicon,50,Items.graphite,40,SWItems.Bi,60,SWItems.In,40));
            hasLiquids = true;

            health = 250;
            size = 2;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidRegion(Liquids.water),
                    new DrawDefault()
            );

            craftTime = 60;
            consumePower(0.3f);
            consumeLiquid(Liquids.water,0.3f);
        }};

        cyanidePyroFilter = new HeatCrafter("cyanide-pyro-filter"){{
            requirements(Category.crafting,ItemStack.with(Items.oxide,20,SWItems.Bi,90,SWItems.U,90));
            hasLiquids = true;

            health = 850;
            size = 4;

            itemCapacity = 30;
            liquidCapacity = 60;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(){{
                        drawLiquid = Liquids.cyanogen;
                    }},
                    new DrawDefault()
            );

            craftTime = 160;
            heatRequirement = 10;
            outputItem = new ItemStack(Items.surgeAlloy,2);
            consumePower(3);
            consumeItems(ItemStack.with(Items.graphite,2));
            consumeLiquids(LiquidStack.with(Liquids.nitrogen,0.2f));
        }};

        laserIncinerator = new Incinerator("laser-incinerator"){{
            requirements(Category.crafting,ItemStack.with(Items.tungsten,30,SWItems.In,20));

            health = 80;
            size = 1;

            consumePower(0.5f);
        }};

        heatTransmitter = new HeatConductor("heat-transmitter"){{
            requirements(Category.crafting,ItemStack.with(Items.graphite,30,SWItems.Bi,30));

            size = 2;
            health = 250;

            researchCostMultiplier = 0.1f;

            regionRotated1 = 1;

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawHeatInput("-heat")
            );
        }};

        heatSeparator = new HeatConductor("heat-separator"){{
            requirements(Category.crafting,ItemStack.with(Items.graphite,30,SWItems.Bi,30));
            splitHeat = true;

            size = 2;
            health = 250;

            researchCostMultiplier = 0.1f;

            regionRotated1 = 1;

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawHeatInput("-heat")
            );
        }};

        component = new BuildTurret("component"){{
            requirements(Category.effect,ItemStack.with(SWItems.Bi,15,SWItems.In,5));

            size = 2;
            health = 250;

            range = 85;
            buildSpeed = 0.25f;
        }};

        gear = new BuildTurret("gear"){{
            requirements(Category.effect,ItemStack.with(Items.graphite,60,Items.silicon,100,Items.tungsten,50));

            size = 3;
            health = 450;

            range = 110;
            buildSpeed = 0.6f;
        }};

        squint = new Radar("squint"){{
            requirements(Category.effect,ItemStack.with(SWItems.Bi,30));

            size = 3;
            health = 550;

            fogRadius = 25;
        }};

        skyEye = new Radar("sky-eye"){{
            requirements(Category.effect,ItemStack.with(Items.graphite,60,Items.silicon,100,Items.tungsten,50));

            size = 3;
            health = 550;

            fogRadius = 50;

            consumePower(1);
        }};

        horizonEye = new Radar("horizon-eye"){{
            requirements(Category.effect,ItemStack.with(Items.oxide,10,Items.silicon,15,SWItems.Bi,30,SWItems.laiteAlloy,5));

            size = 4;
            health = 1350;

            fogRadius = 120;

            consumePower(3);
        }};

        maintenanceStation = new RepairTower("maintenance-station"){{
            requirements(Category.effect,ItemStack.with(Items.oxide,10,Items.silicon,15,SWItems.Bi,30,SWItems.laiteAlloy,5));

            size = 4;
            health = 1350;

            fogRadius = 120;

            consumePower(3);
        }};

        //Power
        blastingGenerator = new ConsumeGenerator("blasting-generator"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,100,Items.silicon,45,Items.graphite,35,Items.tungsten,50,Items.oxide,10));
            hasLiquids = true;

            health = 750;
            size = 3;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidRegion(Liquids.ozone),
                    new DrawDefault(),
                    new DrawGlowRegion(){{
                        color = Color.valueOf("FFCBDD");
                    }}
            );

            powerProduction = 4.2f;
            consumeLiquids(LiquidStack.with(Liquids.water,1,Liquids.ozone,0.3f));
        }};

        combustionGenerator = new ConsumeGenerator("combustion-generator"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,100,Items.silicon,35,Items.graphite,55,Items.tungsten,50,Items.oxide,40));
            hasLiquids = true;

            health = 750;
            size = 3;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidRegion(Liquids.hydrogen),
                    new DrawDefault(),
                    new DrawGlowRegion(){{
                        color = Color.valueOf("BC5452");
                    }}
            );

            powerProduction = 4.2f;
            consumeItems(ItemStack.with(Items.coal,3));
            consumeLiquids(LiquidStack.with(Liquids.hydrogen,0.3f));
        }};

        electricHub = new BeamNode("electric-hub"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,60,Items.silicon,60,Items.oxide,30));

            health = 1000;
            size = 3;

            range = 33;
            fogRadius = 1;
            laserColor2 = Color.valueOf("FFD37F");

            consumePowerBuffered(10000);
        }};

        electricNode = new BeamNode("electric-node"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,60,Items.silicon,60,Items.oxide,30));

            health = 100;
            size = 1;

            range = 11;
            fogRadius = 1;
            laserColor2 = Color.valueOf("FFD37F");

            consumePowerBuffered(2000);
        }};

        nitrogenWell = new ThermalGenerator("nitrogen-well"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,70,Items.graphite,150,Items.tungsten,50));
            attributes.set(Attribute.steam,0.15f);

            health = 450;
            size = 3;

            minEfficiency = 7;
            displayEfficiency = false;
            displayEfficiencyScale = 0.1111f;

            //ambientSound = Sounds.hum;
            ambientSoundVolume = 1;

            effectChance = 0.4f;

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawBlurSpin("-rotator",6)
            );

            powerProduction = 0.42f;
        }};



        rotorGenerator = new ImpactReactor("rotor-generator"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,45,Items.silicon,15,Items.graphite,25));
            placeableLiquid = true;

            health = 450;
            size = 3;

            itemCapacity = 20;
            liquidCapacity = 100;

            explosionDamage = 400;
            explosionRadius = 25;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidRegion(Liquids.water),
                    new DrawRegion("-rotator",60,true),
                    new DrawDefault()
            );

            warmupSpeed = 0.005f;
            powerProduction = 3.8f;
            consumePower(1.2f);
            consumeItems(ItemStack.with(Items.graphite,1));
            consumeLiquids(LiquidStack.with(Liquids.water,0.62f));
        }};

        thermalStation = new ThermalGenerator("thermal-station"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,150,Items.silicon,90,Items.tungsten,50,Items.oxide,50));

            health = 300;
            size = 3;

            displayEfficiency = false;
            displayEfficiencyScale = 0.1666f;
            generateEffect = Fx.redgeneratespark;
            //ambientSound = Sounds.hum;

            attributes.set(Attribute.heat,1);

            powerProduction = 0.13f;
        }};

        thermoelectricGenerator = new VariableReactor("thermoelectric-generator"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,200,Items.silicon,100,Items.graphite,100,Items.tungsten,50,Items.oxide,40,Items.surgeAlloy,5));

            health = 650;
            size = 3;

            hasLiquids = true;
            liquidCapacity = 80;

            explosionDamage = 500;
            explosionRadius = 30;
            explosionShakeDuration = 10;

            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidRegion(Liquids.water),
                    new DrawDefault()
            );

            maxHeat = 6;
            powerProduction = 5.3f;
            consumeLiquid(Liquids.water,0.8f);
        }};

        tidalGenerator = new SolarGenerator("tidal-generator"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,5,SWItems.In,10));
            placeableLiquid = true;
            requiresWater = true;

            health = 250;
            size = 2;

            powerProduction = 1.5f;
        }};

        nuclearReactor = new HeaterGenerator("nuclear-reactor"){{
            requirements(Category.power,ItemStack.with(SWItems.Bi,500,Items.silicon,300,Items.oxide,800,Items.surgeAlloy,100,SWItems.U,500));
            explodeOnFull = true;

            health = 5000;
            size = 8;

            hasItems = true;
            hasLiquids = true;
            itemCapacity = 120;
            liquidCapacity = 1200;

            explosionDamage = 1750;
            explosionRadius = 480;
            explosionPuddleLiquid = SWLiquids.radioactiveWater;
            explosionPuddles = 200;

            destroyBullet = new BasicBulletType(){{
                instantDisappear = true;

                splashDamage = 1;
                splashDamageRadius = 600;

                //status = SWStatusEffects.

                fragVelocityMin = 0.3f;
                fragVelocityMax = 1;
                fragBullet = new BasicBulletType(8,800){{
                    absorbable = false;
                    hittable = false;
                    reflectable = false;

                    drag = 0.012f;

                    parts.addAll(
                            new FlarePart(){{

                            }}
                    );

                    splashDamage = 800;
                    splashDamageRadius = 80;
                }};
            }};

            drawer = new DrawMulti(
                    new DrawRegion("-button"),
                    new DrawCrucibleFlame(){{
                        flameColor = Color.valueOf("92DD7E");
                        flameRadiusScl = 10;
                        flameRadiusMag = 10;
                    }},
                    new DrawPlasma(){{
                        suffix = "-p-";
                        plasma1 = Color.valueOf("9BDE7E");
                        plasma2 = Color.valueOf("9BDE7E");
                    }},
                    new DrawDefault(),
                    new DrawLiquidRegion(SWLiquids.radioactiveWater),
                    new DrawHeatOutput()
            );

            powerProduction = 160;
            heatOutput = 40;
        }};

        //Production

        //Crafter

        //Defense
        superProMaxPlusPlusWall = new BetterWall("super-pro-max-plus-plus-wall"){{
            requirements(Category.defense,ItemStack.with(Items.copper,800000000));
            limitDamage = true;

            health = 8000;
            size = 2;

            healOnHit = 100;
            maximumDamageTaken = 325;
            percentHealOnHit = 0.05f;
            retaliation = 200;
            percentRetaliation = 1;
            flatDamageReduction1 = 2000;
            flatDamageReduction2 = 8000;
            percentDamageReduction = 0.5f;
        }};

        //tekpose
        mixedTungstenWall = new Wall("mixed-tungsten-wall"){{}};

        cobaltWall = new Wall("cobalt-wall");

        cobaltWallLarge = new Wall("cobalt-wall-large");

        armoredDoor = new Wall("armored-door"){{

        }};



        //Turrets
        //tekpose
        engulf = new ItemTurret("engulf"){{
            requirements(Category.turret,ItemStack.with(SWItems.Te,30,SWItems.Li,25,Items.metaglass,20));
            consumePower(4);

            targetAir = true;
            targetGround = false;

            health = 300;
            armor = 1;
            size = 2;

            reload = 160;
            range = 380;
            shootY = 5;
            minWarmup = 0.7f;
            //shootSound = Sounds.shootAlt;
            shoot = new ShootPattern(){{
                firstShotDelay = 22;
                shots = 3;
                shotDelay = 10;
            }};

            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-barrel"){{
                    mirror = false;
                    under = true;
                    progress = PartProgress.recoil;

                    x = 0;
                    y = 0;
                    moveX = 0;
                    moveY = -3;
                }});
                parts.add(new RegionPart("-front"){{
                    mirror = true;
                    under = true;
                    progress = PartProgress.recoil;

                    x = 0;
                    y = 0;
                    moveX = 0;
                    moveY = -2;
                    moveRot = 2;
                }});
                parts.add(new RegionPart("-back"){{
                    mirror = true;
                    under = true;
                    progress = PartProgress.recoil;

                    x = 0;
                    y = 0;
                    moveX = -1;
                    moveY = 1;
                    moveRot = 3;
                }});
            }};

            ammo(
                    SWItems.Li,new FlakBulletType(6,40){{
                        lifetime = 80;

                        frontColor = Color.valueOf("A4A5F5");
                        backColor = Color.valueOf("A4A5F5");
                        trailColor = Color.valueOf("A4A5F5");
                        height = 10;
                        width = 6;
                        trailLength = 5;
                        trailWidth = 2;

                        splashDamage = 25;
                        splashDamageRadius = 25;

                        status = SWStatusEffects.root;
                        statusDuration = 80;

                        chargeEffect = SWFx.engulfCharge1;
                        shootEffect = SWFx.engulfShoot1;
                        smokeEffect = Fx.none;
                        hitEffect = SWFx.engulfHit1;
                    }},
                    Items.metaglass,new FlakBulletType(6,40){{
                        lifetime = 80;

                        frontColor = Color.white;
                        backColor = Color.white;
                        trailColor = Color.white;
                        height = 10;
                        width = 6;
                        trailLength = 5;
                        trailWidth = 2;

                        splashDamage = 25;
                        splashDamageRadius = 50;

                        status = SWStatusEffects.root;
                        statusDuration = 140;

                        chargeEffect = SWFx.engulfCharge2;
                        shootEffect = SWFx.engulfShoot2;
                        smokeEffect = Fx.none;
                        hitEffect = SWFx.engulfHit2;
                    }}
            );
        }};

        guardianWisp = new ItemTurret("guardian-wisp"){{
            requirements(Category.turret,ItemStack.with(SWItems.Te,30,SWItems.Li,25,Items.metaglass,20));
            consumePower(3);

            targetAir = false;
            targetGround = true;

            health = 300;
            armor = 1;
            size = 2;

            maxAmmo = 30;
            ammoPerShot = 3;

            reload = 40;
            range = 200;
            shootY = 5;
            //shootSound = Sounds.shootAlt;
            shoot = new ShootPattern(){{
                firstShotDelay = 22;
                shots = 3;
                shotDelay = 10;
            }};

            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-barrel"){{
                    mirror = false;
                    under = false;
                    progress = PartProgress.recoil;

                    x = 0;
                    y = 0;
                    moveX = 0;
                    moveY = -5;
                }});
            }};

            ammo(
                    SWItems.Te,new ArtilleryBulletType(10,20) {{
                        lifetime = 30;

                        frontColor = Color.valueOf("92DD7E");
                        backColor = Color.valueOf("92DD7E");
                        trailColor = Color.valueOf("92DD7E");
                        height = 12;
                        width = 8;

                        trailChance = 0;
                        trailLength = 24;
                        trailWidth = 2;

                        splashDamage = 35;
                        splashDamageRadius = 64;

                        status = SWStatusEffects.root;
                        statusDuration = 30;

                        despawnEffect = new WrapEffect(Fx.dynamicSpikes,Color.valueOf("92DD7E"),20);
                    }}
            );
        }};

        spine = new ItemTurret("spine"){{
            requirements(Category.turret,ItemStack.with(SWItems.Te,30,SWItems.Li,25,Items.metaglass,20));
            consumePower(3);

            targetAir = false;
            targetGround = true;

            health = 300;
            armor = 1;
            size = 2;

            maxAmmo = 20;
            ammoPerShot = 2;

            reload = 6;
            range = 180;
            shootY = 5;

            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-barrel"){{
                    mirror = false;
                    under = false;
                    progress = PartProgress.recoil;

                    x = 0;
                    y = 0;
                    moveX = 0;
                    moveY = -5;
                }});
            }};

            ammo(
                    SWItems.Te,new BasicBulletType(16,24) {{
                        lifetime = 12;
                        drag = 0.03f;

                        frontColor = Color.valueOf("989AA4");
                        backColor = Color.valueOf("989AA4");
                        height = 12;
                        width = 4;

                        pierce = true;
                        pierceCap = 4;

                        status = SWStatusEffects.root;
                        statusDuration = 10;

                        despawnEffect = SWFx.fan;
                        //despawnEffect = SWFx.spineDespawn;
                    }}
            );
        }};

        starLight = new ContinuousTurret("star-light"){{
            requirements(Category.turret,ItemStack.with(SWItems.Te,50,SWItems.Li,25,Items.metaglass,15,Items.silicon,20));
            consumePower(4.5f);
            unitSort = UnitSorts.strongest;


            targetAir = true;
            targetGround = true;

            health = 600;
            armor = 1;
            size = 3;

            reload = 6;
            range = 250;
            rotateSpeed = 3;
            shootY = 1;
            shootWarmupSpeed = 0.06f;

            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-wing"){{
                    mirror = true;
                    under = true;
                    outline = false;
                    heatProgress = PartProgress.warmup;

                    x = -4;
                    y = -2;
                    moveX = -1;
                    moveY = -6;
                    moveRot = -230;
                    heatColor = Color.valueOf("E34047");
                }});
                parts.add(new RegionPart("-wing"){{
                    mirror = true;
                    under = true;
                    outline = false;
                    heatProgress = PartProgress.warmup;

                    x = -4;
                    y = -2;
                    moveX = -1;
                    moveY = -9;
                    moveRot = -200;
                    heatColor = Color.valueOf("F15E4E");
                }});
                parts.add(new RegionPart("-wing"){{
                    mirror = true;
                    under = true;
                    outline = false;
                    heatProgress = PartProgress.warmup;

                    x = -4;
                    y = -2;
                    moveX = -1;
                    moveY = -11.5f;
                    moveRot = -160;
                    heatColor = Color.valueOf("FF7B54");
                }});
                parts.add(new RegionPart("-back"){{
                    mirror = true;
                    progress = PartProgress.recoil;
                    heatProgress = PartProgress.warmup;

                    x = 0;
                    y = 0;
                    moveX = 1.5f;
                    moveY = -3;
                    moveRot = 20;
                    heatColor = Color.valueOf("FF705580");
                }});
                parts.add(new RegionPart("-back1"){{
                    mirror = false;
                    progress = PartProgress.recoil;
                    heatProgress = PartProgress.warmup;

                    x = 0;
                    y = 0;
                    moveX = 1.5f;
                    moveY = -3;
                    moveRot = 20;
                    heatColor = Color.valueOf("FF705580");
                }});
                parts.add(new RegionPart("-front"){{
                    mirror = true;
                    progress = PartProgress.recoil;
                    heatProgress = PartProgress.warmup;

                    x = 0;
                    y = 0;
                    moveX = 1;
                    moveY = -2;
                    moveRot = -10;
                    heatColor = Color.valueOf("FF705580");
                }});
            }};

            shootType = new PointLaserBulletType(){{
                sprite = "sky-warpath-star-laser";
                buildingDamageMultiplier = 0.3f;
                damage = 30;
                shake = 0.6f;
            }};
        }};

        flux = new LiquidTurret("flux"){{
            requirements(Category.turret,ItemStack.with(SWItems.Te,30,SWItems.Li,25,Items.metaglass,20));
            consumePower(2);

            targetAir = true;
            targetGround = true;

            health = 300;
            armor = 1;
            size = 2;

            liquidCapacity = 200;

            reload = 6;
            range = 210;
            rotateSpeed = 7.5f;
            inaccuracy = 1;
            shootY = 5;

            ammo(
                    Liquids.water,new LiquidBulletType(Liquids.water){{
                        lifetime = 30;
                        speed = 7;

                        damage = 3;
                        knockback = 2;

                        status = StatusEffects.wet;
                        statusDuration = 60;

                        orbSize = 4;
                        puddleSize = 5;
                    }}
            );
        }};

        emberFall = new ItemTurret("ember-fall"){{
            requirements(Category.turret,ItemStack.with(SWItems.Te,30,Items.titanium,25));
            consumePower(0.3f);

            targetAir = true;
            targetGround = true;

            health = 450;
            armor = 3;
            size = 3;

            ammo(
                    Items.coal,new BasicBulletType(10,5.5f,"circle"){{
                        collides = true;
                        pierce = true;


                    }}
            );
        }};


        starFall = new PowerTurret("star-fall"){{
            requirements(Category.turret,ItemStack.with(Items.titanium,2500));
            consumePower(5);

            health = 1350;
            armor = 10;
            size = 4;

            range = 272;
            rotateSpeed = 1.3f;

            shootType = new ArtilleryBulletType(20,1750){{
                splashDamage = 20;
                splashDamageRadius = 20;
                //shootEffect = SWFx.starFallSpawnSmall;

                despawnEffect = SWFx.starFallSpawn;

                fragBullets = 1;
                fragLifeMax = 1;
                fragLifeMin = 1;
                fragBullet = new FallingBulletType(){{
                    lifetime = 66;
                    speed = 0;

                    despawnEffect = SWFx.starFallExplosion;

                    fallingInterval = 8;
                    explosionEffect = SWFx.starFallSpawnSmall;
                    fallingSpawnBullet = new BulletType(){{
                        lifetime = 120;
                        speed = 0;

                        splashDamage = 350;
                        splashDamageRadius = 40;

                        despawnEffect = SWFx.starFallExplosionSmall;
                    }};
                }};
            }};
        }};
        gungnir = new ChargePowerTurret("gungnir"){{
            requirements(Category.turret,ItemStack.with(Items.titanium,2500));
            consumePower(5);

            health = 3000;
            size = 5;

            drawer = new DrawTurret(){{
                parts.addAll(
                        new RegionPart("-bottom"){{
                            mirror = false;

                            moveX = 0;
                            moveY = 0;
                            moveRot = 0;
                            layer = 0;
                        }},
                        new RegionPart("-side"){{
                            progress = PartProgress.reload.curve(Interp.circleOut);
                            mirror = true;

                            x = 11.5f;
                            y = 1;
                            rotation = 12;
                            moveRot = -15;
                            moveX = 1.5f;
                            moveY = -1.5f;
                        }},
                        new RegionPart("-string-back"){{
                            progress = PartProgress.reload.curve(Interp.circleOut);
                            mirror = true;

                            x = 6;
                            y = -12;
                            moveX = 2;
                            moveY = 10;
                            rotation = -70;
                            moveRot = -15;
                            layer = 30;
                        }},
                        new RegionPart("-string-front"){{
                            progress = PartProgress.reload.curve(Interp.circleOut);
                            mirror = true;

                            x = 6;
                            y = 12;
                            moveX = 2;
                            moveY = 10;
                            rotation = -45;
                            moveRot = -46;
                            layer = 30;
                        }},
                        new RegionPart("-winch"){{
                            progress = PartProgress.reload.curve(Interp.circleOut);
                            mirror = false;

                            x = 0;
                            y = 6;
                            moveX = 0;
                            moveY = 0;
                            moveRot = 180;
                            layer = 30;
                        }}
                );
            }};

            reload = 150;

            chargeInterval = -1;

            shootWarmupSpeed = 0.1f;
            minWarmup = 0.9f;
            shoot = new ShootPattern(){{
                firstShotDelay = 10;
            }};

            shootType = new BasicBulletType(24,3250){{
                lifetime = 50;

                pierce = true;
                pierceBuilding = true;
                buildingDamageMultiplier = 0.3f;

                knockback = 24;

                shootEffect = SWFx.gungnirShoot;
                hitEffect = SWFx.gungnirHit;

                trailRotation = true;
                trailLength = 36;
                trailWidth = 3;
                trailColor = Pal.surge;
                trailChance = 1;
                trailEffect = SWFx.gungnirTrail;
            }};
        }};
    }
}
