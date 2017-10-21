package io.reyurnible.android.workrise.domain.converter

import android.content.Context
import io.reyurnible.android.workrise.domain.model.entity.CheckItem
import io.reyurnible.android.workrise.domain.model.entity.Form
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.extensions.toDisplay

/**
 * Markdown Factory
 */
object MarkdownConverter {

    fun convert(report: Report, context: Context): String = report.run {
        "# Report ${id.value.toDisplay(context)}" + "\n" +
                content.map(this@MarkdownConverter::convert).joinToString("\n")
    }

    private fun convert(form: Form): String = form.run {
        "## ${title}" + "\n\n" + when (this) {
            is Form.CheckList -> content.map(this@MarkdownConverter::convert).joinToString("\n")
            is Form.Text -> content
        }
    }

    private fun convert(checkItem: CheckItem): String = checkItem.run {
        "- [${if (checked) "x" else " "}] ${content}"
    }


}
