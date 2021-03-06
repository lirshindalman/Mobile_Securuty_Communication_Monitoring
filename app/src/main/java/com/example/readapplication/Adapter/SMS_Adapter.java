package com.example.readapplication.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readapplication.Object.Message;
import com.example.readapplication.R;

import java.util.List;

public class SMS_Adapter extends RecyclerView.Adapter<SMS_Adapter.ViewHolderList> {
    List<Message> smsList;

    public SMS_Adapter(List<Message> smsList) {
        this.smsList = smsList;
    }

    @NonNull
    @Override
    public SMS_Adapter.ViewHolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_item, parent, false);
        final SMS_Adapter.ViewHolderList viewHolder = new SMS_Adapter.ViewHolderList(view);

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SMS_Adapter.ViewHolderList holder, int position) {
        holder.sms_LBL_from.setText(smsList.get(position).getSenderNumber());
        holder.sms_LBL_date.setText(smsList.get(position).getMessageTime());
        holder.sms_LBL_message.setText(smsList.get(position).getMessage());

        if (smsList.get(position).isExist()) {
            holder.sms_LBL_exists.setText(smsList.get(position).getSenderName());
        } else {
            holder.sms_LBL_exists.setText("NOT IN CONTACTS");
        }
    }

    @Override
    public int getItemCount() {
        return smsList.size();
    }

    public class ViewHolderList extends RecyclerView.ViewHolder {
        private TextView sms_LBL_from;
        private TextView sms_LBL_date;
        private TextView sms_LBL_exists;
        private TextView sms_LBL_message;

        public ViewHolderList(@NonNull View itemView) {
            super(itemView);

            sms_LBL_from = itemView.findViewById(R.id.SMS_LBL_From);
            sms_LBL_date = itemView.findViewById(R.id.SMS_LBL_Date);
            sms_LBL_exists = itemView.findViewById(R.id.SMS_LBL_Exists);
            sms_LBL_message = itemView.findViewById(R.id.SMS_LBL_Message);
        }
    }
}
