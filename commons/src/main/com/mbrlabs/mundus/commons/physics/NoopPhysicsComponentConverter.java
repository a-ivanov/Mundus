package com.mbrlabs.mundus.commons.physics;

import com.mbrlabs.mundus.commons.dto.PhysicsComponentDTO;
import com.mbrlabs.mundus.commons.mapper.PhysicsComponentConverter;
import com.mbrlabs.mundus.commons.scene3d.GameObject;
import com.mbrlabs.mundus.commons.scene3d.components.AbstractPhysicsComponent;

public enum NoopPhysicsComponentConverter implements PhysicsComponentConverter {
    INSTANCE;

    @Override
    public AbstractPhysicsComponent convert(PhysicsComponentDTO dto, GameObject go) {
        return null;
    }

    @Override
    public PhysicsComponentDTO convert(AbstractPhysicsComponent component) {
        return null;
    }
}
