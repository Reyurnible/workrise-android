package io.reyurnible.android.workrise.presentation.common

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Activity Extensions
 */
// Activity
fun Activity.setContentFragment(@IdRes containerId: Int, fragment: android.app.Fragment, tag: String? = null) {
    fragmentManager.beginTransaction().apply {
        tag?.let {
            replace(containerId, fragment, it)
        } ?: kotlin.run {
            replace(containerId, fragment)
        }
    }.commit()
}

fun Activity.addFragment(@IdRes containerId: Int, fragment: android.app.Fragment, tag: String? = null, backStack: Boolean = false) {
    fragmentManager.beginTransaction().apply {
        add(containerId, fragment, tag)
        if (isAddToBackStackAllowed && backStack) {
            addToBackStack(tag)
        }
    }.commit()
}

fun Activity.findFragmentById(@IdRes containerId: Int): android.app.Fragment? =
        fragmentManager.findFragmentById(containerId)

fun Activity.findFragmentByTag(tag: String): android.app.Fragment? =
        fragmentManager.findFragmentByTag(tag)

// Support Activity
fun AppCompatActivity.setContentFragment(@IdRes containerId: Int, fragment: android.support.v4.app.Fragment, tag: String? = null) {
    supportFragmentManager.beginTransaction().apply {
        replace(containerId, fragment, tag)
    }.commit()
}

fun AppCompatActivity.addFragment(
        @IdRes containerId: Int,
        fragment: android.support.v4.app.Fragment, tag: String? = null,
        backStack: Boolean = false
) {
    supportFragmentManager.beginTransaction().apply {
        add(containerId, fragment, tag)
        if (isAddToBackStackAllowed && backStack) {
            addToBackStack(tag)
        }
    }.commit()
}

fun AppCompatActivity.findFragmentById(@IdRes containerId: Int): Fragment? =
        supportFragmentManager.findFragmentById(containerId)

fun AppCompatActivity.findFragmentByTag(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)
