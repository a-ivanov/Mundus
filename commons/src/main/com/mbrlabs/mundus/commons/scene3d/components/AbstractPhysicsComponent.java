package com.mbrlabs.mundus.commons.scene3d.components;

import com.mbrlabs.mundus.commons.scene3d.GameObject;

public abstract class AbstractPhysicsComponent extends AbstractComponent {
    public AbstractPhysicsComponent(GameObject go) {
        super(go);
        type = Type.PHYSICS;
    }
}
