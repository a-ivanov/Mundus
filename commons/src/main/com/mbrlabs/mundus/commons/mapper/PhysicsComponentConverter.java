package com.mbrlabs.mundus.commons.mapper;

import com.mbrlabs.mundus.commons.dto.PhysicsComponentDTO;
import com.mbrlabs.mundus.commons.scene3d.GameObject;
import com.mbrlabs.mundus.commons.scene3d.components.AbstractPhysicsComponent;

public interface PhysicsComponentConverter {
    AbstractPhysicsComponent convert(PhysicsComponentDTO dto, GameObject go);
    PhysicsComponentDTO convert(AbstractPhysicsComponent component);
}
