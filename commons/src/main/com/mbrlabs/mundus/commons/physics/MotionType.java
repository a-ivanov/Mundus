package com.mbrlabs.mundus.commons.physics;

public enum MotionType {
    /** Pass-through. Overlap detection only. No physical impact. */
    SENSOR,
    /** Solid wall. Immovable. Blocks dynamic objects. */
    STATIC,
    /** Unstoppable. Moves via code. Pushes others. */
    KINEMATIC,
    /** Simulated. Reacts to gravity and collisions. */
    DYNAMIC
}
