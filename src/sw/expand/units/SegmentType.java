package sw.expand.units;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Scaled;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.entities.part.DrawPart;
import mindustry.entities.units.WeaponMount;
import mindustry.game.Team;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class SegmentType extends UnitType {
    public float maxLinkDistance = 32;
    public int bodies = 10;

    public TextureRegion headRegion,bodyRegion, tailRegion;
    public Seq<Weapon>
            headWeapons = new Seq<>(Weapon.class),
            bodyWeapons = new Seq<>(Weapon.class),
            tailWeapons = new Seq<>(Weapon.class);
    public Seq<DrawPart>
            headParts = new Seq<>(DrawPart.class),
            bodyParts = new Seq<>(DrawPart.class),
            tailParts = new Seq<>(DrawPart.class);

    public SegmentType(String name){
        super(name);
        engineColor = Color.valueOf("00000000");

        aiController = () -> new SegmentAI();
        controller = u -> new SegmentAI();

    }

    @Override
    public void load() {
        super.load();
        headRegion = Core.atlas.find(name + "-head");
        bodyRegion = Core.atlas.find(name + "-body");
        tailRegion = Core.atlas.find(name + "-tail");

        headWeapons.each(Weapon::load);
        bodyWeapons.each(Weapon::load);
        tailWeapons.each(Weapon::load);

        for(var part : headParts){
            part.load(name + "-head");
        }
        for(var part : bodyParts){
            part.load(name + "-body");
        }
        for(var part : tailParts){
            part.load(name + "-tail");
        }
    }

    @Override
    public Unit create(Team team) {
        Log.info("create() is used.");
        SegmentEntity unit = (SegmentEntity) super.create(team);
        unit.isHead = true;

        return unit;
    }

    @Override
    public void drawBody(Unit u) {
        SegmentEntity unit = (SegmentEntity) u;
        applyColor(unit);

        if(unit.isHead)Draw.rect(headRegion, unit.x, unit.y, unit.rotation - 90);
        else if(unit.next == null)Draw.rect(tailRegion, unit.x, unit.y, unit.rotation - 90);
        else Draw.rect(bodyRegion, unit.x, unit.y, unit.rotation - 90);

        Draw.reset();
    }

    @Override
    public void draw(Unit u) {
        Draw.z(Layer.flyingUnit);
        SegmentEntity unit = (SegmentEntity) u;
        Seq<DrawPart> realParts;
        if(unit.isHead) realParts = headParts;
        else if (unit.next == null) {
            realParts = tailParts;
            Draw.z(Layer.flyingUnit + 3);
        }
        else realParts = bodyParts;

        if(realParts.size > 0){
            for(int i = 0; i < realParts.size; i++){
                var part = realParts.get(i);

                WeaponMount mount = unit.mounts.length > part.weaponIndex ? unit.mounts[part.weaponIndex] : null;
                if(mount != null){
                    DrawPart.params.set(mount.warmup, mount.reload / mount.weapon.reload, mount.smoothReload, mount.heat, mount.recoil, mount.charge, unit.x, unit.y, unit.rotation);
                }else{
                    DrawPart.params.set(0f, 0f, 0f, 0f, 0f, 0f, unit.x, unit.y, unit.rotation);
                }

                if(unit instanceof Scaled s){
                    DrawPart.params.life = s.fin();
                }

                applyColor(unit);
                part.draw(DrawPart.params);
            }
        }

        super.draw(u);
    }
}

