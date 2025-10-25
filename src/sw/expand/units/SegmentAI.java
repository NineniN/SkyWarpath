package sw.expand.units;

import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.util.Tmp;
import mindustry.entities.units.AIController;

public class SegmentAI extends AIController {
    // 运动参数
    public float acceleration = 0.3f;
    public float maxSpeedMultiplier = 1.0f;
    public float idealDistanceRatio = 0.75f;
    public float rotationSmoothing = 0.2f; // 旋转平滑系数

    private Vec2 currentVelocity = new Vec2();
    private float targetRotation = 0f;

    @Override
    public void updateMovement() {
        if(unit instanceof SegmentEntity SE){
            if(SE.isHead){
                super.updateMovement();
                currentVelocity.setZero();
            } else {
                stableBodyMovement();
            }
        }
    }

    public void stableBodyMovement(){
        if(unit instanceof SegmentEntity SE && SE.type instanceof SegmentType type){
            SegmentEntity parent = SE.parent;
            if(parent == null) return;

            float currentDistance = Mathf.dst(SE.x, SE.y, parent.x, parent.y);
            float maxSpeed = SE.speed() * maxSpeedMultiplier;
            float idealDistance = type.maxLinkDistance * idealDistanceRatio;

            // 计算到父体的方向（这是唯一的目标方向）
            targetRotation = Angles.angle(SE.x, SE.y, parent.x, parent.y);

            // 基于距离差计算速度
            float distanceError = currentDistance - idealDistance;
            float kp = 2.0f;
            float desiredSpeed = Mathf.clamp(distanceError * kp, -maxSpeed * 0.3f, maxSpeed);

            // 平滑速度变化
            Vec2 desiredVelocity = Tmp.v1.trns(targetRotation, Math.abs(desiredSpeed));
            currentVelocity.lerpDelta(desiredVelocity, acceleration);

            // 只在需要时移动
            if(currentDistance > idealDistance * 0.9f && currentDistance < idealDistance * 1.1f){
                // 在理想距离附近，大幅减速或停止
                currentVelocity.scl(0.1f);
            }

            // 应用移动
            SE.moveAt(currentVelocity);

            // 关键修改：直接设置旋转，不使用插值
            SE.rotation = targetRotation;
        }
    }
}