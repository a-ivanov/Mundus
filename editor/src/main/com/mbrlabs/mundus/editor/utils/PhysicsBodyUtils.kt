package com.mbrlabs.mundus.editor.utils

import com.badlogic.gdx.math.collision.BoundingBox
import com.mbrlabs.mundus.commons.physics.Axis
import com.mbrlabs.mundus.commons.physics.BoxCollisionShape
import com.mbrlabs.mundus.commons.physics.CapsuleCollisionShape
import com.mbrlabs.mundus.commons.physics.CylinderCollisionShape
import com.mbrlabs.mundus.commons.physics.CollisionShape
import com.mbrlabs.mundus.commons.physics.MotionType
import com.mbrlabs.mundus.commons.physics.SphereCollisionShape
import com.mbrlabs.mundus.commons.scene3d.GameObject
import com.mbrlabs.mundus.commons.scene3d.components.Component
import com.mbrlabs.mundus.commons.scene3d.components.ModelComponent
import com.mbrlabs.mundus.commons.utils.Pools

object PhysicsBodyUtils {

    val FALLBACK_COLLISION_SHAPE = BoxCollisionShape().apply {
        width = 1f
        height = 1f
        depth = 1f
    }
    val DEFAULT_MOTION_TYPE = MotionType.STATIC
    const val DEFAULT_MASS = 0f

    fun getModelBounds(go: GameObject): BoundingBox? =
        go.findComponentByType<ModelComponent>(Component.Type.MODEL)?.let {
            it.orientedBoundingBox?.bounds
        }

    fun fitBoxCollisionShape(bounds: BoundingBox): CollisionShape {
        val boxCollisionShape = BoxCollisionShape()
        boxCollisionShape.width = bounds.width
        boxCollisionShape.height = bounds.height
        boxCollisionShape.depth = bounds.depth
        boxCollisionShape.centerX = bounds.centerX
        boxCollisionShape.centerY = bounds.centerY
        boxCollisionShape.centerZ = bounds.centerZ

        return boxCollisionShape
    }

    fun fitSphereCollisionShape(bounds: BoundingBox, inscribe: Boolean): CollisionShape {
        val radius = if (inscribe) {
            val minSide = minOf(bounds.width, bounds.height, bounds.depth)
            minSide / 2.0f
        } else {
            val dim = Pools.vector3Pool.obtain()
            bounds.getDimensions(dim)
            val diagonal = dim.len()
            Pools.vector3Pool.free(dim)
            diagonal * 0.5f
        }
        val sphereCollisionShape = SphereCollisionShape()
        sphereCollisionShape.radius = radius
        sphereCollisionShape.centerX = bounds.centerX
        sphereCollisionShape.centerY = bounds.centerY
        sphereCollisionShape.centerZ = bounds.centerZ

        return sphereCollisionShape
    }

    /**
     * Fits a capsule aligned to the given local axis.
     *
     * `radius` is chosen so the capsule fits within the two extents perpendicular to [axis],
     * and also clamped by half of the axis extent (so the capsule always fits the bounds).
     * `length` is the cylinder length (excluding the two hemispherical caps), matching common physics APIs
     * like ODE4J's `dCreateCapsule(radius, length)`.
     */
    fun fitCapsuleCollisionShape(
        bounds: BoundingBox,
        axis: Axis = Axis.Y
    ): CollisionShape {
        val axisExtent = when (axis) {
            Axis.X -> bounds.width
            Axis.Y -> bounds.height
            Axis.Z -> bounds.depth
        }

        val crossExtentA = when (axis) {
            Axis.X -> bounds.height
            Axis.Y -> bounds.width
            Axis.Z -> bounds.width
        }
        val crossExtentB = when (axis) {
            Axis.X -> bounds.depth
            Axis.Y -> bounds.depth
            Axis.Z -> bounds.height
        }

        val crossRadius = minOf(crossExtentA, crossExtentB) / 2.0f
        val radius = minOf(crossRadius, axisExtent / 2.0f)
        val length = maxOf(0f, axisExtent - 2.0f * radius)

        val capsuleCollisionShape = CapsuleCollisionShape()
        capsuleCollisionShape.axis = axis
        capsuleCollisionShape.radius = radius
        capsuleCollisionShape.length = length
        capsuleCollisionShape.centerX = bounds.centerX
        capsuleCollisionShape.centerY = bounds.centerY
        capsuleCollisionShape.centerZ = bounds.centerZ

        return capsuleCollisionShape
    }

    /**
     * Fits a straight cylinder aligned to the given local axis.
     *
     * `radius` is chosen so the cylinder fits within the two extents perpendicular to [axis].
     * `length` is the full cylinder height along [axis], matching common physics APIs
     * like LibGDX's `CylinderShapeBuilder.build(diameterX, height, diameterZ, ...)`.
     */
    fun fitCylinderCollisionShape(
        bounds: BoundingBox,
        axis: Axis = Axis.Y
    ): CollisionShape {
        val axisExtent = when (axis) {
            Axis.X -> bounds.width
            Axis.Y -> bounds.height
            Axis.Z -> bounds.depth
        }

        val crossExtentA = when (axis) {
            Axis.X -> bounds.height
            Axis.Y -> bounds.width
            Axis.Z -> bounds.width
        }
        val crossExtentB = when (axis) {
            Axis.X -> bounds.depth
            Axis.Y -> bounds.depth
            Axis.Z -> bounds.height
        }

        val radius = minOf(crossExtentA, crossExtentB) * 0.5f
        val length = axisExtent

        val cylinderCollisionShape = CylinderCollisionShape()
        cylinderCollisionShape.axis = axis
        cylinderCollisionShape.radius = radius
        cylinderCollisionShape.length = length
        cylinderCollisionShape.centerX = bounds.centerX
        cylinderCollisionShape.centerY = bounds.centerY
        cylinderCollisionShape.centerZ = bounds.centerZ

        return cylinderCollisionShape
    }
}
