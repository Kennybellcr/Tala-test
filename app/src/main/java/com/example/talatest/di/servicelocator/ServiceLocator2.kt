package com.example.talatest.di.servicelocator

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

object ServiceLocator2 {

    init {
        BuildServices()
    }

    private val serviceInstances: HashMap<String, Any> = HashMap()
    private val servicesImplementation: HashMap<String, KClass<*>> = HashMap()

    /**
     * Return instance of desired class or object that implement desired interface.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getService(interfaceClass: KClass<T>): T {
        return getImplementedService(interfaceClass.qualifiedName.toString()) as T
    }
    /**
     * This method allows to bind a custom service implementation to an interface.
     *
     * @param interfaceClass      interface
     * @param implementationClass class which implement interface specified in first param
     */
    fun addService(interfaceClass: KClass<*>, implementationClass: KClass<*>) {
        servicesImplementation[interfaceClass.qualifiedName.toString()] = implementationClass
    }
    /**
     * Return the object specified
     *
     * @param interfaceName Interface name that we can search our reference
     * */
    private fun getImplementedService(interfaceName: String): Any {
        return serviceInstances[interfaceName]
            ?: try {
                val classToImplement: KClass<*> =
                    if (servicesImplementation.containsKey(interfaceName)) {
                        servicesImplementation[interfaceName]!!
                    } else {
                        Class.forName(interfaceName).kotlin
                    }
                val serviceInstance = classToImplement.createInstance()
                serviceInstances[interfaceName] = serviceInstance
                return serviceInstance
            } catch (ex: ClassNotFoundException) {
                throw IllegalArgumentException(
                    "Requested service class was not found: $interfaceName", ex
                )
            } catch (ex: Exception) {
                throw IllegalArgumentException("Cannot initialize requested service: $interfaceName", ex)
            }
    }
}