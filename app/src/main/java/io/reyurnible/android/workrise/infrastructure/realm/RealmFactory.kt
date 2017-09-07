package io.reyurnible.android.workrise.infrastructure.realm

import android.app.Application
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Realm Instance Factory
 */
class RealmFactory(application: Application) {
    companion object {
        const val DB_NAME = "workrise.realm"
    }

    private val config: RealmConfiguration

    init {
        Realm.init(application)
        config = RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(1)
                .build()
        Realm.getDefaultInstance()
    }

    fun createInstance(): Single<Realm> =
            Single.create { source ->
                Realm.getInstanceAsync(config, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm) {
                        source.onSuccess(realm)
                    }

                    override fun onError(exception: Throwable?) {
                        super.onError(exception)
                        exception?.let(source::onError)
                    }
                })
            }
}
