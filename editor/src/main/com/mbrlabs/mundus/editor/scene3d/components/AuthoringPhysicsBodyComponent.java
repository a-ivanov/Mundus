package com.mbrlabs.mundus.editor.scene3d.components;

import com.mbrlabs.mundus.commons.physics.CollisionShape;
import com.mbrlabs.mundus.commons.physics.MotionType;
import com.mbrlabs.mundus.commons.scene3d.GameObject;
import com.mbrlabs.mundus.commons.scene3d.components.AbstractPhysicsBodyComponent;
import com.mbrlabs.mundus.commons.scene3d.components.Component;

/**
 * Only serializes data allowing the derived {@link AbstractPhysicsBodyComponent} to take over at runtime.
 */
public class AuthoringPhysicsBodyComponent extends AbstractPhysicsBodyComponent {
    private MotionType motionType = MotionType.STATIC;
    private CollisionShape collisionShape;
    private float mass;
    private boolean disableDeactivation;

    public AuthoringPhysicsBodyComponent(GameObject go) {
        super(go);
    }

    @Override
    public MotionType getMotionType() {
        return motionType;
    }

    @Override
    public void setMotionType(MotionType motionType) {
        this.motionType = motionType;
    }

    @Override
    public CollisionShape getCollisionShape() {
        return collisionShape;
    }

    @Override
    public void setCollisionShape(CollisionShape collisionShape) {
        this.collisionShape = collisionShape;
    }

    @Override
    public float getMass() {
        return mass;
    }

    @Override
    public void setMass(float mass) {
        this.mass = mass;
    }

    @Override
    public boolean isDisableDeactivation() {
        return disableDeactivation;
    }

    @Override
    public void setDisableDeactivation(boolean disableDeactivation) {
        this.disableDeactivation = disableDeactivation;
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public Component clone(GameObject go) {
        AuthoringPhysicsBodyComponent physicsBodyComponent = new AuthoringPhysicsBodyComponent(go);
        physicsBodyComponent.setMotionType(motionType);
        physicsBodyComponent.setCollisionShape(collisionShape);
        physicsBodyComponent.setMass(mass);
        physicsBodyComponent.setDisableDeactivation(disableDeactivation);

        return physicsBodyComponent;
    }
}
