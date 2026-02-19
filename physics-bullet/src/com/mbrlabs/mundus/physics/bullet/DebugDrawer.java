package com.mbrlabs.mundus.physics.bullet;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.utils.Disposable;
import com.mbrlabs.mundus.commons.physics.DebugDrawMode;

public class DebugDrawer implements Disposable {
    private DebugDrawMode debugDrawMode = DebugDrawMode.DRAW_WIREFRAME;

    private final com.badlogic.gdx.physics.bullet.DebugDrawer delegate;

    public DebugDrawer(btCollisionWorld collisionWorld) {
        delegate = new com.badlogic.gdx.physics.bullet.DebugDrawer();
        delegate.setDebugMode(convertBtDebugDrawMode(debugDrawMode));
        collisionWorld.setDebugDrawer(delegate);
    }

    public DebugDrawMode getDebugDrawMode() {
        return this.debugDrawMode;
    }

    public void setDebugDrawMode(DebugDrawMode debugDrawMode) {
        this.debugDrawMode = debugDrawMode;
        int btDebugDrawMode = convertBtDebugDrawMode(debugDrawMode);
        delegate.setDebugMode(btDebugDrawMode);
    }

    public void drawDebug(Camera camera, btCollisionWorld collisionWorld) {
        delegate.begin(camera);
        collisionWorld.debugDrawWorld();
        delegate.end();
    }

    @Override
    public void dispose() {
        delegate.dispose();
    }

    private static int convertBtDebugDrawMode(DebugDrawMode debugDrawMode) {
        switch (debugDrawMode) {
            case NO_DEBUG:
                return btIDebugDraw.DebugDrawModes.DBG_NoDebug;
            case DRAW_AABB:
                return btIDebugDraw.DebugDrawModes.DBG_DrawAabb;
            default:
                return btIDebugDraw.DebugDrawModes.DBG_DrawWireframe;
        }
    }
}
