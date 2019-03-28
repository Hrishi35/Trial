package com.example.owner.trial;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
Context context;
List<Pojo> pojoList;
public RecyclerAdapter(Context context,List<Pojo> pojoList){
    this.context=context;
    this.pojoList=pojoList;
}
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    Log.d("testing","testing"+i);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder viewHolder, int i) {
        String urls;
        final Pojo il = pojoList.get(i);
        viewHolder.tq.setText(il.getQuestion());
        viewHolder.tm.setText("["+il.getMarks()+"]");
        urls=il.getImageurl();
        if (urls.equalsIgnoreCase("null")){
            viewHolder.iv.setVisibility(View.GONE);
        }
        else{
            Log.d("as","url"+urls);
            Glide.with(this.context)
                    .load(urls)
                    .into(viewHolder.iv);
        }
        viewHolder.cb.setChecked(il.isSelected());
        viewHolder.cb.setTag(il);
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                Pojo c =(Pojo)cb.getTag();
                c.setSelected(cb.isChecked());
                il.setSelected(cb.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return pojoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tq,tm;
        CheckBox cb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iview1);
            tq=(TextView)itemView.findViewById(R.id.qs1);
            tm=(TextView)itemView.findViewById(R.id.mr1);
            cb = (CheckBox) itemView.findViewById(R.id.ckbox);
        }
    }
    public List<Pojo>getPojoList(){
    return  pojoList;
    }
}
