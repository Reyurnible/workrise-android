package io.reyurnible.android.workrise.infrastructure.realm

import android.app.Application
import android.content.Context
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Realm Instance Factory
 */
class RealmFactory(context: Context) {
    companion object {
        const val DB_NAME = "workrise.realm"
        const val DB_SCHEME_VERSION = 0L
    }

    private val config: RealmConfiguration

    init {
        Realm.init(context)
        config = RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(DB_SCHEME_VERSION)
                .build()
    }

    fun createInstance(): Single<Realm> =
            Single.create { source ->
                Realm.getInstanceAsync(config, object : Realm.Callback() {
                    override fun onSuccess(realm: Realm) {
                        source.onSuccess(realm)
                    }

                    override fun onError(exception: Throwable) {
                        super.onError(exception)
                        exception.let(source::onError)
                    }
                })
            }
}
