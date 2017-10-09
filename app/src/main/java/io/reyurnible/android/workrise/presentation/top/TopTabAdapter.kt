package io.reyurnible.android.workrise.presentation.top

import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nshmura.recyclertablayout.RecyclerTabLayout
import io.reyurnible.android.workrise.R
import io.reyurnible.android.workrise.domain.model.value.YearMonthDay
import kotlinx.android.synthetic.main.top_item_tab.view.*
import java.util.*

/**
 * Top YearMonthDay Top TabAdapter
 */
class TopTabAdapter(
        viewPager: ViewPager,
        var dates: List<YearMonthDay>
) : RecyclerTabLayout.Adapter<TopTabAdapter.ViewHolder>(viewPager) {
    override fun getItemCount(): Int = dates.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(viewPager.context).inflate(R.layout.top_item_tab, parent, false))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(position, dates[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val weekOfDaysArrays = itemView.context.resources.getStringArray(R.array.top_pager_weeks)

        fun bind(position: Int, date: YearMonthDay) {
            itemView.weekOfDayText.text = weekOfDaysArrays[date.toCalendar().get(Calendar.DAY_OF_WEEK) - 1]
            itemView.monthOfDayText.text = date.day.toString()
            if (position == currentIndicatorPosition) {
                // Current Selected Position
                itemView.monthOfDayText.setBackgroundResource(R.drawable.top_tab_selected_today)
                itemView.monthOfDayText.setTextColor(ContextCompat.getColor(itemView.context, R.color.text_primary_light))
            } else {
                itemView.monthOfDayText.background = null
                itemView.monthOfDayText.setTextColor(ContextCompat.getColor(itemView.context, R.color.text_primary_dark))
            }
        }
    }

}
