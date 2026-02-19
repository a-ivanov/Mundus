package com.mbrlabs.mundus.commons.dto;

import com.mbrlabs.mundus.commons.physics.BodyType;
import com.mbrlabs.mundus.commons.physics.CollisionShape;

public class PhysicsComponentDTO {
    private BodyType bodyType;
    private CollisionShape collisionShape;
    private float mass;
    private boolean disableDeactivation;

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
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
