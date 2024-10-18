package com.abc.agrios.market;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.abc.agrios.R;

import java.util.List;

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.CropViewHolder> {
    private List<CommodityPrice> cropList;

    public CropAdapter(List<CommodityPrice> cropList) {
        this.cropList = cropList;
    }

    @NonNull
    @Override
    public CropViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crop, parent, false);
        return new CropViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CropViewHolder holder, int position) {
        CommodityPrice crop = cropList.get(position);
        holder.state.setText(crop.getState());
        holder.district.setText(crop.getDistrict());
        holder.market.setText(crop.getMarket());
        holder.commodity.setText(crop.getCommodity());
        holder.variety.setText(crop.getVariety());
        holder.grade.setText(crop.getGrade());
        holder.arrivalDate.setText(crop.getArrivalDate());
        holder.minPrice.setText(crop.getMinPrice());
        holder.maxPrice.setText(crop.getMaxPrice());
        holder.modalPrice.setText(crop.getModalPrice());
    }

    @Override
    public int getItemCount() {
        return cropList.size();
    }

    public static class CropViewHolder extends RecyclerView.ViewHolder {
        TextView state;
        TextView district;
        TextView market;
        TextView commodity;
        TextView variety;
        TextView grade;
        TextView arrivalDate;
        TextView minPrice;
        TextView maxPrice;
        TextView modalPrice;

        public CropViewHolder(@NonNull View itemView) {
            super(itemView);
            state = itemView.findViewById(R.id.state);
            district = itemView.findViewById(R.id.district);
            market = itemView.findViewById(R.id.market);
            commodity = itemView.findViewById(R.id.commodity);
            variety = itemView.findViewById(R.id.variety);
            grade = itemView.findViewById(R.id.grade);
            arrivalDate = itemView.findViewById(R.id.arrivalDate);
            minPrice = itemView.findViewById(R.id.minPrice);
            maxPrice = itemView.findViewById(R.id.maxPrice);
            modalPrice = itemView.findViewById(R.id.modalPrice);
        }
    }
}
