package com.mbrlabs.mundus.commons.scene3d.components;

import com.mbrlabs.mundus.commons.physics.CollisionShape;
import com.mbrlabs.mundus.commons.physics.MotionType;
import com.mbrlabs.mundus.commons.scene3d.GameObject;

public abstract class AbstractPhysicsBodyComponent extends AbstractComponent {
    public AbstractPhysicsBodyComponent(GameObject go) {
        super(go);
        type = Type.PHYSICS_BODY;
    }

    public abstract MotionType getMotionType();

    public abstract void setMotionType(MotionType motionType);

    public abstract CollisionShape getCollisionShape();

    public abstract void setCollisionShape(CollisionShape collisionShape);

    public abstract float getMass();

    public abstract void setMass(float mass);

    public abstract boolean isDisableDeactivation();

    public abstract void setDisableDeactivation(boolean disableDeactivation);
}
