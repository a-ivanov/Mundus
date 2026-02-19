package com.mbrlabs.mundus.commons.physics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Disposable;
import com.mbrlabs.mundus.commons.mapper.PhysicsComponentConverter;

public interface PhysicsSystem extends Disposable {
    void update(float delta);

    void drawDebug(Camera camera);

    PhysicsState getPhysicsState();

    void setPhysicsState(PhysicsState physicsState);

    DebugDrawMode getDebugDrawMode();

    void setDebugDrawMode(DebugDrawMode debugDrawMode);

    PhysicsComponentConverter getPhysicsComponentConverter();
}
