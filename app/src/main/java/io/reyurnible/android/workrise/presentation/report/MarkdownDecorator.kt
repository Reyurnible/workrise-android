package io.reyurnible.android.workrise.presentation.report

import android.content.Context
import io.reyurnible.android.workrise.domain.model.entity.CheckItem
import io.reyurnible.android.workrise.domain.model.entity.Form
import io.reyurnible.android.workrise.domain.model.entity.Report
import io.reyurnible.android.workrise.extensions.toDisplay

/**
 * Markdown Factory
 */
object MarkdownDecorator {

    fun decorate(report: Report, context: Context): String = report.run {
        "# Report ${id.value.toDisplay(context)}" + "\n" +
                content.map(this@MarkdownDecorator::decorate).joinToString("\n")
    }

    private fun decorate(form: Form): String = form.run {
        "## ${title}" + "\n\n" + when (this) {
            is Form.CheckList -> content.map(this@MarkdownDecorator::decorate).joinToString("\n")
            is Form.Text -> content
        }
    }

    private fun decorate(checkItem: CheckItem): String = checkItem.run {
        "- [${if (checked) "x" else " "}] ${content}"
    }


}
