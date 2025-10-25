package sw.content;

import arc.graphics.Color;
import mindustry.type.CellLiquid;
import mindustry.type.Liquid;

public class SWLiquids {
    public static Liquid radioactiveWater;
    public static void load(){
        radioactiveWater = new CellLiquid("radioactive-water"){{
            color = Color.valueOf("5e988d60");
            boilPoint = 2;
            viscosity = 0;
            heatCapacity = 0;
        }};
    }
}
