package com.melardev.tutorialssystem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtBattery;
    private ImageView imgBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBattery = (TextView) findViewById(R.id.txtBattery);
        imgBattery = (ImageView) findViewById(R.id.imgBatteryInfo);

        //Intent stickyIntent = this.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        Intent stickyIntent = this.registerReceiver(this.batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        updateUI(stickyIntent);

    }

    void updateUI(Intent intent) {
        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
        int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int icon_small = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0);
        boolean present = intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
        String technology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
        int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
        int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);


        txtBattery.setText(
                "Health: " + health + "\n" +
                        "Icon Small:" + icon_small + "\n" +
                        "Level: " + level + "\n" +
                        "Plugged: " + plugged + "\n" +
                        "Present: " + present + "\n" +
                        "Scale: " + scale + "\n" +
                        "Status: " + status + "\n" +
                        "Technology: " + technology + "\n" +
                        "Temperature: " + temperature + "\n" +
                        "Voltage: " + voltage + "\n");

        if (icon_small != 0)
            imgBattery.setImageResource(icon_small);
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };
}
