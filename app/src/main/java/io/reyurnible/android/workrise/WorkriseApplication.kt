package io.reyurnible.android.workrise

import android.app.Application
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator
import toothpick.smoothie.FactoryRegistry
import toothpick.smoothie.MemberInjectorRegistry
import toothpick.smoothie.module.SmoothieApplicationModule

class WorkriseApplication : Application() {
    private lateinit var appScope: Scope

    override fun onCreate() {
        super.onCreate()
        Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
        MemberInjectorRegistryLocator.setRootRegistry(MemberInjectorRegistry())
        FactoryRegistryLocator.setRootRegistry(FactoryRegistry())

        appScope = Toothpick.openScope(this).apply {
            installModules(SmoothieApplicationModule(this@WorkriseApplication))
        }
    }

    override fun onTerminate() {
        Toothpick.closeScope(appScope)
        super.onTerminate()
    }

}
