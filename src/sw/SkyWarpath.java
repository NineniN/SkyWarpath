package sw;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import sw.content.*;

public class SkyWarpath extends Mod{
    public static final String MOD_NAME = "sky-warpath";

    public static String name(String name) {
        return MOD_NAME + "-" + name;
    }

    public SkyWarpath(){
        Log.info("Loaded SkyWarpath constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                //dialog.cont.image(Core.atlas.find("sky-warpath-icon")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some sky warpath content.");

        //SWFx.loadTexture();
        SWItems.load();
        SWLiquids.load();
        SWStatusEffects.load();
        SWWeapons.load();
        SWUnitTypes.load();
        SWBlocks.load();
        SWPlanets.load();
        SWSectorPresets.load();
        SWTechTree.load();

    }

}
