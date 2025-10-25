package sw.expand.units;

import arc.Events;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.util.Log;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.ai.types.LogicAI;
import mindustry.entities.units.UnitController;
import mindustry.entities.units.WeaponMount;
import mindustry.game.EventType;
import mindustry.gen.Groups;
import mindustry.gen.UnitEntity;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import sw.content.SWUnitTypes;

public class SegmentEntity extends UnitEntity {
    public boolean isHead = false;
    public boolean sneaked = false;
    public SegmentEntity head,parent,next;
    private transient int headId = -1,parentId = -1,nextId = -1;


    @Override
    public int classId() {
        return SWUnitTypes.segmentID;
    }

    @Override
    public void add() {
        super.add();

        if (isHead && !sneaked) createSegments();
    }

    @Override
    public boolean playerControllable() {
        boolean var10000;
        label27: {
            //当且仅当为头部时可操控
            if (this.type.playerControllable && isHead) {
                UnitController var2 = this.controller;
                if (!(var2 instanceof LogicAI)) {
                    break label27;
                }

                LogicAI ai = (LogicAI)var2;
                if (ai.controller == null || !ai.controller.block.privileged) {
                    break label27;
                }
            }

            var10000 = false;
            return var10000;
        }

        var10000 = true;
        return var10000;
    }

    @Override
    public void update() {
        super.update();
        if(!this.sneaked)this.createSegments();

        //if(!isHead)bodyMovement();

        if(this.dead && parent != null && next != null){
            parent.next = null;
            next.isHead = true;

            parent.setupWeapons(parent.type);
            next.setupWeapons(next.type);
        }
    }

    @Override
    public void setupWeapons(UnitType unit) {
        SegmentType def = (SegmentType) unit;
        if(isHead) this.mounts = new WeaponMount[def.headWeapons.size];
        else if(next == null) this.mounts = new WeaponMount[def.tailWeapons.size];
        else this.mounts = new WeaponMount[def.bodyWeapons.size];

        for(int i = 0; i < this.mounts.length; ++i) {
            this.mounts[i] = (WeaponMount)((Weapon)def.weapons.get(i)).mountType.get((Weapon)def.weapons.get(i));
        }
    }

    @Override
    public void draw() {
        super.draw();
    }
    /*
    public void bodyMovement(){
        SegmentType type = (SegmentType) this.type;

        //检测距离
        Tmp.v1.trns(Angles.angle(this.x,this.y,parent.x,parent.y),0);
        if(Mathf.dst(this.x,this.y,parent.x,parent.y) > type.maxLinkDistance){
            Tmp.v2.trns(
                    Angles.angle(this.x,this.y,parent.x + Tmp.v1.x,parent.y + Tmp.v1.y),
                    this.speed()
            );
            this.moveAt(Tmp.v2);
        }
    }
     */

    public void createSegments(){
        Log.info("创建段实体链条，头部ID: @", id);

        SegmentType type = (SegmentType)this.type;
        SegmentEntity current = this;

        for(int i = 0; i < type.bodies - 1; i++){
            SegmentEntity nextSegment = (SegmentEntity) type.create(this.team);
            nextSegment.isHead = false;
            nextSegment.sneaked = true;

            // 设置关系
            current.next = nextSegment;
            nextSegment.parent = current;
            nextSegment.head = this;

            // 设置位置
            nextSegment.set(Tmp.v1.trns(current.rotation + 180, type.maxLinkDistance + 16).add(current));

            // 立即添加到世界
            nextSegment.add();

            current = nextSegment;

            Log.debug("创建段实体 @，父段ID: @", nextSegment.id, current.id);
        }

        this.sneaked = true;
        Log.info("段实体链条创建完成，总段数: @", type.bodies);
    }

    @Override
    public void write(Writes write) {
        super.write(write);

        write.bool(isHead);
        write.bool(sneaked);

        // 安全地写入ID，处理null情况
        write.i(head != null ? head.id : -1);
        write.i(parent != null ? parent.id : -1);
        write.i(next != null ? next.id : -1);

        Log.debug("SegmentEntity.write: id=@, isHead=@, headId=@, parentId=@, nextId=@",
                id, isHead, (head != null ? head.id : -1), (parent != null ? parent.id : -1), (next != null ? next.id : -1));
    }

    @Override
    public void read(Reads read) {
        super.read(read);

        isHead = read.bool();
        sneaked = read.bool();

        // 先保存ID，等所有实体加载完成后再解析关系
        headId = read.i();
        parentId = read.i();
        nextId = read.i();

        Log.debug("SegmentEntity.read: id=@, isHead=@, headId=@, parentId=@, nextId=@",
                id, isHead, headId, parentId, nextId);

        Time.run(3f,this::resolveReferences);
    }

    public void resolveReferences() {
        if(headId != -1) {
            head = (SegmentEntity) Groups.unit.getByID(headId);
            if(head == null) Log.warn("无法找到head实体: id=@", headId);
        }

        if(parentId != -1) {
            parent = (SegmentEntity) Groups.unit.getByID(parentId);
            if(parent == null) Log.warn("无法找到parent实体: id=@", parentId);
        }

        if(nextId != -1) {
            next = (SegmentEntity) Groups.unit.getByID(nextId);
            if(next == null) Log.warn("无法找到next实体: id=@", nextId);
        }

        // 重置临时ID
        headId = parentId = nextId = -1;
    }
}
