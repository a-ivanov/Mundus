package com.mbrlabs.mundus.editor.utils

import com.badlogic.gdx.utils.Array
import com.mbrlabs.mundus.commons.mapper.CustomComponentConverter
import com.mbrlabs.mundus.commons.physics.NoopPhysicsSystem
import com.mbrlabs.mundus.commons.physics.PhysicsSystem
import com.mbrlabs.mundus.pluginapi.ComponentExtension
import com.mbrlabs.mundus.pluginapi.PhysicsSystemExtension
import org.pf4j.PluginManager

/**
 * The util methods for plugin.
 */
object PluginUtils {
    private val TAG = PluginUtils::class.java.simpleName

    /**
     * Gets CustomComponentConverter for all plugins.
     */
    fun getCustomComponentConverters(pluginManager: PluginManager): Array<CustomComponentConverter> {
        val customComponentConverters = Array<CustomComponentConverter>()
        pluginManager.getExtensions(ComponentExtension::class.java).forEach {
            val customComponentConverter = it.converter
            if (customComponentConverter != null) {
                customComponentConverters.add(customComponentConverter)
            }
        }

        return customComponentConverters
    }

    /**
     * Gets first PhysicsSystem found in all plugins or provides fallback.
     */
    fun getPhysicsSystem(pluginManager: PluginManager): PhysicsSystem {
        val extensions = pluginManager.getExtensions(PhysicsSystemExtension::class.java)
        val physicsSystem = if (extensions.isEmpty()) {
            Log.info(TAG, "No physics system found. Fall back to ${NoopPhysicsSystem::class.java.simpleName}.")
            NoopPhysicsSystem.INSTANCE
        } else {
            extensions.first().physicsSystem.also {
                Log.info(TAG, "Found (${extensions.size}) physics systems. Using first available implementation: ${it::class.java.simpleName}.")
            }
        }
        return physicsSystem
    }
}
