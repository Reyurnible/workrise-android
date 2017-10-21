package io.reyurnible.android.workrise.domain.model.entity

data class FormSetting(
        val title: String,
        val type: FormType
) {
    companion object Factory

    enum class FormType {
        Text, CheckList
    }
}
