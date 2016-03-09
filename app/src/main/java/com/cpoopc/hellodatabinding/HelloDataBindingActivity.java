package com.cpoopc.hellodatabinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cpoopc.hellodatabinding.databinding.WelcomeBinding;
import com.cpoopc.hellodatabinding.viewmodel.WelcomeViewModel;

public class HelloDataBindingActivity extends AppCompatActivity {

    private WelcomeBinding mViewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 创建DAtaBinding 方法1
//        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_hello_databinding);
        // 创建DAtaBinding 方法2
        mViewDataBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_hello_databinding, null, false);
        setContentView(mViewDataBinding.getRoot());

        final WelcomeViewModel welcome = new WelcomeViewModel();
        welcome.setHello1("hello DataBinding1");
        welcome.setHello2("hello DataBinding2");
        welcome.setButtonText("1.textView.setText() 然后修改model值 \n 2.toggle progress show/hide");
        mViewDataBinding.setWelcome(welcome);
        // 含有id的view会自动在Binding中生成,省去了findViewById
        mViewDataBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewDataBinding.hello1Tv.setText("通过textView.setText()设置文字");
                mViewDataBinding.hello2Tv.setText("通过textView.setText()设置文字");
                android.util.Log.d("mainactivity:", "setText()后,观察model是否随之变化-welcome.hello1:" + welcome.getHello1());// DataBinding目前仅支持单向绑定,不会收到TextView文字改变的影响
                android.util.Log.d("mainactivity:", "setText()后,观察model是否随之变化-welcome.hello2:" + welcome.hello2());// DataBinding目前仅支持单向绑定,不会收到TextView文字改变的影响
                welcome.setHello1("改变Model属性,并notifyPropertyChanged,TextView文字随之变化");// 设置后绑定的TextView文字会随之改变
                welcome.setHello2("改变Model属性,但没有notifyPropertyChanged,TextView文字不会随之变化");// 没有notify,绑定的TextView文字不会随之改变
                welcome.setLoading(!welcome.isLoading());

            }
        });
    }
}
