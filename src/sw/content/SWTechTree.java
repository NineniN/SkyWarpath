package sw.content;
import arc.struct.Seq;
import mindustry.content.TechTree;
import mindustry.game.Objectives;
import sw.content.*;

public class SWTechTree {
    public static void load(){
        SWPlanets.kleist.techTree = TechTree.nodeRoot("kleist",SWBlocks.explorerCore,() -> {
            //Items
            TechTree.nodeProduce(SWItems.Te,() -> {
                TechTree.nodeProduce(SWItems.Li,() -> {

                });
            });

            //Maps
            TechTree.node(SWSectorPresets.startingLake,() -> {
                TechTree.node(SWSectorPresets.desolateStrait, Seq.with(
                        new Objectives.SectorComplete(SWSectorPresets.startingLake)
                ),() -> {
                    TechTree.node(SWSectorPresets.vastValley, Seq.with(
                            new Objectives.SectorComplete(SWSectorPresets.desolateStrait)
                    ),() -> {
                        TechTree.node(SWSectorPresets.snagsFortress, Seq.with(
                                new Objectives.SectorComplete(SWSectorPresets.vastValley)
                        ),() -> {

                        });
                    });

                    TechTree.node(SWSectorPresets.clampedStrait, Seq.with(
                            new Objectives.SectorComplete(SWSectorPresets.desolateStrait)
                    ),() -> {

                    });
                });
            });
        });
    }
}
