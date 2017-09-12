package io.reyurnible.android.workrise.domain.repository.param

sealed class FormEditingParam(open val title: String) {
    data class Text(
            override val title: String,
            val content: String
    ) : FormEditingParam(title)

    data class CheckList(
            override val title: String,
            val content: List<CheckItemEditingParam>
    ) : FormEditingParam(title)
}
