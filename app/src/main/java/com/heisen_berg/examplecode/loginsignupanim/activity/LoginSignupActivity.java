package com.heisen_berg.examplecode.loginsignupanim.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.heisen_berg.examplecode.R;
import com.heisen_berg.examplecode.loginsignupanim.FlexibleFrameLayout;

public class LoginSignupActivity extends AppCompatActivity {

    LinearLayout loginView, signupView;

    ObjectAnimator enterLogin, exitLogin, enterSignup, exitSignup, enterLoginBtn, enterSignupBtn;
    boolean isLoginVisible = true;

    static int DURATION = 320;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        loginView = findViewById(R.id.ll_login);
        signupView = findViewById(R.id.ll_signup);

        enterLogin = ObjectAnimator.ofFloat(loginView, "translationX", 0).setDuration(DURATION);
        enterLogin.setInterpolator(new FastOutSlowInInterpolator());
        exitLogin = ObjectAnimator.ofFloat(loginView, "translationX", -720).setDuration(DURATION);
        exitLogin.setInterpolator(new FastOutSlowInInterpolator());

        enterSignup = ObjectAnimator.ofFloat(signupView, "translationX", 720, 0).setDuration(DURATION);
        enterSignup.setInterpolator(new FastOutSlowInInterpolator());
        exitSignup = ObjectAnimator.ofFloat(signupView, "translationX", 720).setDuration(DURATION);
        exitSignup.setInterpolator(new FastOutSlowInInterpolator());

        enterLoginBtn = ObjectAnimator.ofFloat(findViewById(R.id.btn_login), "translationX", -720, 0).setDuration(120);
        enterLoginBtn.setInterpolator(new FastOutSlowInInterpolator());

        enterSignupBtn = ObjectAnimator.ofFloat(findViewById(R.id.btn_signup), "translationX", 720, 0).setDuration(120);
        enterSignupBtn.setInterpolator(new FastOutSlowInInterpolator());

        findViewById(R.id.btn_login).setOnClickListener(view -> switchView());
        findViewById(R.id.btn_signup).setOnClickListener(view -> switchView());
    }

    void switchView() {
        if (isLoginVisible) {
            signupView.setVisibility(View.VISIBLE);
            AnimatorSet set = new AnimatorSet();
            set.play(enterSignup).with(exitLogin);
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    findViewById(R.id.btn_login).setBackgroundColor(Color.parseColor("#1C286C"));
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    findViewById(R.id.btn_login).setBackgroundColor(Color.parseColor("#28C678"));
                    enterLoginBtn.start();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            set.start();
        } else {
            loginView.setVisibility(View.VISIBLE);
            AnimatorSet set = new AnimatorSet();
            set.play(enterLogin).with(exitSignup);
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    findViewById(R.id.btn_signup).setBackgroundColor(Color.parseColor("#28C678"));
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    findViewById(R.id.btn_signup).setBackgroundColor(Color.parseColor("#1C286C"));
                    enterSignupBtn.start();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            set.start();


        }

        isLoginVisible = !isLoginVisible;
    }

}
