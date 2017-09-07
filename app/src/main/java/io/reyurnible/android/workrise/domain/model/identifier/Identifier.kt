package io.reyurnible.android.workrise.domain.model.identifier

/**
 * Idの属性を表すためのInterface
 */
abstract class Identifier<out T>(open val value: T)
