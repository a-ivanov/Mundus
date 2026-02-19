package com.mbrlabs.mundus.commons.physics;

import com.mbrlabs.mundus.commons.scene3d.GameObject;
import com.mbrlabs.mundus.commons.scene3d.components.AbstractPhysicsBodyComponent;

public enum NoopPhysicsSystem implements PhysicsSystem {
    INSTANCE;

    @Override
    public void update(float delta) {
    }

    @Override
    public PhysicsState getPhysicsState() {
        return PhysicsState.PAUSED;
    }

    @Override
    public void setPhysicsState(PhysicsState physicsState) {
    }

    @Override
    public AbstractPhysicsBodyComponent createPhysicsBodyComponent(MotionType motionType, CollisionShape collisionShape, float mass, boolean disableDeactivation, GameObject go) {
        return null;
    }
}
