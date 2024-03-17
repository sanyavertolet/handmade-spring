package com.sanyavertolet.handmadespring.config

interface Config {
    fun <T> getImplClass(interfaceType: Class<T>): Class<out T>
}
