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

public class MessOrdertobePrepareViewAdapter extends RecyclerView.Adapter<MessOrdertobePrepareViewAdapter.ViewHolder> {

    private Context mcontext;
    private List<MessWaitingOrders> messWaitingOrderslist;

    public MessOrdertobePrepareViewAdapter(Context context, List<MessWaitingOrders> messWaitingOrderslist) {
        this.messWaitingOrderslist = messWaitingOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.mess_ordertobeprepare_view, parent, false);
        return new MessOrdertobePrepareViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final MessWaitingOrders messWaitingOrders = messWaitingOrderslist.get(position);
        holder.dishname.setText(messWaitingOrders.getDishName());
        holder.price.setText("Price: ₹ " + messWaitingOrders.getDishPrice());
        holder.quantity.setText("× " + messWaitingOrders.getDishQuantity());
        holder.totalprice.setText("Total: ₹ " + messWaitingOrders.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return messWaitingOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.Dname);
            price = itemView.findViewById(R.id.Dprice);
            totalprice = itemView.findViewById(R.id.Tprice);
            quantity = itemView.findViewById(R.id.Dqty);
        }
    }
}
