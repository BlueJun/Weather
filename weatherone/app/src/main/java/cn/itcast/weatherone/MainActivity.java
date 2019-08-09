package cn.itcast.weatherone;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText citynameEditText;
    private Button searchWeatherButton;
    private TextView citynametTextView;
    private TextView weahterTextView;
    private TextView tempTextView;
    private TextView h_tempTextView;
    private TextView l_tempTextView;
    String jonString;
    ProgressDialog progressDialog;
    private static final int SET = 1;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET:
                    Util util = new Util();
                    try {
                        List<Map<String, Object>> all = util
                                .getInformation(msg.obj.toString());
                        Iterator<Map<String, Object>> iterator = all.iterator();
                        while (iterator.hasNext()) {
                            Map<String, Object> map = iterator.next();
                            Log.d("天气", map.get("weather").toString());
                            citynametTextView.setText(map.get("cityName")
                                    .toString());
                            weahterTextView.setText(map.get("weather").toString());
                            tempTextView.setText(map.get("temp").toString());
                            h_tempTextView.setText(map.get("l_temp").toString());
                            l_tempTextView.setText(map.get("h_temp").toString());

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                    }
                    break;

            }

        }

    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 生命周期方法
        super.setContentView(R.layout.activity_main); // 设置要使用的布局管理器
        citynameEditText = (EditText) findViewById(R.id.myedit);
        searchWeatherButton = (Button) findViewById(R.id.searchweather);
        citynametTextView = (TextView) findViewById(R.id.city);
        weahterTextView = (TextView) findViewById(R.id.weather);
        tempTextView = (TextView) findViewById(R.id.temp);
        h_tempTextView = (TextView) findViewById(R.id.h_temp);
        l_tempTextView = (TextView) findViewById(R.id.l_temp);



        searchWeatherButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                new Thread(new NewThread()).start();
                Log.d("按键", "Success");


            }
        });
    }

    private class NewThread implements Runnable {

        public void run() {

            String address = "http://apistore.baidu.com/microservice/weather?citypinyin="
                    + citynameEditText.getText().toString();

            HttpDownloader httpDownloader = new HttpDownloader();
            String jonString = httpDownloader.download(address);
            Message msg = MainActivity.this.handler
                    .obtainMessage(MainActivity.SET, jonString);
            MainActivity.this.handler.sendMessage(msg);

        }

    }



}
