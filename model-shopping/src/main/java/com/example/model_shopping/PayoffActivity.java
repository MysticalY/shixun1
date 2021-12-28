package com.example.model_shopping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.library_base.view.BaseActivity;
import com.example.library_community.ARouterActivityPath;
import com.example.library_community.addressdb.AddressBean;
import com.example.library_community.shappingdb.ShoppingBean;
import com.example.library_community.shappingdb.ShoppingDao;
import com.example.library_community.shappingdb.ShoppingDataBase;
import com.example.library_community.util.LogUtils;
import com.example.library_pay.PayResult;
import com.example.library_pay.util.OrderInfoUtil2_0;
import com.example.model_shopping.adapter.PayAdapter;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * _ ██╗   ██╗   █████╗   ███╗    ██╗   ██████╗
 * __╚██╗ ██╔╝  ██╔══██╗  ████╗   ██║  ██╔════╝
 * ___╚████╔╝   ███████║  ██╔██╗  ██║  ██║  ███╗
 * ____╚██╔╝    ██╔══██║  ██║╚██╗ ██║  ██║   ██║
 * _____██║     ██║  ██║  ██║ ╚████║   ╚██████╔╝
 * _____╚═╝     ╚═╝  ╚═╝  ╚═╝  ╚═══╝    ╚═════╝
 * Date:2021/12/17
 * Time:19:23
 * author:YangHaoYang
 * Package com.example.model_shopping
 */
@Route(path = ARouterActivityPath.Pay.PAY_HOME)
public class PayoffActivity extends BaseActivity {
    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000118609374";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     * pkcs8 格式的商户私钥。
     * <p>
     * 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * RSA2_PRIVATE。
     * <p>
     * 建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDC1ehjyGTDT4tg/o5Qr+MTqpVQl/gl87Tnd/WZPNhZic+w1A7IX6kl4UzAM49VmjPRbOx2ZPDbtXW8IYF6VvvbR+z1WCdSO7hX3l2/7+/Aryw0C+8z+wM34WOBl61ts/Mw9R8ct/hKLuLSmStRhP5waIkBp3NZs7+PoUFFvTMXHh9veW1lkiBZ5t6cYh+fLH8tTGf2mPihwx3bCr44W1D3bX2yJZOB5x1ENNqtVO8etYZeTFrxlSn50GG2mCv296+/3w4fvVBH/MB0Hbwprtn5FIj3D70/CPa2/wa/hHbwPsvc6XfeVQrhIKzdx6zDtO6mg9NXqUBQuUpDRXwg0RQvAgMBAAECggEAG28bRdGVvpqYuZOeXmYlx0MhuzNq0Y07T8xoXKdgn1r0T6XMXxJ5MshCkl2wNRF9cmOv+AvGjmlCprbhEGTnGb1K9FDxWHboQhchhEE4tRz1UG7Nw7f4gAnQhRLibKwF9kLNmrGc2HU5owXGP7hGS3WstPrrFxpkQskl87fsVbi9LZOjBWWsRiymUWspVGfDq1iy/sgUffMQKUASp/VIHiNfuutV7YAaEIHeJF5zYg1fNbIm4sNYU9A8jGfYHKfioXG/7sU4dldyraPvig/rDuLmd8mhRPgCpAlZ/45XMckYlWmiSGhnWsPPBg7hw/xcYS8/pxRZGqpI9UR4TOO2oQKBgQDnSztRJeJJ7jWYC7DBilkaRDljCp851xyCBes2tKxg91b8PdI1VTaY6M+ZAiAE/UX5TFuWk3hRFqDyu6WtDSA9PxJeaIiWMYVnIwhleQlomfcCD0Fh6pZatHVtq1ZgpCK6fEEFPrQKUI0axG+XSz4AFZmQIgChrDL2IpRRC4Ip7QKBgQDXpbfS+2xS0YjCWLmAtwdTEcnwL3gLopqtPt4sMCCQsjQU+fSUMS7PIbHcZ7j4w6XjwsiUvND4pbGyw1eSbgC/JOicnW4ByMrEevTK7XiUzZVxv0zwutWaO699+lC+TBoBN8LBWmwT5WDPMJgd0xC4tFLhfpBQGw3UTntOVTmDCwKBgCAcvtiOZPOUwhYhEWJdne9ap4kLsX2rUwbuYXDP9trjnE0bHDNRaGokcKYyuFVZAL0TD+hBA0Rm2uO4j9EN8HrwZdF7u/L2XyopoAHOozLFpZjAm3hHxKZFRcR7NFvojfXMSI683OxNsLmVqlScGriSRybOwb2wlca8mFEEQwclAoGBAIzq4+bhGXY9Koslg4MTF5w3AakMu/uOdFlFvdVOVdl4OgSDGkpEoXwFXr7SoNMetqbRQFXB1STG0gopEG7Fp4NA0SiWz64m0in210RMTgGfHktIYTTmQnMZEaLXEjBodo3ZLFFBj2Ko/8wkyyKiLEkmvQpRtGhEZZMY/gbANmblAoGBALC31cZmyphKNC5xUFr2fuJ6HZ13GPmmE2LWNx+aKU5nGMisgKomY3/DAx/jKQrF+7Ze8KcWLIV+p0mm+JNR8IUvfb9HM10RD3UhzzmWaBSUk8jN0t0g+5Nn9zSP68ZTUUvdKOkLoNkXMUNqGmEYYiWYBbSVZt8Cq09GE/L2x2oP";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private Toolbar payoffTb;
    private RecyclerView payoffRv;
    private TextView payoffPrice;
    private Button payoffBtn;
    private PayAdapter adapter;
    private float total;
    @Autowired
    public List<ShoppingBean> bean;
    private ShoppingDao shoppingDao;
    private android.widget.RelativeLayout payoffLayout;
    private TextView payoffAddress;
    private TextView payoffName;
    private android.widget.RelativeLayout payRl;
    private TextView payoffZone;

