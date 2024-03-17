package com.sanyavertolet.handmadespring

import com.sanyavertolet.handmadespring.config.Config
import com.sanyavertolet.handmadespring.config.KotlinConfig

class ObjectFactory private constructor() {
    private val config: Config = KotlinConfig("com.sanyavertolet")

    fun <T> createObject(type: Class<T>): T {
        val implClass = if (type.isInterface) config.getImplClass(type) else type

        return implClass.getDeclaredConstructor().newInstance()
    }

    companion object {
        private val instance: ObjectFactory = ObjectFactory()

        fun getInstance() = instance
    }
}
