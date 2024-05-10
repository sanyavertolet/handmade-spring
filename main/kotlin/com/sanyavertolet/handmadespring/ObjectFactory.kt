package com.sanyavertolet.handmadespring

import com.sanyavertolet.handmadespring.announcer.Announcer
import com.sanyavertolet.handmadespring.announcer.ConsoleAnnouncer
import com.sanyavertolet.handmadespring.config.Config
import com.sanyavertolet.handmadespring.config.KotlinConfig
import com.sanyavertolet.handmadespring.policeman.AngryPoliceman
import com.sanyavertolet.handmadespring.policeman.Policeman

class ObjectFactory private constructor() {
    @Suppress("ktlint:standard:multiline-expression-wrapping")
    private val config: Config = KotlinConfig(
        "com.sanyavertolet",
        mapOf(
            Policeman::class.java to AngryPoliceman::class.java,
            Announcer::class.java to ConsoleAnnouncer::class.java,
        ),
    )

    fun <T : Any> createObject(type: Class<T>): T {
        val implClass = if (type.isInterface) config.getImplClass(type) else type

        return implClass.getDeclaredConstructor().newInstance()
    }

    companion object {
        private val instance: ObjectFactory = ObjectFactory()

        fun getInstance() = instance
    }
}
