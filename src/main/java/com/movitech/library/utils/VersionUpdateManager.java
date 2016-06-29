package com.movitech.library.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.movitech.library.R;
import org.json.JSONObject;

/**
 * Created by Nick on 2016/6/21.
 * 版本更新管理类
 */
public class VersionUpdateManager {

    private static final String TAG = VersionUpdateManager.class.getSimpleName();

    private String mIs_update;
    private String mVersion_name;
    private String mVersion_desc;
    private String mIs_force;
    private String mAndroid_download_url;

    private Context mContext;

    // 是否忽略该版标记
    public boolean mIs_ignore = false;
    public String mRecord_version_name = "";

    public static int mUpdate_type = 0x1000;

    public VersionUpdateManager(Context context) {
        this.mContext = context;
    }

    private Handler mGetVersionHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String jsonStr = (String) msg.obj;
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                mIs_update = jsonObject.getString("is_update");
                mVersion_name = jsonObject.getString("version_name");
                mVersion_desc = jsonObject.getString("version_desc");
                mIs_force = jsonObject.getString("is_force");
                mAndroid_download_url = jsonObject.getString("android_download_url");
                if (mIs_update != null && "true".equalsIgnoreCase(mIs_update)) {
                    Log.i(TAG, "需要更新");
                    // 显示提示更新对话框
                    if (mUpdate_type == 0x1000) {
                        showNoticeDialog();
                    } else if (mUpdate_type == 0x1001) {
                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("versionIgnore", Activity.MODE_PRIVATE);
                        mIs_ignore = sharedPreferences.getBoolean("is_ignore", false);
                        mRecord_version_name = sharedPreferences.getString("version_name", "");
                        if (!mIs_ignore || !mRecord_version_name.equalsIgnoreCase(mVersion_name)) {
                            showNoticeDialogWithIgnore();
                        }
                    }
                } else {
                    Log.i(TAG, "已是最新版本");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 检测软件是否需要更新
     */
    public void checkUpdate(String path) {
        mUpdate_type = 0x1000;
        requestData(path);
    }

    /**
     * 检测版本更新（包含“忽略改版”功能）
     * @param path
     */
    public void checkUpdateWithIgnore(String path) {
        mUpdate_type = 0x1001;
        requestData(path);
    }

    /**
     * 请求网络数据
     */
    protected void requestData(String path) {
//        if (NetworkUtils.isNetWorkConnected(mContext)) {
//            RequestParams params = new RequestParams();
//            try {
//                String currentVersion = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_META_DATA).versionName;
//                params.put("currentVersion", currentVersion);
//                AsyncHttpRequestUtils.ClientPost(path, params, new AsyncHttpCallBack(mContext) {
//                    @Override
//                    public void onSelfSuccess(String result) {
//                        Message msg = Message.obtain();
//                        msg.obj = result;
//                        mGetVersionHandler.sendMessage(msg);
//                    }
//
//                    @Override
//                    public void onSelfFailed(Throwable throwable) {
//                        ToastUtils.showToast(mContext, mContext.getResources().getString(R.string.request_failed_str), Toast.LENGTH_LONG);
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            ToastUtils.showToast(mContext, mContext.getResources().getString(R.string.request_failed_str), Toast.LENGTH_LONG);
//        }
    }

    /**
     * 弹出提示框
     */
    protected void showNoticeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.version_update_title);
        String message = mContext.getResources().getString(R.string.version_update_message01) + mVersion_name + mContext.getResources().getString(R.string.version_update_message02) + mVersion_desc;
        builder.setMessage(message);
        builder.setPositiveButton(R.string.version_update_btn_sure, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
                // 跳转到下载页面
                if (TextUtils.isEmpty(mAndroid_download_url)) {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.version_update_no_url), Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mAndroid_download_url));
                mContext.startActivity(intent);
            }
        });
        if (mIs_force != null && !"true".equalsIgnoreCase(mIs_force)) {
            builder.setNegativeButton(R.string.version_update_btn_cancel, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 隐藏当前对话框
                    dialog.dismiss();
                }
            });
        }
        builder.create().show();
    }

    /**
     * 弹出提示框（包含“忽略该版”功能）
     */
    protected void showNoticeDialogWithIgnore() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.version_update_title);
        // 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(mContext).inflate(R.layout.version_update_dialog, null);
        // 设置我们自定义的布局文件作为弹出框的Content
        builder.setView(view);
        final TextView textView = (TextView) view.findViewById(R.id.tv_content);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck_ignore);
        String message = mContext.getResources().getString(R.string.version_update_message01) + mVersion_name + mContext.getResources().getString(R.string.version_update_message02) + mVersion_desc;
        textView.setText(message);
        builder.setPositiveButton(R.string.version_update_btn_sure, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
                // 跳转到下载页面
                if (TextUtils.isEmpty(mAndroid_download_url)) {
                    Toast.makeText(mContext, mContext.getResources().getString(R.string.version_update_no_url), Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mAndroid_download_url));
                mContext.startActivity(intent);
            }
        });
        if (mIs_force != null && !"true".equalsIgnoreCase(mIs_force)) {
            builder.setNegativeButton(R.string.version_update_btn_cancel, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences sharedPreferences = mContext.getSharedPreferences("versionIgnore", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (checkBox.isChecked()) {
                        editor.putBoolean("is_ignore", true);
                        editor.putString("version_name", mVersion_name);
                    } else {
                        editor.putBoolean("is_ignore", false);
                    }
                    editor.commit();
                    Log.i(TAG, "数据成功写入SharedPreferences");
                    // 隐藏当前对话框
                    dialog.dismiss();
                }
            });
        }
        builder.create().show();
    }

}
