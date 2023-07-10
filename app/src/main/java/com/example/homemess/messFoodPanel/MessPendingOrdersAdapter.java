package com.example.homemess.messFoodPanel;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homemess.R;
import com.example.homemess.ReusableCodeForAll;
import com.example.homemess.SendNotification.APIService;
import com.example.homemess.SendNotification.Client;
import com.example.homemess.SendNotification.Data;
import com.example.homemess.SendNotification.MyResponse;
import com.example.homemess.SendNotification.NotificationSender;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessPendingOrdersAdapter extends RecyclerView.Adapter<MessPendingOrdersAdapter.ViewHolder> {

    private Context context;
    private List<MessPendingOrders1> messPendingOrders1list;
    private APIService apiService;
    String userid, dishid;


    public MessPendingOrdersAdapter(Context context, List<MessPendingOrders1> messPendingOrders1list) {
        this.messPendingOrders1list = messPendingOrders1list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mess_orders, parent, false);
        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);
        return new MessPendingOrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final MessPendingOrders1 messPendingOrders1 = messPendingOrders1list.get(position);
        holder.Address.setText(messPendingOrders1.getAddress());
        holder.grandtotalprice.setText("GrandTotal: ₹ " + messPendingOrders1.getGrandTotalPrice());
        final String random = messPendingOrders1.getRandomUID();
        holder.Vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Mess_order_dishes.class);
                intent.putExtra("RandomUID", random);
                context.startActivity(intent);
            }
        });

        holder.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("MessPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(random).child("Dishes");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            final MessPendingOrders messPendingOrders = snapshot.getValue(MessPendingOrders.class);
                            HashMap<String, String> hashMap = new HashMap<>();
                            String messid = messPendingOrders.getMessId();
                            String dishid = messPendingOrders.getDishId();
                            hashMap.put("MessId", messPendingOrders.getMessId());
                            hashMap.put("DishId", messPendingOrders.getDishId());
                            hashMap.put("DishName", messPendingOrders.getDishName());
                            hashMap.put("DishPrice", messPendingOrders.getPrice());
                            hashMap.put("DishQuantity", messPendingOrders.getDishQuantity());
                            hashMap.put("RandomUID", random);
                            hashMap.put("TotalPrice", messPendingOrders.getTotalPrice());
                            hashMap.put("UserId", messPendingOrders.getUserId());
                            FirebaseDatabase.getInstance().getReference("MessPaymentOrders").child(messid).child(random).child("Dishes").child(dishid).setValue(hashMap);
                        }
                        DatabaseReference data = FirebaseDatabase.getInstance().getReference("MessPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(random).child("OtherInformation");
                        data.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                MessPendingOrders1 messPendingOrders1 = dataSnapshot.getValue(MessPendingOrders1.class);
                                HashMap<String, String> hashMap1 = new HashMap<>();
                                hashMap1.put("Address", messPendingOrders1.getAddress());
                                hashMap1.put("GrandTotalPrice", messPendingOrders1.getGrandTotalPrice());
                                hashMap1.put("MobileNumber", messPendingOrders1.getMobileNumber());
                                hashMap1.put("Name", messPendingOrders1.getName());
                                hashMap1.put("Note",messPendingOrders1.getNote());
                                hashMap1.put("RandomUID", random);
                                FirebaseDatabase.getInstance().getReference("MessPaymentOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(random).child("OtherInformation").setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        DatabaseReference Reference = FirebaseDatabase.getInstance().getReference("MessPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(random).child("Dishes");
                                        Reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                    final MessPendingOrders messPendingOrders = snapshot.getValue(MessPendingOrders.class);
                                                    HashMap<String, String> hashMap2 = new HashMap<>();
                                                    userid = messPendingOrders.getUserId();
                                                    dishid = messPendingOrders.getDishId();
                                                    hashMap2.put("MessId", messPendingOrders.getMessId());
                                                    hashMap2.put("DishId", messPendingOrders.getDishId());
                                                    hashMap2.put("DishName", messPendingOrders.getDishName());
                                                    hashMap2.put("DishPrice", messPendingOrders.getPrice());
                                                    hashMap2.put("DishQuantity", messPendingOrders.getDishQuantity());
                                                    hashMap2.put("RandomUID", random);
                                                    hashMap2.put("TotalPrice", messPendingOrders.getTotalPrice());
                                                    hashMap2.put("UserId", messPendingOrders.getUserId());
                                                    FirebaseDatabase.getInstance().getReference("CustomerPaymentOrders").child(userid).child(random).child("Dishes").child(dishid).setValue(hashMap2);
                                                }
                                                DatabaseReference dataa = FirebaseDatabase.getInstance().getReference("MessPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(random).child("OtherInformation");
                                                dataa.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        MessPendingOrders1 messPendingOrders1 = dataSnapshot.getValue(MessPendingOrders1.class);
                                                        HashMap<String, String> hashMap3 = new HashMap<>();
                                                        hashMap3.put("Address", messPendingOrders1.getAddress());
                                                        hashMap3.put("GrandTotalPrice", messPendingOrders1.getGrandTotalPrice());
                                                        hashMap3.put("MobileNumber", messPendingOrders1.getMobileNumber());
                                                        hashMap3.put("Name", messPendingOrders1.getName());
                                                        hashMap3.put("Note",messPendingOrders1.getNote());
                                                        hashMap3.put("RandomUID", random);
                                                        FirebaseDatabase.getInstance().getReference("CustomerPaymentOrders").child(userid).child(random).child("OtherInformation").setValue(hashMap3).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                FirebaseDatabase.getInstance().getReference("CustomerPendingOrders").child(userid).child(random).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                        FirebaseDatabase.getInstance().getReference("CustomerPendingOrders").child(userid).child(random).child("OtherInformation").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                                FirebaseDatabase.getInstance().getReference("MessPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(random).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {

                                                                                        FirebaseDatabase.getInstance().getReference("MessPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(random).child("OtherInformation").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                            @Override
                                                                                            public void onSuccess(Void aVoid) {
                                                                                                FirebaseDatabase.getInstance().getReference().child("Tokens").child(userid).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                                    @Override
                                                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                                        String usertoken = dataSnapshot.getValue(String.class);
                                                                                                        sendNotifications(usertoken, "Order Accepted", "Your Order has been Accepted by the Mess, Now make Payment for Order", "Payment");
                                                                                                        ReusableCodeForAll.ShowAlert(context,"","Wait for the Customer to make Payment");

                                                                                                    }

                                                                                                    @Override
                                                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                                                    }
                                                                                                });
                                                                                            }
                                                                                        });
                                                                                    }
                                                                                });

                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        holder.Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference Reference = FirebaseDatabase.getInstance().getReference("MessPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(random).child("Dishes");
                Reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            final MessPendingOrders messPendingOrders = snapshot.getValue(MessPendingOrders.class);
                            userid = messPendingOrders.getUserId();
                            dishid = messPendingOrders.getDishId();
                        }
                        FirebaseDatabase.getInstance().getReference().child("Tokens").child(userid).child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String usertoken = dataSnapshot.getValue(String.class);
                                sendNotifications(usertoken, "Order Rejected", "Your Order has been Rejected by the Mess due to some Circumstances", "Home");
                                FirebaseDatabase.getInstance().getReference("CustomerPendingOrders").child(userid).child(random).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        FirebaseDatabase.getInstance().getReference("CustomerPendingOrders").child(userid).child(random).child("OtherInformation").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                FirebaseDatabase.getInstance().getReference("MessPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(random).child("Dishes").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        FirebaseDatabase.getInstance().getReference("MessPendingOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(random).child("OtherInformation").removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                FirebaseDatabase.getInstance().getReference("AlreadyOrdered").child(userid).child("isOrdered").setValue("false");
                                                            }
                                                        });

                                                    }
                                                });

                                            }
                                        });

                                    }
                                });

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }

    private void sendNotifications(String usertoken, String title, String message, String order) {

        Data data = new Data(title, message, order);
        NotificationSender sender = new NotificationSender(data, usertoken);
        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.code() == 200) {
                    if (response.body().success != 1) {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return messPendingOrders1list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Address, grandtotalprice;
        Button Vieworder, Accept, Reject;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Address = itemView.findViewById(R.id.AD);
            grandtotalprice = itemView.findViewById(R.id.TP);
            Vieworder = itemView.findViewById(R.id.vieww);
            Accept = itemView.findViewById(R.id.accept);
            Reject = itemView.findViewById(R.id.reject);


        }
    }
}