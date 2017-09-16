package io.reyurnible.android.workrise.domain.model.value

data class Optional<out T>(val value: T?) {
    companion object {
        inline fun <reified T> empty(): Optional<T> = Optional<T>(null)

        inline fun <reified T> of(value: T): Optional<T> = Optional<T>(value)

        inline fun <reified T> ofNull(value: T?): Optional<T> = Optional<T>(value)
    }
}
