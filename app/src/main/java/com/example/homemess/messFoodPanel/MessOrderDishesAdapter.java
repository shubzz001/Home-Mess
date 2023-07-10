package com.example.homemess.messFoodPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemess.R;

import java.util.List;

public class MessOrderDishesAdapter extends RecyclerView.Adapter<MessOrderDishesAdapter.ViewHolder> {


    private Context mcontext;
    private List<MessPendingOrders> messPendingOrderslist;

    public MessOrderDishesAdapter(Context context, List<MessPendingOrders> messPendingOrderslist) {
        this.messPendingOrderslist = messPendingOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.mess_order_dishes, parent, false);
        return new MessOrderDishesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final MessPendingOrders messPendingOrders = messPendingOrderslist.get(position);
        holder.dishname.setText(messPendingOrders.getDishName());
        holder.price.setText("Price: ₹ " + messPendingOrders.getPrice());
        holder.quantity.setText("× " + messPendingOrders.getDishQuantity());
        holder.totalprice.setText("Total: ₹ " + messPendingOrders.getTotalPrice());


    }

    @Override
    public int getItemCount() {
        return messPendingOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.DN);
            price = itemView.findViewById(R.id.PR);
            totalprice = itemView.findViewById(R.id.TR);
            quantity = itemView.findViewById(R.id.QY);
        }
    }
}
