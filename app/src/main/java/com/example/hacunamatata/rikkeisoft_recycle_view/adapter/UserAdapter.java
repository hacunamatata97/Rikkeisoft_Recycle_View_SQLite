package com.example.hacunamatata.rikkeisoft_recycle_view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.hacunamatata.rikkeisoft_recycle_view.R;
import com.example.hacunamatata.rikkeisoft_recycle_view.entity.User;
import java.util.List;

/**
 * Created by hacunamatata on 10/21/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private OnItemClickListener mListener;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvUserName.setText(user.getName());
        holder.tvUserPhone.setText(user.getPhone());
    }

    @Override
    public int getItemCount() {
        return null != userList ? userList.size() : 0;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvUserName;
        private TextView tvUserPhone;
        private OnItemClickListener mClickListener;

        public UserViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            mClickListener = onItemClickListener;
            tvUserName = itemView.findViewById(R.id.tv_name);
            tvUserPhone = itemView.findViewById(R.id.tv_phone);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onItemClickListener(getAdapterPosition());
        }
    }
}