package com.mbrlabs.mundus.commons.physics;

/**
 * Capsule collision shape definition.
 *
 * <p><b>Coordinate space:</b> all fields are expressed in the owning rigid body's <b>local</b> space.
 * To build a physics engine shape/geom, apply the rigid body's world transform, then apply the
 * local capsule offset ({@link #centerX}, {@link #centerY}, {@link #centerZ}) and orientation
 * implied by {@link #axis}.</p>
 *
 * <p><b>Length semantics:</b> {@link #length} stores the <b>cylinder length</b> (distance between the
 * centers of the two hemispherical caps). This matches common physics APIs (e.g. ODE-style
 * capsules) that take <code>(radius, cylinderLength)</code>. If a library expects the
 * <b>total end-to-end height</b>, compute <code>height = length + 2 * radius</code>.</p>
 */
public class CapsuleCollisionShape implements CollisionShape {

    /**
     * Capsule main axis in the collision-shape's local space.
     * Defaults to Y to preserve legacy scenes that don't have this field serialized.
     */
    public Axis axis = Axis.Y;

    /**
     * Radius of the capsule's hemispherical caps (and the cylinder radius).
     * Must be {@code >= 0}.
     */
    public float radius;

    /**
     * Length of the capsule's cylindrical segment along {@link #axis}, excluding the caps.
     * Must be {@code >= 0}.
     *
     * <p>Total end-to-end capsule height is <code>length + 2 * radius</code>.</p>
     */
    public float length;

    /**
     * Local-space center (offset) of the capsule relative to the owning rigid body's origin.
     */
    public float centerX;
    public float centerY;
    public float centerZ;
}
