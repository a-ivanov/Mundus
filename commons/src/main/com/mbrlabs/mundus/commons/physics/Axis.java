package com.mbrlabs.mundus.commons.physics;

/**
 * Common principal axes for 3D collision shapes.
 *
 * <p>Shared by multiple shape definitions (capsule, cylinder, etc.) so that authoring code,
 * debug renderers, and physics backends can reason about orientation in a uniform way.</p>
 */
public enum Axis {
    X,
    Y,
    Z
}
