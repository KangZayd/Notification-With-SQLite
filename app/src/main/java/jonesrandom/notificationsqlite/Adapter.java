package jonesrandom.notificationsqlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    OnListClick listClick;
    List<ModelData> data;

    public Adapter (List<ModelData> data ,OnListClick listClick) {
        this.listClick = listClick;
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row , parent , false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.ListClick(data.get(position), listClick);
        holder.Tittle.setText(data.get(position).getTittle());
        holder.Message.setText(data.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView Tittle, Message;

        public Holder(View itemView) {
            super(itemView);
            Tittle = (TextView) itemView.findViewById(R.id.row_tittle);
            Message = (TextView) itemView.findViewById(R.id.row_message);
        }

        public void ListClick(final ModelData data, final OnListClick click) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.onClick(data);
                }
            });
        }
    }

    public interface OnListClick {
        void onClick(ModelData data);
    }
}
