package com.mbrlabs.mundus.commons.physics;

import com.badlogic.gdx.graphics.Camera;
import com.mbrlabs.mundus.commons.mapper.PhysicsComponentConverter;

public enum NoopPhysicsSystem implements PhysicsSystem {
    INSTANCE;

    @Override
    public void update(float delta) {
    }

    @Override
    public void drawDebug(Camera camera) {
    }

    @Override
    public PhysicsState getPhysicsState() {
        return PhysicsState.PAUSED;
    }

    @Override
    public void setPhysicsState(PhysicsState physicsState) {
    }

    @Override
    public DebugDrawMode getDebugDrawMode() {
        return DebugDrawMode.NO_DEBUG;
    }

    @Override
    public void setDebugDrawMode(DebugDrawMode debugDrawMode) {
    }

    @Override
    public PhysicsComponentConverter getPhysicsComponentConverter() {
        return NoopPhysicsComponentConverter.INSTANCE;
    }

    @Override
    public void dispose() {
    }
}
