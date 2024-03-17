package com.sanyavertolet.handmadespring.config

import org.reflections.Reflections

class KotlinConfig(packageName: String) : Config {
    private val scanner = Reflections(packageName)

    override fun <T> getImplClass(interfaceType: Class<T>): Class<out T> {
        val classes = scanner.getSubTypesOf(interfaceType)
        if (classes.size != 1) {
            throw RuntimeException("Could not determine single interface ${interfaceType.name} implementation")
        }
        return classes.iterator().next()
    }
}
