package com.mbrlabs.mundus.commons.dto;

import com.mbrlabs.mundus.commons.physics.CollisionShape;
import com.mbrlabs.mundus.commons.physics.MotionType;

public class PhysicsBodyComponentDTO {
    private MotionType motionType;
    private CollisionShape collisionShape;
    private float mass;
    private boolean disableDeactivation;

    public MotionType getMotionType() {
        return motionType;
    }

    public void setMotionType(MotionType motionType) {
        this.motionType = motionType;
    }

    public CollisionShape getCollisionShape() {
        return collisionShape;
    }

    public void setCollisionShape(CollisionShape collisionShape) {
        this.collisionShape = collisionShape;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public boolean isDisableDeactivation() {
        return disableDeactivation;
    }

    public void setDisableDeactivation(boolean disableDeactivation) {
        this.disableDeactivation = disableDeactivation;
    }
}
