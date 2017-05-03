package com.gogoing.www.safeground;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gogoing.www.safeground.Utils.PkgUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Splash extends AppCompatActivity {

    private static final int UPDATEVERSION =100 ;
    private static final int REQ_For_result =20 ;
    private BufferedReader bf;
    private Handler  handler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
        switch (msg.what){
            case UPDATEVERSION :
                Alertshow();
                break;
            default:
                break;
        }

        }
    };
    private String des;
    private String urlup;
    private FileOutputStream out;
    private ProgressDialog progressBar;
    private ProgressDialog pdd;



    private void Alertshow() {
        final AlertDialog.Builder alert  =  new AlertDialog.Builder(this);
        alert.setTitle("更新提示");
        alert.setMessage(des);
        alert.setPositiveButton("nima ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressBar = new ProgressDialog(Splash.this);
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setCancelable(false);
                progressBar.setCanceledOnTouchOutside(false);
                progressBar.show();
                Log.e("SS", "onClick: s!!!!!!!!!!!!!!!!!" );
                update(progressBar);
//            dialog.dismiss();

            }
        });
        alert.setNegativeButton("cao", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("SS", "@@@@@@@@@@@@@@@@@@@@@" );

                dialog.dismiss();
                loadMainActivity();

            }
        });
        AlertDialog   dialog = alert.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
//        alert.setCancelable(false);

    }

    private void update(ProgressDialog pd) {
            this.pdd = pd;
        new Thread(new Runnable() {


            @Override
            public void run() {
                try {
                    URL url = new URL(urlup);
                    File  file =  new File(Environment.getExternalStorageDirectory(),"s.apk");

                    HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                    connect.setConnectTimeout(5000);
                    connect.setReadTimeout(5000);
                    connect.connect();
                    int contentLength = connect.getContentLength();
                    pdd.setMax(contentLength);

                    String result = "";
                    int current = 0;
                    if (connect.getResponseCode() == 200) {
                        InputStream in = connect.getInputStream();
                        out = new FileOutputStream(file);
                        byte [] by = new byte[1024];
                        int  leng;
                        while ((leng = in.read(by)) != -1) {
                           out.write(by,0,leng);
                            current += leng;
                            Log.e("hahhs", "run: "+current );
                            pdd.setProgress(current);
                        }
                      runOnUiThread(new Runnable() {
                          @Override
                          public void run() {

                          }
                      });

                        pdd.dismiss();
                        //开始传到安装

                        // <intent-filter>
                        // <action android:name="android.intent.action.VIEW" />
                        // <category android:name="android.intent.category.DEFAULT" />
                        // <data android:scheme="content" />
                        // <data android:scheme="file" />
                        // <data android:mimeType="application/vnd.android.package-archive" />
                        // </intent-filter>

                        Intent   intent   = new   Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.setDataAndType(Uri.parse("file"+file.getAbsolutePath()), "application/vnd.android.package-archive");
                        startActivityForResult(intent, REQ_For_result);

                    } else {
                        //TODO
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_For_result :
                loadMainActivity();
               break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        全屏无标题栏：
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        TextView versionname = (TextView) findViewById(R.id.splash_version_name);
        versionname.setText(getResources().getString(R.string.version_name) + PkgUtils.getVersionName(this));
        checkVersion();


    }

    private void checkVersion() {
//http://127.0.0.1:8080/news/version.json
        final int scode = PkgUtils.getVersioCode(this);
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL("http://10.0.2.2:8080/news/version.json");
                    HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                    connect.setConnectTimeout(5000);
                    connect.setReadTimeout(5000);
                    connect.connect();
                    String result = "";
                    if (connect.getResponseCode() == 200) {
                        InputStream in = connect.getInputStream();
                        bf = new BufferedReader(new InputStreamReader(in));
                        String buffer;
                        while ((buffer = bf.readLine()) != null) {
                            result += buffer;
                        }
                        Log.e("sss", "checkVersion: " + result);

                        try {
                            JSONObject jobjet = new JSONObject(result);
                            int code = jobjet.getInt("version");
                            des = jobjet.getString("des");
                            urlup = jobjet.getString("url");
                            if (code > scode) {
                                //更新
                                Message  msg = new Message();
                                msg.what = UPDATEVERSION;
                                handler.sendMessage(msg);

                            } else {
//                                不更新
                                loadMainActivity();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } else {
                        //TODO
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bf != null) {
                            bf.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    private void loadMainActivity() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
            }
        },1500);

    }


}
