package sw.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class SWItems {
    public static Item Li,Bi,In,Te,U,laiteAlloy,vectorFabric,JTK,ITK;

    public static void load(){
        //klest
        JTK = new Item("JTK",Color.valueOf("5757C1")){{
            hardness = 1;
            cost =  2;
        }};
        ITK  = new Item("ITK",Color.valueOf("8A73C6")){{
            hardness = 1;
            cost = 5;
        }};
        Te = new Item("tellurium",Color.valueOf("989AA4")){{
            hardness = 1;
            cost = 2;
        }};
        Li = new Item("lithium", Color.valueOf("87A5FF")){{
            hardness = 1;
            cost = 2;
        }};
        //texboss
        Bi = new Item("bisemutum",Color.valueOf("989AA4")){{
            hardness = 1;
            cost = 2;

            flammability = 0.6f;
        }};
        In = new Item("indium",Color.valueOf("C2CEDE")){{
            hardness = 1;
            cost = 2;

            flammability = 0.3f;
        }};
        U = new Item("uranium",Color.valueOf("84F491")){{
            hardness = 8;
            cost = 2.5f;
            radioactivity = 1.5f;
            explosiveness = 0.1f;
        }};
    }
}
