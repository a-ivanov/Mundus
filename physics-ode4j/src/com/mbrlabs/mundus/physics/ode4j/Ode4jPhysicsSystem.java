package com.mbrlabs.mundus.physics.ode4j;

import com.badlogic.gdx.graphics.Camera;
import com.github.antzGames.gdx.ode4j.ode.*;
import com.mbrlabs.mundus.commons.mapper.PhysicsComponentConverter;
import com.mbrlabs.mundus.commons.physics.DebugDrawMode;
import com.mbrlabs.mundus.commons.physics.PhysicsState;
import com.mbrlabs.mundus.commons.physics.PhysicsSystem;

import static com.github.antzGames.gdx.ode4j.ode.OdeConstants.*;

public class Ode4jPhysicsSystem implements PhysicsSystem {
    private PhysicsState physicsState = PhysicsState.PAUSED;

    private final DWorld world;
    private final DSpace space;
    private final DJointGroup contactGroup;

    public Ode4jPhysicsSystem() {
        OdeHelper.initODE2(0);
        world = OdeHelper.createWorld();
        space = OdeHelper.createSapSpace(null, DSapSpace.AXES.XZY);
        contactGroup = OdeHelper.createJointGroup();
        initWorld();
    }

    private void nearCallback(Object data, DGeom o1, DGeom o2) {
        DBody b1 = o1.getBody();
        DBody b2 = o2.getBody();
        if (b1 != null && b2 != null && OdeHelper.areConnected(b1, b2)) return;

        int N = 4;
        DContactBuffer contacts = new DContactBuffer(N);
        int n = OdeHelper.collide(o1, o2, N, contacts.getGeomBuffer());
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                DContact contact = contacts.get(i);
                contact.surface.mode = dContactSlip1 | dContactSlip2 | dContactSoftERP | dContactSoftCFM | dContactApprox1;
                if (o1 instanceof DSphere || o2 instanceof DSphere)
                    contact.surface.mu = 20;
                else
                    contact.surface.mu = 0.5;

                contact.surface.slip1 = 0.0;
                contact.surface.slip2 = 0.0;
                contact.surface.soft_erp = 0.8;
                contact.surface.soft_cfm = 0.01;
                DJoint c = OdeHelper.createContactJoint(world, contactGroup, contact);
                c.attach(o1.getBody(), o2.getBody());
            }
        }
    }

    private void initWorld() {
        world.setGravity(0, -1.5, 0);
        world.setCFM(1e-5);
        world.setERP(0.8);
        world.setQuickStepNumIterations(20);
    }

    @Override
    public void update(float delta) {
        if (physicsState == PhysicsState.RUNNING) {
            space.collide(null, this::nearCallback);
        }
    }

    @Override
    public void drawDebug(Camera camera) {

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
        return null;
    }

    @Override
    public void setDebugDrawMode(DebugDrawMode debugDrawMode) {

    }

    @Override
    public PhysicsComponentConverter getPhysicsComponentConverter() {
        return null;
    }

    @Override
    public void dispose() {
        contactGroup.destroy();
        space.destroy();
        world.destroy();
        OdeHelper.closeODE();
    }
}
