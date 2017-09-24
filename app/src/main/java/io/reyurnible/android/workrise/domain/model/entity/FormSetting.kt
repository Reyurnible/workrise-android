package io.reyurnible.android.workrise.domain.model.entity

data class FormSetting(
        val title: String,
        val type: FormType
) {
    enum class FormType {
        Text, CheckList
    }
}
