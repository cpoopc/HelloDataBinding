package com.cpoopc.hellodatabinding.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.cpoopc.hellodatabinding.BR;

/**
 * @author cpoopc
 * @date 2016/3/9
 * @time 11:13
 * @description
 */
public class WelcomeViewModel extends BaseObservable {
    public String hello0;
    String hello1;
    String hello2;
    boolean isLoading;
    String buttonText;

    public void onClickButton(View view) {
        android.util.Log.d("welcomeViewModel:", "on button click");
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    @Bindable
    public String getHello1() {
        return hello1 + "---get方法附加后缀";
    }

    public void setHello1(String hello1) {
        this.hello1 = hello1;
        notifyPropertyChanged(BR.hello1);// 需要实现Observable,或者继承BaseObservable,然后notifyPropertyChanged,才能实现单向数据绑定
    }

    /**
     * 没有get前缀也可以
     * 但是无法使用@Bindable注解
     * @Bindable注解需要符合javaBeans规范命名
     * @return
     */
    public String hello2() {
        return hello2;
    }

    public void setHello2(String hello2) {
        this.hello2 = hello2;
    }

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR.loading);
    }

}
