package com.example.lab.android.nuc.law_analysis.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lab.android.nuc.law_analysis.view.activity.LawyerActivity;
import com.example.lab.android.nuc.law_analysis.R;
import com.example.lab.android.nuc.law_analysis.bean.Lawyer;
import com.example.lab.android.nuc.law_analysis.view.fragment.LawyersFragment;

import java.util.ArrayList;

public class LawyerAdapter extends RecyclerView.Adapter<LawyerAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;

    private ArrayList<Lawyer> mArrayList;

    private LawyersFragment.OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener = null;

    public LawyerAdapter(ArrayList<Lawyer> arrayList,LawyersFragment.OnRecyclerviewItemClickListener onRecyclerviewItemClickListener){
        this.mArrayList = arrayList;
        this.mOnRecyclerviewItemClickListener = onRecyclerviewItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (mContext == null){
           mContext = parent.getContext();
       }
       View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.lawyer_item,parent,false);
       final ViewHolder viewHolder = new ViewHolder( view );
       viewHolder.lawyer_item.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       } );
       view.setOnClickListener( this );
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Lawyer lawyer = mArrayList.get( position );
        if (lawyer.imageUri != null) {
            Glide.with( mContext ).load( lawyer.imageUri).into(((ViewHolder)holder).lawyer_image );
        }
        holder.lawyer_name.setText( lawyer.lawyer_name );
        holder.lawyer_like.setText( lawyer.lawyer_like );
        holder.lawyer_location.setText( lawyer.lawyer_location );
        holder.lawyer_level.setText( lawyer.lawyer_level );
        holder.itemView.setTag( position);
        holder.lawyer_item.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( mContext, LawyerActivity.class );
                intent.putExtra( LawyerActivity.CONTACT_NAME,lawyer.lawyer_name );
                intent.putExtra( LawyerActivity.CONTACT_IAMGE_ID,lawyer.imageUri );
                intent.putExtra( "imageUri",lawyer.imageUri );
                intent.putExtra( "nativeLanguage",lawyer.lawyer_like );
                intent.putExtra( "learnLanguage",lawyer.lawyer_location );
                intent.putExtra(  "languageLevel",lawyer.lawyer_level);
                mContext.startActivity( intent );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    @Override
    public void onClick(View v) {
//        mOnRecyclerviewItemClickListener.onItemClickListaner( v,((int) v.getTag()) );
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lawyer_name,lawyer_level,lawyer_like,lawyer_location;
        ImageView lawyer_image;
        LinearLayout lawyer_item;

        public ViewHolder(View itemView) {
            super(itemView);
            lawyer_name = (TextView) itemView.findViewById( R.id.lawyer_name );
            lawyer_level = (TextView) itemView.findViewById( R.id.lawyer_level );
            lawyer_like = (TextView) itemView.findViewById( R.id.lawyer_like );
            lawyer_location = (TextView) itemView.findViewById( R.id.lawyer_location );
            lawyer_image = (ImageView) itemView.findViewById( R.id.lawyer_image );
            lawyer_item = (LinearLayout) itemView.findViewById( R.id.lawyers_layout );
        }
    }

    public void setFilter(ArrayList<Lawyer> FilteredDataList) {
        mArrayList = FilteredDataList;
        notifyDataSetChanged();
    }
}
