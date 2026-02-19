package com.mbrlabs.mundus.commons.mapper;

import com.mbrlabs.mundus.commons.dto.PhysicsBodyComponentDTO;
import com.mbrlabs.mundus.commons.physics.PhysicsSystem;
import com.mbrlabs.mundus.commons.scene3d.GameObject;
import com.mbrlabs.mundus.commons.scene3d.components.AbstractPhysicsBodyComponent;

public class PhysicsBodyComponentConverter {

    /**
     * Converts {@link PhysicsBodyComponentDTO} to {@link AbstractPhysicsBodyComponent}.
     */
    public static AbstractPhysicsBodyComponent convert(PhysicsBodyComponentDTO dto, GameObject go, PhysicsSystem physicsSystem) {
        return physicsSystem.createPhysicsBodyComponent(dto.getMotionType(), dto.getCollisionShape(), dto.getMass(), dto.isDisableDeactivation(), go);
    }

    /**
     * Converts {@link AbstractPhysicsBodyComponent} to {@link PhysicsBodyComponentDTO}.
     */
    public static PhysicsBodyComponentDTO convert(AbstractPhysicsBodyComponent physicsBodyComponent) {
        PhysicsBodyComponentDTO dto = new PhysicsBodyComponentDTO();
        dto.setMotionType(physicsBodyComponent.getMotionType());
        dto.setCollisionShape(physicsBodyComponent.getCollisionShape());
        dto.setMass(physicsBodyComponent.getMass());
        dto.setDisableDeactivation(physicsBodyComponent.isDisableDeactivation());

        return dto;
    }
}