    @Override
    public int bindLayout() {
        return R.layout.payoff_item;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        payoffTb =  findViewById(R.id.payoff_tb);
        payoffRv =  findViewById(R.id.payoff_rv);
        payoffPrice = findViewById(R.id.payoff_price);
        payoffBtn = findViewById(R.id.payoff_btn);
        setSupportActionBar(payoffTb);
        ShoppingDataBase.getInstance(this).getShoppingDao();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        payoffTb.setNavigationIcon(R.mipmap.icon_back);
        payoffTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        shoppingDao = ShoppingDataBase.getInstance(PayoffActivity.this).getShoppingDao();
        payoffRv.addItemDecoration(new Y_DividerItemDecoration(this) {
            @Override
            public Y_Divider getDivider(int itemPosition) {
                Y_Divider divider = null;
                divider = new Y_DividerBuilder().setBottomSideLine(true, Color.parseColor("#E3E4E7"),2,0,0).create();
                return divider;
            }
        });
        payoffLayout = findViewById(R.id.payoff_layout);

        payoffName =  findViewById(R.id.payoff_name);
        payRl =  findViewById(R.id.pay_rl);
        payoffZone = (TextView) findViewById(R.id.payoff_zone);
    }

    @Override
    public void initData() {
        ImmersionBar.with(this)
                .hideBar(BarHide.FLAG_HIDE_BAR)
                .init();
        payoffRv.setLayoutManager(new LinearLayoutManager(this));

        sum();
        //支付
        payoffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
                String s = new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                payV2(s);
            }
        });
        //选择收获人
        payoffLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(PayoffActivity.this,AddressActivity.class);
                startActivity(intent);
            }
        });

    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getAddress(AddressBean addressBean){
        if (addressBean!=null){
            payoffName.setText("姓名"+addressBean.getName());
            payoffZone.setText("地址"+addressBean.getZone());
        }
    }
    public void sum(){
        total = 0f;
        bean = (List<ShoppingBean>) getIntent().getSerializableExtra("pay");
        adapter = new PayAdapter(bean);
            payoffRv.setAdapter(adapter);
            adapter.addFooterView(addView());
        for (int i = 0; i < adapter.getData().size(); i++) {
            total +=adapter.getData().get(i).getNumber()*Float.valueOf(adapter.getData().get(i).getMoney());
        }
        payoffPrice.setText(new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }
    private View addView() {
        return LayoutInflater.from(this).inflate(R.layout.pay_item2,null);
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        List<ShoppingBean> all = shoppingDao.findAll();
                        EventBus.getDefault().postSticky("pay");
                        Toast.makeText(PayoffActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        total = 0;
                        finish();

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayoffActivity.this, "支付失败" + payResult, Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

            }
        }

        ;
    };

    /**
     * 支付宝支付业务示例
     */
    public void payV2(String price) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2, price);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayoffActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
