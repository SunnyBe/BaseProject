package com.zistus.basemvi.note.ui.note_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zistus.basemvi.R
import com.zistus.basemvi.note.model.NoteEntity
import com.zistus.core.utils.DateUtils
import kotlinx.android.synthetic.main.item_note.view.*
import kotlinx.android.synthetic.main.layout_note_container.view.*

class NoteListAdapter(private val itemEvent: NoteListAdapter.ItemEvent): ListAdapter<NoteEntity.Note, NoteListAdapter.NoteViewHolder>(DiffUtil) {

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<NoteEntity.Note>() {
            override fun areItemsTheSame(oldItem: NoteEntity.Note, newItem: NoteEntity.Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NoteEntity.Note,
                newItem: NoteEntity.Note
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(note: NoteEntity.Note) {
            itemView.item_note_title.text = note.title
            itemView.item_note_content.text = note.content
            itemView.item_date.text = DateUtils.fetchMonthYear(note.date)
            itemView.note_day.text = DateUtils.fetchDay(note.date)
            itemView.note_time.text = DateUtils.fetchTime(note.date)?.toLowerCase()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            itemEvent.itemClick(getItem(position))
        }
    }

    interface ItemEvent{
        fun itemClick(note: NoteEntity.Note)
    }

}
