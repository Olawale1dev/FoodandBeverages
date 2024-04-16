package tso.foodandbeverages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<SubjectModel> list;

    private static final int VIEW_TYPE_MENU_ITEM = 0;
    private  int VIEW_TYPE_AD = 1;
    private static final int AD_INTERVAL = 3;



    public SubjectAdapter(Context context, ArrayList<SubjectModel> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return (position % (AD_INTERVAL + 1) == 0) ? VIEW_TYPE_AD : VIEW_TYPE_MENU_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_MENU_ITEM) {
            View view = inflater.inflate(R.layout.item_subject, parent, false);
            return new MenuItemViewHolder(view);
        } else {
            AdView adView = new AdView(context);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId("ca-app-pub-9163926251753325/5055754006"); // Replace with your ad unit ID
            return new AdViewHolder(adView);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (list != null && list.size() > position) {
            SubjectModel model = list.get(position);



            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, pdfViewActivity.class);
                    intent.putExtra("name", model.getSubjectName());
                    intent.putExtra("position", position);
                    context.startActivity(intent); // Use startActivity instead of startActivities
                }
            });
        }
        if (getItemViewType(position) == VIEW_TYPE_MENU_ITEM) {
            SubjectModel model = list.get(position);
            MenuItemViewHolder menuHolder = (MenuItemViewHolder) holder;
            menuHolder.subjectName.setText(model.getSubjectName());
        } else {
            AdViewHolder adHolder = (AdViewHolder) holder;
            AdRequest adRequest = new AdRequest.Builder().build();
            adHolder.adView.loadAd(adRequest);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   /* public class viewHolder extends RecyclerView.ViewHolder {

        TextView subjectName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.moduleName);
        }


    }
    */


    public static class MenuItemViewHolder extends RecyclerView.ViewHolder {
        TextView subjectName;

        public MenuItemViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.moduleName);
        }
    }

    public static class AdViewHolder extends RecyclerView.ViewHolder {
        AdView adView;

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
            adView = (AdView) itemView;
        }
    }


}
