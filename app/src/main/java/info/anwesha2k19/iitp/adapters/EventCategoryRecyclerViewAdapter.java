package info.anwesha2k19.iitp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import info.anwesha2k19.iitp.R;
import info.anwesha2k19.iitp.data.EventData;

/**
 * Created by mayank on 28/11/17.
 */

public class EventCategoryRecyclerViewAdapter extends RecyclerView.Adapter<EventCategoryRecyclerViewAdapter.ViewHolder> {

    private final CardClick mOnClickListener;
    private final List<EventData> mValues;
    private Context context;

    public EventCategoryRecyclerViewAdapter(List<EventData> eventData, Context context, CardClick click) {
        mValues = eventData;
        this.context = context;
        mOnClickListener = click;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.header.setText(mValues.get(position).name);
//        if (mValues.get(position).tagline == null || mValues.get(position).tagline.equals(" ") || mValues.get(position).tagline.equals("null") || mValues.get(position).tagline.equals("")) {
//        Log.v("muks2", mValues.get(position).name + " " + Boolean.toString(!TextUtils.isEmpty(mValues.get(position).short_desc)) + Boolean.toString(!mValues.get(position).short_desc.equals("null")));
        if (!TextUtils.isEmpty(mValues.get(position).short_desc) && !mValues.get(position).short_desc.equals("null"))
            holder.tvdesc.setText(mValues.get(position).toDisplay_short);
        else if (!TextUtils.isEmpty(mValues.get(position).long_desc) && !mValues.get(position).long_desc.equals("null"))
            holder.tvdesc.setText(mValues.get(position).toDisplay_long);
        else
            holder.tvdesc.setText(context.getString(R.string.tap_for_more_info));
//        } else
//            holder.tvdesc.setText(mValues.get(position).toDisplay_long);

        RequestOptions options = new RequestOptions()
                .error(R.drawable.anwesha_placeholder);

        Glide.with(context)
                .load(holder.mItem.imageURL)
                .apply(options)
                .into(holder.imageView);
        holder.imageView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface CardClick {
        void onListClick(EventData eventData, View view) throws ClassNotFoundException;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public EventData mItem;
        View v;
        TextView header;
        TextView tvdesc;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            v = view;
            header = view.findViewById(R.id.card_header);
            tvdesc = view.findViewById(R.id.card_text);
            imageView = view.findViewById(R.id.card_cardimage);
            imageView.setTransitionName("event_image_view_transition");
            header.setTransitionName("event_text_header_transition");
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            try {
                mOnClickListener.onListClick(mValues.get(position), v);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
