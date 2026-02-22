package com.mbrlabs.mundus.physics.ode4j;

import com.github.antzGames.gdx.ode4j.ode.DBody;
import com.github.antzGames.gdx.ode4j.ode.DGeom;
import com.mbrlabs.mundus.commons.physics.BodyType;
import com.mbrlabs.mundus.commons.scene3d.GameObject;
import com.mbrlabs.mundus.commons.scene3d.components.AbstractPhysicsComponent;
import com.mbrlabs.mundus.commons.scene3d.components.Component;

import java.util.Objects;

public class Ode4jRigidBodyPhysicsComponent extends AbstractPhysicsComponent {
    private final DGeom geom;
    private final DBody body;

    public Ode4jRigidBodyPhysicsComponent(GameObject go, DGeom geom, DBody body) {
        super(go);
        this.geom = Objects.requireNonNull(geom);
        this.body = body;
    }

    @Override
    public BodyType getBodyType() {
        if (body == null) return BodyType.STATIC;
        if (body.getMass() == null) return BodyType.KINEMATIC;
        return BodyType.DYNAMIC;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public Component clone(GameObject go) {
        return null;
    }
}
