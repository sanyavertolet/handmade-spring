package com.sanyavertolet.handmadespring.config

import org.reflections.Reflections

class KotlinConfig(
    packageName: String,
    interface2Impl: Map<Class<*>, Class<*>>,
) : Config {
    private val scanner = Reflections(packageName)
    private val interfaceToImplementation: Map<Class<*>, Class<*>> = interface2Impl

    override fun <T : Any> getImplClass(interfaceType: Class<T>): Class<out T> {
        val implementation = interfaceToImplementation[interfaceType]
        if (implementation == null) {
            val classes = scanner.getSubTypesOf(interfaceType)
            if (classes.size != 1) {
                throw RuntimeException("Could not determine single interface ${interfaceType.simpleName} implementation")
            }
            return classes.iterator().next()
        }

        // fixMe: should not cast to Class<T>?
        @Suppress("UNCHECKED_CAST")
        return implementation as Class<T>
    }
}
