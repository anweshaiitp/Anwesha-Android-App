package info.anwesha2k18.iitp.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import info.anwesha2k18.iitp.R;
import info.anwesha2k18.iitp.data.EventsData;

import java.util.ArrayList;


public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.EventViewHolder> {
    private final ListCardClick mOnClickListener;
    ArrayList<EventsData> dataList = new ArrayList<>();
    String eventHeader[];
    String eventText[];
    String eventRules[];
    String dateTime[];
    String organizers[];
    String contacts[];
    String venue[];
    TypedArray images;
    Context context;

    public EventsRecyclerViewAdapter(Context context, ListCardClick listCardClick, String eventHeader[], String eventText[], String eventRules[], String dateTime[], String venue[], TypedArray img, String organizers[], String contacts[]) {
        this.eventHeader = eventHeader;
        this.eventText = eventText;
        this.eventRules = eventRules;
        this.dateTime = dateTime;
        this.venue = venue;
        mOnClickListener = listCardClick;
        images = img;
        this.organizers = organizers;
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        boolean attachToParent = false;
        View view = layoutInflater.inflate(R.layout.card_view, parent, attachToParent);
        EventViewHolder eventViewHolder = new EventViewHolder(view);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        EventsData eventsData = new EventsData();
        eventsData.setHeader(eventHeader[position]);
        eventsData.setText(eventText[position]);
        eventsData.setRules(eventRules[position]);
        eventsData.setDateTime(dateTime[position]);
        eventsData.setVenue(venue[position]);
        eventsData.setImageId(images.getResourceId(position, -1));
        eventsData.setOrganizers(organizers[position]);
        eventsData.setContacts(contacts[position]);

        dataList.add(eventsData);

        holder.textViewHeader.setText(eventHeader[position]);
//        if (!eventText[position].equals("-1"))
//            holder.textViewData.setText(eventText[position]);
//        else {
        holder.textViewData.setVisibility(View.GONE);
        holder.textViewHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
//        }
        Glide.with(context).clear(holder.imageView);

        Glide.with(context)
                .load(eventsData.getImageId())
                .thumbnail(0.5f)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return eventHeader.length;
    }

    public interface ListCardClick {
        void onListClick(EventsData eventsData, View view) throws ClassNotFoundException;
    }

    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewHeader;
        TextView textViewData;
        ImageView imageView;

        public EventViewHolder(View itemView) {
            super(itemView);
            textViewHeader = (TextView) itemView.findViewById(R.id.card_header);
            textViewData = (TextView) itemView.findViewById(R.id.card_text);
            imageView = (ImageView) itemView.findViewById(R.id.card_cardimage);
            itemView.setOnClickListener(this);
            imageView.setTransitionName("event_image_view_transition");
            textViewHeader.setTransitionName("event_text_header_transition");
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            try {
                mOnClickListener.onListClick(dataList.get(position), v);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
