package com.example.poul.bloodykeras.Adapters;


        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.support.v7.widget.RecyclerView;
        import android.text.style.StyleSpan;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.poul.bloodykeras.Model.Donor;
        import com.example.poul.bloodykeras.R;
        import com.example.poul.bloodykeras.Session;

        import java.util.List;

        import rx.Observable;



public class ResultDonorSearch extends RecyclerView.Adapter<ResultDonorSearch.DonorSearchViewHolder> {

    private List<Donor> donors;
    private int rowLayout;
    private Context context;
    private int iddonor;
    private int iduser;


    public ResultDonorSearch(List<Donor> donors, int rowLayout, Context context,int iddonor, int iduser) {
        this.donors =donors ;
        this.rowLayout = rowLayout;
        this.context = context;
        this.iddonor= iddonor;
        this.iduser=iduser;
    }

    @Override
    public ResultDonorSearch.DonorSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);



        return new DonorSearchViewHolder(view);
    }

int row_index;
    @Override
    public void onBindViewHolder(DonorSearchViewHolder holder, final int position) {

        holder.lname.setText( donors.get(position).getLname() );
        holder.fname.setText( donors.get(position).getFname() );
        holder.fathername.setText( "Father's name : " + " " + donors.get(position).getFatherName());
        holder.at.setText("AT: " + " " + donors.get(position).getAT());
        holder.phone.setText("Phone : " + " " + donors.get(position).getPhone());


        //γαμημένος onclick listener της recycle view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               context = v.getContext();

                Intent intent = new Intent(context, Session.class);

                intent.putExtra("userid",iduser);
                intent.putExtra("donorid", iddonor);


                context.startActivity(intent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return donors.size();


    }

    public static class DonorSearchViewHolder extends RecyclerView.ViewHolder {
        TextView lname;
        TextView fname;
        TextView at;
        TextView fathername;
        TextView phone;


        public DonorSearchViewHolder(View v) {
            super(v);

            lname = (TextView) v.findViewById(R.id.ResultDonorLname);
            fname = (TextView) v.findViewById(R.id.ResultDonorFname);
            at = (TextView) v.findViewById(R.id.ResultDonorAT);
            fathername = (TextView) v.findViewById(R.id.ResultDonorFathername);
            phone = (TextView) v.findViewById(R.id.ResultDonorPhone);

        }

        /*@Override
        public void onClick(View v) {

            //Toast.makeText(DonorSearchViewHolder.this,"la",Toast.LENGTH_LONG).show();
        }*/

    }
}
