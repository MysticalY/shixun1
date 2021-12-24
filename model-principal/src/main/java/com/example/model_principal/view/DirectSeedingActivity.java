package com.example.model_principal.view;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.example.library_base.view.BaseActivity;
import com.example.model_principal.R;
import com.example.model_principal.adapter.BrainAdapter;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/22
 * Time:19:49
 * author:YangHaoYang
 * Package com.example.model_principal.view
 */
public class DirectSeedingActivity extends BaseActivity {
//    String flvUrl = "http://3891.liveplay.myqcloud.com/live/3891_user_cbf3f104_8e14.m3u8";
    String flvUrl = "https://hsplay-360.v.btime.com/live_btime/btv_sn_20170706_s1/index.m3u8?time=1640223876&sign=32a27b876d111e4b94fafb5a31b5cc5b";
    String rtmpURL = "rtmp://3891.livepush.myqcloud.com/live/3891_user_cbf3f104_8e14?bizid=3891&txSecret=cb5570634bc667516cf283383ed3d15b&txTime=61C3E3DE"; //此处填写您的 rtmp 推流地址
    private TXCloudVideoView videoView;
    private Toolbar directTb;
    private TXLivePlayer mLivePlayer;
    private android.widget.Button tui;
    private android.widget.Button la;
    private TXCloudVideoView pusherTxCloudView;

    @Override
    public int bindLayout() {
        return R.layout.directseeding;
    }

    @Override
    public void initView() {

        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
        videoView = findViewById(R.id.video_view);
        directTb =  findViewById(R.id.direct_tb);
        setSupportActionBar(directTb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        directTb.setNavigationIcon(R.mipmap.icon_back);
        directTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLivePlayer.pause();
                finish();
            }
        });
        tui = (Button) findViewById(R.id.tui);
        la = (Button) findViewById(R.id.la);
        pusherTxCloudView = (TXCloudVideoView) findViewById(R.id.pusher_tx_cloud_view);
    }

    @Override
    public void initData() {
        la.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建 player 对象
                mLivePlayer = new TXLivePlayer(DirectSeedingActivity.this);
//关键 player 对象与界面 view
                mLivePlayer.setPlayerView(videoView);

                mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_VOD_HLS); //推荐 FLV
            }
        });
        tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TXLivePushConfig mLivePushConfig  = new TXLivePushConfig();
                TXLivePusher mLivePusher = new TXLivePusher(DirectSeedingActivity.this);

// 一般情况下不需要修改 config 的默认配置
                mLivePusher.setConfig(mLivePushConfig);
                mLivePusher.startCameraPreview(pusherTxCloudView);

                int ret = mLivePusher.startPusher(rtmpURL.trim());
                if (ret == -5) {
                    Log.i("TAG", "startRTMPPush: license 校验失败");
                }
//                mLivePusher.stopPusher();
//                mLivePusher.stopCameraPreview(true); //如果已经启动了摄像头预览，请在结束推流时将其关闭。
            }
        });
    }
}
