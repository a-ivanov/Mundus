package com.mbrlabs.mundus.commons.physics;

import com.mbrlabs.mundus.commons.scene3d.GameObject;
import com.mbrlabs.mundus.commons.scene3d.components.AbstractPhysicsBodyComponent;

public interface PhysicsSystem {
    void update(float delta);

    PhysicsState getPhysicsState();
    void setPhysicsState(PhysicsState physicsState);

    AbstractPhysicsBodyComponent createPhysicsBodyComponent(MotionType motionType, CollisionShape collisionShape, float mass, boolean disableDeactivation, GameObject go);
}
