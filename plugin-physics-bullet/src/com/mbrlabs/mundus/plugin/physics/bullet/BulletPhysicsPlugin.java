package com.mbrlabs.mundus.plugin.physics.bullet;

import com.mbrlabs.mundus.commons.physics.PhysicsSystem;
import com.mbrlabs.mundus.physics.bullet.BulletPhysicsSystem;
import com.mbrlabs.mundus.pluginapi.PhysicsSystemExtension;
import org.pf4j.Extension;
import org.pf4j.Plugin;

public class BulletPhysicsPlugin extends Plugin {

    @Extension
    public static class BulletPhysicsSystemExtension implements PhysicsSystemExtension {

        @Override
        public void initPhysicsSystem() {
            BulletPhysicsSystem.init();
        }

        @Override
        public PhysicsSystem getPhysicsSystem() {
            return new BulletPhysicsSystem();
        }
    }
}
