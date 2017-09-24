package io.reyurnible.android.workrise.common

import io.realm.Case
import io.realm.RealmModel
import io.realm.RealmQuery
import io.realm.Sort
import java.util.*
import kotlin.reflect.KMutableProperty1

/**
 * Realm Extension
 */
// equalTo
fun <T : RealmModel> RealmQuery<T>.equalTo(
        prop: KMutableProperty1<T, String>,
        value: String?
) = equalTo(prop.name, value)

fun <T : RealmModel> RealmQuery<T>.equalTo(
        prop: KMutableProperty1<T, String>,
        value: String?,
        casing: Case
) = equalTo(prop.name, value, casing)

fun <T : RealmModel> RealmQuery<T>.equalTo(
        prop: KMutableProperty1<T, Int>,
        value: Int?
) = equalTo(prop.name, value)

fun <T : RealmModel> RealmQuery<T>.equalTo(
        prop: KMutableProperty1<T, Short>,
        value: Short?
) = equalTo(prop.name, value)

fun <T : RealmModel> RealmQuery<T>.equalTo(
        prop: KMutableProperty1<T, Long>,
        value: Long?
) = equalTo(prop.name, value)

fun <T : RealmModel> RealmQuery<T>.equalTo(
        prop: KMutableProperty1<T, Double>,
        value: Double?
) = equalTo(prop.name, value)

fun <T : RealmModel> RealmQuery<T>.equalTo(
        prop: KMutableProperty1<T, Float>,
        value: Float?
) = equalTo(prop.name, value)

fun <T : RealmModel> RealmQuery<T>.equalTo(
        prop: KMutableProperty1<T, Boolean>,
        value: Boolean?
) = equalTo(prop.name, value)

fun <T : RealmModel> RealmQuery<T>.equalTo(
        prop: KMutableProperty1<T, Date>,
        value: Date?
) = equalTo(prop.name, value)

// greaterThan
fun <T : RealmModel> RealmQuery<T>.greaterThan(
        prop: KMutableProperty1<T, Int>,
        value: Int
) = greaterThan(prop.name, value)

// lessThan
fun <T : RealmModel> RealmQuery<T>.lessThan(
        prop: KMutableProperty1<T, Int>,
        value: Int
) = lessThan(prop.name, value)

// find(デフォルト昇順)
fun <T : RealmModel> RealmQuery<T>.findAllSorted(
        prop: KMutableProperty1<T, *>,
        sortOrder: Sort = Sort.ASCENDING
) = findAllSorted(prop.name, sortOrder)

fun <T : RealmModel> RealmQuery<T>.findAllSortedAsync(
        prop: KMutableProperty1<T, *>,
        sortOrder: Sort = Sort.ASCENDING
) = findAllSortedAsync(prop.name, sortOrder)
