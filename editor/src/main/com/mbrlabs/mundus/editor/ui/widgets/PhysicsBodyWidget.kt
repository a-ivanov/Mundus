package com.mbrlabs.mundus.editor.ui.widgets

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.kotcrab.vis.ui.widget.VisCheckBox
import com.kotcrab.vis.ui.widget.VisLabel
import com.kotcrab.vis.ui.widget.VisSelectBox
import com.mbrlabs.mundus.commons.physics.Axis
import com.mbrlabs.mundus.commons.physics.BoxCollisionShape
import com.mbrlabs.mundus.commons.physics.CapsuleCollisionShape
import com.mbrlabs.mundus.commons.physics.CollisionShape
import com.mbrlabs.mundus.commons.physics.CylinderCollisionShape
import com.mbrlabs.mundus.commons.physics.SphereCollisionShape
import com.mbrlabs.mundus.commons.scene3d.components.AbstractPhysicsBodyComponent
import com.mbrlabs.mundus.editor.utils.PhysicsBodyUtils

class PhysicsBodyWidget(val physicsBodyComponent: AbstractPhysicsBodyComponent) : BaseWidget() {

    private enum class ShapeSelection {
        BOX,
        SPHERE,
        CAPSULE,
        CYLINDER
    }

    private val shapesSelectBox = VisSelectBox<ShapeSelection>()
    private val inscribedCheckBox = VisCheckBox(null)
    private val axisSelectBox = VisSelectBox<Axis>()
    private val shapeSection = getSectionTable()

    init {
        setupWidgets()
    }

    private fun setupWidgets() {
        add(VisLabel("Collision Shape: ")).left()
        add(shapesSelectBox).left().row()
        add(shapeSection).row()
        resetCollisionShapeSection(physicsBodyComponent.collisionShape)

        shapesSelectBox.setItems(
            ShapeSelection.BOX,
            ShapeSelection.SPHERE,
            ShapeSelection.CAPSULE,
            ShapeSelection.CYLINDER
        )
        shapesSelectBox.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                fitAndSetCollisionShape()
            }
        })

        inscribedCheckBox.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                fitAndSetCollisionShape()
            }
        })

        axisSelectBox.setItems(Axis.X, Axis.Y, Axis.Z)
        axisSelectBox.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                if (shapesSelectBox.selected == ShapeSelection.CAPSULE || shapesSelectBox.selected == ShapeSelection.CYLINDER) {
                    fitAndSetCollisionShape()
                }
            }
        })
    }

    private fun fitAndSetCollisionShape() {
        val collisionShape = getCollisionShape()
        physicsBodyComponent.collisionShape = collisionShape
        resetCollisionShapeSection(collisionShape)
    }

    private fun getCollisionShape(): CollisionShape =
        PhysicsBodyUtils.getModelBounds(physicsBodyComponent.gameObject)?.let {
            when (shapesSelectBox.selected) {
                ShapeSelection.BOX -> PhysicsBodyUtils.fitBoxCollisionShape(it)
                ShapeSelection.SPHERE -> PhysicsBodyUtils.fitSphereCollisionShape(it, inscribedCheckBox.isChecked)
                ShapeSelection.CAPSULE -> PhysicsBodyUtils.fitCapsuleCollisionShape(it, axisSelectBox.selected)
                ShapeSelection.CYLINDER -> PhysicsBodyUtils.fitCylinderCollisionShape(it, axisSelectBox.selected)
            }
        } ?: PhysicsBodyUtils.FALLBACK_COLLISION_SHAPE

    private fun resetCollisionShapeSection(collisionShape: CollisionShape) {
        shapeSection.clear()
        when (collisionShape) {
            is BoxCollisionShape -> addBoxShapeSection(collisionShape)
            is SphereCollisionShape -> addSphereShapeSection(collisionShape)
            is CapsuleCollisionShape -> addCapsuleShapeSection(collisionShape)
            is CylinderCollisionShape -> addCylinderShapeSection(collisionShape)
        }
    }

    private fun addBoxShapeSection(boxCollisionShape: BoxCollisionShape) {
        shapeSection.add(VisLabel("Width: ")).left()
        shapeSection.add(getShapePropertyField(boxCollisionShape.width)).left().row()

        shapeSection.add(VisLabel("Height: ")).left()
        shapeSection.add(getShapePropertyField(boxCollisionShape.height)).left().row()

        shapeSection.add(VisLabel("Depth: ")).left()
        shapeSection.add(getShapePropertyField(boxCollisionShape.depth)).left().row()
    }

    private fun addSphereShapeSection(sphereCollisionShape: SphereCollisionShape) {
        addInscribedLabelAndCheckBox()

        shapeSection.add(VisLabel("Radius: ")).left()
        shapeSection.add(getShapePropertyField(sphereCollisionShape.radius)).left().row()
    }

    private fun addCapsuleShapeSection(capsuleCollisionShape: CapsuleCollisionShape) {
        addAxisSection(axisSelectBox, capsuleCollisionShape.axis)
        addRadiusAndLengthFields(capsuleCollisionShape.radius, capsuleCollisionShape.length)
    }

    private fun addCylinderShapeSection(cylinderCollisionShape: CylinderCollisionShape) {
        addAxisSection(axisSelectBox, cylinderCollisionShape.axis)
        addRadiusAndLengthFields(cylinderCollisionShape.radius, cylinderCollisionShape.length)
    }

    private fun addRadiusAndLengthFields(radius: Float, length: Float) {
        shapeSection.add(VisLabel("Radius: ")).left()
        shapeSection.add(getShapePropertyField(radius)).left().row()

        shapeSection.add(VisLabel("Length: ")).left()
        shapeSection.add(getShapePropertyField(length)).left().row()
    }

    private fun addAxisSection(axisSelectBox: VisSelectBox<Axis>, axis: Axis?) {
        axisSelectBox.selected = axis ?: Axis.Y
        shapeSection.add(VisLabel("Axis: ")).left()
        shapeSection.add(axisSelectBox).left().row()
    }

    private fun addInscribedLabelAndCheckBox() {
        shapeSection.add(VisLabel("Inscribed Shape (Inside): ")).left()
        shapeSection.add(inscribedCheckBox).left().row()
    }

    companion object {
        private fun getShapePropertyField(vale: Float): FloatField {
            val propertyField = FloatField(false)
            propertyField.isDisabled = true
            propertyField.text = vale.toString()
            return propertyField
        }
    }
}
