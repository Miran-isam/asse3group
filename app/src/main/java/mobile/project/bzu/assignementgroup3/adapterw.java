package mobile.project.bzu.assignementgroup3;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterw extends RecyclerView.Adapter<adapterw.ViewHolder> {

    LayoutInflater inflater;
    List<String> posts;
    //List<User> users;
    Context context;
    // Post p = new Post();

    public adapterw (Context context, List<String> posts){
        this.inflater=LayoutInflater.from(context);
        this.posts = posts;
        this.context=context;



    }

    @NonNull
    @Override
    public adapterw.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.recycle2,parent,false);
        return new adapterw.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewW3.setText(posts.get(position));
        Log.d("TAGGGGGG", "calculateNow: " +holder.textViewW3 );
    }






    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // TextView userName;
        EditText ed1;
        TextView textViewW3;
        ConstraintLayout tags;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // userName=itemView.findViewById(R.id.userName);
            ed1=itemView.findViewById(R.id.editresult);
            textViewW3=itemView.findViewById(R.id.textView3);
            tags=itemView.findViewById(R.id.c1);


        }
    }
}
