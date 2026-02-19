package com.mbrlabs.mundus.editor.ui.modules.inspector.components

import com.kotcrab.vis.ui.widget.VisLabel
import com.kotcrab.vis.ui.widget.VisTable
import com.mbrlabs.mundus.commons.scene3d.GameObject
import com.mbrlabs.mundus.commons.scene3d.components.AbstractPhysicsBodyComponent
import com.mbrlabs.mundus.commons.scene3d.components.Component
import com.mbrlabs.mundus.editor.ui.widgets.PhysicsBodyWidget

class PhysicsBodyComponentWidget(physicsBodyComponent: AbstractPhysicsBodyComponent) :
    ComponentWidget<AbstractPhysicsBodyComponent>("Physics Body Component", physicsBodyComponent) {

    private val settingsContainer = VisTable()

    init {
        this.component = physicsBodyComponent
        setupUI()
    }

    private fun setupUI() {
        collapsibleContent.add(VisLabel("Settings")).left().row()
        collapsibleContent.addSeparator().padBottom(5f).row()
        settingsContainer.add(PhysicsBodyWidget(component)).padLeft(10f)
        collapsibleContent.add(settingsContainer).left().row()
    }

    override fun setValues(go: GameObject) {
        val c: AbstractPhysicsBodyComponent? = go.findComponentByType(Component.Type.PHYSICS_BODY)
        if (c != null) {
            component = c
        }
    }

}
