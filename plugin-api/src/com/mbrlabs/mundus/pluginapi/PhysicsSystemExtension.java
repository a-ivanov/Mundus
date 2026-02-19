package com.mbrlabs.mundus.pluginapi;

import com.mbrlabs.mundus.commons.physics.PhysicsSystem;
import org.pf4j.ExtensionPoint;

public interface PhysicsSystemExtension extends ExtensionPoint {

    PhysicsSystem getPhysicsSystem();
}
