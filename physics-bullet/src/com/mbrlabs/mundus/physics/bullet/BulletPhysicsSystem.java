package com.mbrlabs.mundus.physics.bullet;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.btConstraintSolver;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.mbrlabs.mundus.commons.mapper.PhysicsComponentConverter;
import com.mbrlabs.mundus.commons.physics.DebugDrawMode;
import com.mbrlabs.mundus.commons.physics.PhysicsState;
import com.mbrlabs.mundus.commons.physics.PhysicsSystem;

public class BulletPhysicsSystem implements PhysicsSystem {
    private static final int MAX_SUB_STEPS = 1;
    private static final float FIXED_TIME_STEP = 1f / 60f;
    private static final float MIN_TIME_STEP = 1f / 30f;

    private PhysicsState physicsState = PhysicsState.PAUSED;

    private final btCollisionConfiguration collisionConfiguration;
    private final btDispatcher dispatcher;
    private final btBroadphaseInterface broadphase;
    private final btConstraintSolver constraintSolver;
    private final btDiscreteDynamicsWorld dynamicsWorld;
    private final DebugDrawer debugDrawer;

    public BulletPhysicsSystem() {
        collisionConfiguration = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfiguration);
        broadphase = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();
        dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, broadphase, constraintSolver, collisionConfiguration);

        collisionConfiguration.obtain();
        dispatcher.obtain();
        broadphase.obtain();
        constraintSolver.obtain();
        dynamicsWorld.obtain();

        debugDrawer = new DebugDrawer(dynamicsWorld);
    }

    public static void init() {
        Bullet.init(true);
    }

    @Override
    public void update(float delta) {
        if (physicsState == PhysicsState.RUNNING) {
            float actualDelta = Math.min(MIN_TIME_STEP, delta);
            dynamicsWorld.stepSimulation(actualDelta, MAX_SUB_STEPS, FIXED_TIME_STEP);
        }
    }

    @Override
    public void drawDebug(Camera camera) {
        debugDrawer.drawDebug(camera, dynamicsWorld);
    }

    @Override
    public PhysicsState getPhysicsState() {
        return this.physicsState;
    }

    @Override
    public void setPhysicsState(PhysicsState physicsState) {
        this.physicsState = physicsState == null ? PhysicsState.PAUSED : physicsState;
    }

    @Override
    public DebugDrawMode getDebugDrawMode() {
        return debugDrawer.getDebugDrawMode();
    }

    @Override
    public void setDebugDrawMode(DebugDrawMode debugDrawMode) {
        debugDrawer.setDebugDrawMode(debugDrawMode);
    }

    @Override
    public PhysicsComponentConverter getPhysicsComponentConverter() {
        return new BulletPhysicsComponentConverter();
    }

    @Override
    public void dispose() {
        dynamicsWorld.release();
        constraintSolver.release();
        broadphase.release();
        dispatcher.release();
        collisionConfiguration.release();

        debugDrawer.dispose();
    }
}
