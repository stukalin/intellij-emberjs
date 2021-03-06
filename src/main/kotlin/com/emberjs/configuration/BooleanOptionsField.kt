package com.emberjs.configuration

import com.emberjs.configuration.utils.ElementUtils
import org.jdom.Element
import javax.swing.JCheckBox

class BooleanOptionsField(
        default: Boolean,
        field: String,
        cmdlineOptionName: String
) : OptionsField<Boolean>(default, field, cmdlineOptionName) {
    override var value: Boolean = default

    override fun writeTo(component: Any) {
        when (component) {
            is JCheckBox -> component.isSelected = value
            is Element -> ElementUtils.writeBool(component, value = this.value, name = this.field)
        }
    }

    override fun readFrom(component: Any) {
        when (component) {
            is JCheckBox -> value = component.isSelected
            is Element -> this.value = ElementUtils.readBool(component, name = this.field) ?: this.default
        }
    }
}