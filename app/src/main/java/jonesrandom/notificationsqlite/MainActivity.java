package jonesrandom.notificationsqlite;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DatabaseHelper(this);
        helper.dummy();

        List<ModelData> data = helper.getList();

        RecyclerView list = (RecyclerView)findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new Adapter(data, new Adapter.OnListClick() {
            @Override
            public void onClick(ModelData data) {

                String Title = data.getTittle();
                ModelData mData = helper.getDataNotification(Title);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivities(MainActivity.this, mData.getId(), new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT );

                NotificationCompat.Builder notif = new NotificationCompat.Builder(MainActivity.this);
                notif.setSmallIcon(R.mipmap.ic_launcher);
                notif.setContentTitle(mData.getTittle());
                notif.setContentText(mData.getMessage());
                notif.setContentIntent(pIntent);

                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                manager.notify(mData.getId(), notif.build());
            }
        }));
    }
}
