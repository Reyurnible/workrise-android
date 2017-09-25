package io.reyurnible.android.workrise.infrastructure.pref

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import io.reyurnible.android.workrise.domain.model.entity.ReportSetting
import kotlin.reflect.KProperty1

/**
 * Shared Preferences Manager
 */
class WorkrisePreferences(context: Context, private val moshi: Moshi) {
    companion object {
        const val PREF_NAME = "workrise.pref"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var reportSetting: ReportSetting?
        set(value) {
            preferences.edit().putJson(WorkrisePreferences::reportSetting, value, moshi).apply()
        }
        get() = preferences.getJson(WorkrisePreferences::reportSetting, moshi)

    private inline fun <reified V> SharedPreferences.Editor.putJson(
            prop: KProperty1<WorkrisePreferences, V>,
            value: V?,
            moshi: Moshi
    ): SharedPreferences.Editor = this.apply {
        value?.let {
            val adapter = moshi.adapter(V::class.java)
            putString(prop.name, adapter.toJson(it))
        } ?: let {
            remove(prop.name)
        }
    }

    private inline fun <reified V> SharedPreferences.getJson(
            prop: KProperty1<WorkrisePreferences, V>,
            moshi: Moshi
    ): V? = getString(prop.name, null)
            ?.takeIf { it.isNotEmpty() }
            ?.let {
                val adapter = moshi.adapter(V::class.java)
                adapter.fromJson(it)
            }
}
