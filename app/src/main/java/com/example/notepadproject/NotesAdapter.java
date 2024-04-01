package com.example.notepadproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notepadproject.Note;
import com.example.notepadproject.R;

import java.util.List;

/**
 * The `MyRecyclerViewAdapter` class is an adapter for populating a RecyclerView with a list of items.
 * It provides custom item views and handles item click events.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> implements View.OnClickListener {

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int adapterPosition;
    private List<Note> mData;

    /**
     * Constructs a new `MyRecyclerViewAdapter` with the provided context and data.
     *
     * @param context The context used for inflating views.
     * @param data    The data to be displayed in the RecyclerView.
     */
    public NotesAdapter(Context context, List<Note> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    /**
     * Adds an item to the adapter's data.
     *
     * @param note The item (deck name) to be added to the data list.
     */
    public void addItem(Note note) {
        this.mData.add(note);
    }

    /**
     * Sets the click listener for items in the RecyclerView.
     *
     * @param itemClickListener The click listener for item click events.
     */
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    /**
     * A ViewHolder for items in the RecyclerView. It holds references to the item's views and handles click events.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNumber;
        TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapterPosition = getAdapterPosition();
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    /**
     * Interface for defining item click listener callbacks.
     */
    public interface ItemClickListener {
        /**
         * Called when an item in the RecyclerView is clicked.
         *
         * @param view     The clicked view.
         * @param position The position of the clicked item.
         */
        void onItemClick(View view, int position);
    }

    @Override
    public void onClick(View view) {
        mClickListener.onItemClick(view, adapterPosition);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String id = String.valueOf(position + 1);
        String title = mData.get(position).getTitle();
        holder.tvNumber.setText(id);
        holder.tvTitle.setText(title);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
