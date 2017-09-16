package io.reyurnible.android.workrise

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class WorkriseApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var androidActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var androidFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = androidActivityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = androidFragmentInjector
}
