package com.mbrlabs.mundus.editor.core.physics

import com.mbrlabs.mundus.commons.physics.CollisionShape
import com.mbrlabs.mundus.commons.physics.MotionType
import com.mbrlabs.mundus.commons.physics.PhysicsState
import com.mbrlabs.mundus.commons.physics.PhysicsSystem
import com.mbrlabs.mundus.commons.scene3d.GameObject
import com.mbrlabs.mundus.commons.scene3d.components.AbstractPhysicsBodyComponent
import com.mbrlabs.mundus.editor.scene3d.components.AuthoringPhysicsBodyComponent

object AuthoringPhysicsSystem : PhysicsSystem {
    override fun update(delta: Float) {
    }

    override fun getPhysicsState(): PhysicsState = PhysicsState.PAUSED;

    override fun setPhysicsState(physicsState: PhysicsState?) {
    }

    override fun createPhysicsBodyComponent(
        motionType: MotionType,
        collisionShape: CollisionShape,
        mass: Float,
        disableDeactivation: Boolean,
        go: GameObject
    ): AbstractPhysicsBodyComponent {
        val physicsBodyComponent = AuthoringPhysicsBodyComponent(go)
        physicsBodyComponent.motionType = motionType
        physicsBodyComponent.collisionShape = collisionShape
        physicsBodyComponent.mass = mass
        physicsBodyComponent.isDisableDeactivation = disableDeactivation

        return physicsBodyComponent
    }
}
