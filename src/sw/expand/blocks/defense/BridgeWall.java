package sw.expand.blocks.defense;

import arc.math.geom.Geometry;
import mindustry.Vars;
import mindustry.gen.Building;

public class BridgeWall extends BetterWall {
    public int range = 5;

    public BridgeWall(String name){
        super(name);
    }

    @Override
    public void drawPlace(int x,int y,int rotation,boolean valid){
        for(int i = 0;i < 4;i++){
            int maxLen = range + size / 2;
            Building dest = null;
            var dir = Geometry.d4[i];
            int dx = dir.x, dy = dir.y;
            int offset = size / 2;

            for(int j = 1 + offset;j <= range + offset; j++){
                var other = Vars.world.build(x + j * dir.x,y + j * dir.y);
            }
        }
    }

    public class BridgeWallBuild extends BetterWallBuild{

    }
}
