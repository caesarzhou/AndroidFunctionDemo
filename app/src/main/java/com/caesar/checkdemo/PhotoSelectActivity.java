package com.caesar.checkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.caesar.checkdemo.adapter.ALog;
import com.caesar.checkdemo.model.UploadPicture;
import com.caesar.checkdemo.model.UploadPicture1;
import com.google.gson.Gson;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.util.ArrayList;

import me.shaohui.advancedluban.Luban;

/**
 * 相册图片选择
 * Created by caesar on 2016/11/28.
 * 修订历史:
 */
public class PhotoSelectActivity extends AppCompatActivity implements TakePhoto.TakeResultListener,InvokeListener {

    private static final String TAG = PhotoSelectActivity.class.getName();
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_select_activity);
        tv= (TextView) findViewById(R.id.tv_gallery);
        iv= (ImageView) findViewById(R.id.iv_photo_select);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompressConfig config;
                LubanOptions option=new LubanOptions.Builder()
                        .setGear(Luban.CUSTOM_GEAR)
                        .setMaxHeight(1920)
                        .setMaxWidth(1080)
                        .setMaxSize(3072000)
                        .create();
                config= CompressConfig.ofLuban(option);
                getTakePhoto().onEnableCompress(config,true);
                getTakePhoto().onPickMultiple(9);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }


    /** 上传图片 */
    private void upLoad(ArrayList<TImage> images) {
        String url = "http://www.meiyeplus.cn/index.php?controller=app_circle&action=upload_photo";
        RequestParams params = new RequestParams();
        params.addBodyParameter("hid", "204787");
        for(int i = 0;i <images.size();i++) {
            ALog.i("img"+(i+1));
            params.addBodyParameter("img"+(i+1), new File(images.get(i).getPath()));
        }
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                UploadPicture info = gson.fromJson(result, UploadPicture.class);
                ALog.i("-+-+-+-+-+" + info.getMsg());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ALog.i("请求失败");
            }
        });
    }

    /** 上传图片 */
    private void upLoad1(ArrayList<TImage> images) {
        String url = "http://www.meiyeplus.com/index.php?controller=seller_shop_check&action=photo_upload";
        RequestParams params = new RequestParams();
        params.addBodyParameter("shop_id", "1167");
        params.addBodyParameter("img1",new File(images.get(0).getPath()));
        params.addBodyParameter("type","0");
        params.addBodyParameter("photo_type","1");
        params.addBodyParameter("lat","39.913249");
        params.addBodyParameter("lng","116.403625");
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Gson gson = new Gson();
                ALog.i("----------"+result);
                UploadPicture1 info = gson.fromJson(result, UploadPicture1.class);
                ALog.i("-+-+-+-+-+" + info.getMsg());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ALog.i("请求失败"+e.getMessage());
            }
        });
    }

    @Override
    public void takeSuccess(TResult result) {
        String imgPath = new File(result.getImages().get(0).getPath()).getPath();
        Toast.makeText(PhotoSelectActivity.this, "图片地址" + imgPath, Toast.LENGTH_SHORT).show();
        Glide.with(PhotoSelectActivity.this).load(new File(result.getImages().get(0).getPath())).into(iv);
        upLoad1(result.getImages());
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    /**
     *  获取TakePhoto实例
     * @return
     */
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }
}
