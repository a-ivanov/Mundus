package com.mbrlabs.mundus.commons.physics;

/**
 * Cylinder collision shape definition.
 *
 * <p><b>Coordinate space:</b> all fields are expressed in the owning rigid body's <b>local</b>
 * space. To build a physics engine shape/geom, apply the rigid body's world transform, then apply
 * the local cylinder offset ({@link #centerX}, {@link #centerY}, {@link #centerZ}) and
 * orientation implied by {@link #axis}.</p>
 *
 * <p><b>Length semantics:</b> {@link #length} stores the <b>total cylinder height</b> along the
 * main {@link #axis}, i.e. the distance from one flat end of the cylinder to the other. This
 * matches common physics APIs that take a full height value, e.g. LibGDX's
 * {@code CylinderShapeBuilder.build(diameterX, height, diameterZ, ...)}. If a library expects a
 * half-height, compute it as {@code halfHeight = length * 0.5f}.</p>
 *
 * <p><b>Typical mappings:</b></p>
 * <ul>
 *   <li>Bullet (LibGDX gdx-bullet): for a Y-aligned cylinder use
 *   {@code new btCylinderShape(new Vector3(radius, length * 0.5f, radius))}.</li>
 *   <li>ODE/ODE4J: if using {@code dCreateCylinder(radius, length)}, pass {@link #radius} and
 *   {@link #length} directly and align the geom to {@link #axis}.</li>
 * </ul>
 */
public class CylinderCollisionShape implements CollisionShape {

    /** Cylinder main axis in local space. */
    public Axis axis = Axis.Y;

    /**
     * Radius of the cylinder (distance from center to side) in local units.
     * Must be {@code >= 0}.
     */
    public float radius;

    /**
     * Total end-to-end cylinder height along {@link #axis}.
     * Must be {@code >= 0}.
     */
    public float length;

    /**
     * Local-space center (offset) of the cylinder relative to the owning rigid body's origin.
     */
    public float centerX;
    public float centerY;
    public float centerZ;
}
