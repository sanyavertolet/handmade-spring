package com.sanyavertolet.handmadespring.config

interface Config {
    fun <T : Any> getImplClass(interfaceType: Class<T>): Class<out T>
}
