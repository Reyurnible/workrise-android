package io.reyurnible.android.workrise.presentation.reportsetting

import android.content.Context
import android.support.v7.widget.PopupMenu
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RelativeLayout
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.entity.FormSetting
import kotlinx.android.synthetic.main.report_setting_item_form_setting.view.*

/**
 * Report Setting Form Setting Adapter
 */
class FormSettingAdapter(
        context: Context,
        var listener: OnFormSettingAdapterListener? = null
) : ArrayAdapter<FormSetting>(context, R.layout.report_setting_item_form_setting) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            ((convertView as? FormSettingItemView) ?: FormSettingItemView(context)).apply {
                formSetting = getItem(position)
                onEditMenuClickListener = this@FormSettingAdapter::onEditMenuItemClick
                onDeleteMenuClickListener = this@FormSettingAdapter::onDeleteMenuItemClick
                setOnClickListener {
                    listener?.onItemClicked(getItem(position))
                }
            }

    private fun onEditMenuItemClick(formSetting: FormSetting, view: FormSettingItemView) {
        listener?.onItemEditClicked(formSetting)
    }

    private fun onDeleteMenuItemClick(formSetting: FormSetting, view: FormSettingItemView) {
        listener?.onItemDeleteClicked(formSetting)
    }

    interface OnFormSettingAdapterListener {
        fun onItemClicked(formSetting: FormSetting)
        fun onItemEditClicked(formSetting: FormSetting)
        fun onItemDeleteClicked(formSetting: FormSetting)
    }

    private class FormSettingItemView
    @JvmOverloads constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0
    ) : RelativeLayout(context, attrs, defStyleAttr) {
        var formSetting: FormSetting? = null
            set(value) {
                field = value
                value?.let(this::bind)
            }

        var onEditMenuClickListener: ((FormSetting, FormSettingItemView) -> Unit)? = null
        var onDeleteMenuClickListener: ((FormSetting, FormSettingItemView) -> Unit)? = null

        init {
            inflate(context, R.layout.report_setting_item_form_setting, this)
        }

        override fun onFinishInflate() {
            super.onFinishInflate()
            menuButton.setOnClickListener {
                showPopupMenu(menuButton)
            }
        }

        private fun bind(formSetting: FormSetting) {
            titleText.text = formSetting.title
            typeText.text = formSetting.type.name
        }

        private fun showPopupMenu(anchor: View) = PopupMenu(anchor.context, anchor).apply {
            inflate(R.menu.form_setting_item_menu)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.form_setting_menu_edit -> {
                        formSetting?.let { onEditMenuClickListener?.invoke(it, this@FormSettingItemView) }
                        true
                    }
                    R.id.form_setting_menu_delete -> {
                        formSetting?.let { onDeleteMenuClickListener?.invoke(it, this@FormSettingItemView) }
                        true
                    }
                    else -> false
                }
            }
        }.show()
    }

}
