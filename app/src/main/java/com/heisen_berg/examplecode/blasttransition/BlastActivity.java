package com.heisen_berg.examplecode.blasttransition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.heisen_berg.examplecode.R;
import com.heisen_berg.examplecode.app.Config;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlastActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_tagline)
    TextView tvTagline;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.iv_calendar)
    ImageView ivCalendar;
    @BindView(R.id.iv_cup)
    ImageView ivCup;
    @BindView(R.id.iv_book)
    ImageView ivBook;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.iv_gamepad)
    ImageView ivGamepad;
    @BindView(R.id.iv_bag)
    ImageView ivBag;

    @BindView(R.id.fl_lav)
    FrameLayout flLottieView;
    @BindView(R.id.lav)
    LottieAnimationView lottieAnimationView;

    int WIDTH;
    int HEIGHT;

    AnimatorSet titleTaglineSet, loginSet, calendarSet, cupSet, bookSet, phoneSet, gamepadSet, bagSet;
    ObjectAnimator enterTitleAnim, enterTaglineAnim, loginAnim, loginAnimAlpha,
            calendarScaleX, calendarScaleY,
            cupScaleX, cupScaleY,
            bookScaleX, bookScaleY,
            phoneScaleX, phoneScaleY,
            gamepadScaleX, gamepadScaleY,
            bagScaleX, bagScaleY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blast);
        ButterKnife.bind(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        WIDTH = displayMetrics.widthPixels;
        HEIGHT = displayMetrics.heightPixels;

        lottieAnimationView.buildDrawingCache();

        initializeAnimators();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            flLottieView.setVisibility(View.INVISIBLE);
            playAnimation();
        }, 2200);
    }

    void initializeAnimators() {
        enterTitleAnim = ObjectAnimator.ofFloat(tvTitle, Config.TRANSALTION_X, -WIDTH, 0).setDuration(420);
        enterTaglineAnim = ObjectAnimator.ofFloat(tvTagline, Config.TRANSALTION_Y, HEIGHT/8, 0).setDuration(420);

        loginAnim = ObjectAnimator.ofFloat(tvLogin, Config.TRANSALTION_Y, HEIGHT, 0).setDuration(420);
        loginAnimAlpha = ObjectAnimator.ofFloat(tvLogin, Config.ALPHA, 0, 1).setDuration(720);

        calendarScaleX = ObjectAnimator.ofFloat(ivCalendar, Config.SCALE_X, 0, 1).setDuration(320);
        calendarScaleY = ObjectAnimator.ofFloat(ivCalendar, Config.SCALE_Y, 0, 1).setDuration(320);

        cupScaleX = ObjectAnimator.ofFloat(ivCup, Config.SCALE_X, 0, 1).setDuration(320);
        cupScaleY = ObjectAnimator.ofFloat(ivCup, Config.SCALE_Y, 0, 1).setDuration(320);

        bookScaleX = ObjectAnimator.ofFloat(ivBook, Config.SCALE_X, 0, 1).setDuration(320);
        bookScaleY = ObjectAnimator.ofFloat(ivBook, Config.SCALE_Y, 0, 1).setDuration(320);

        phoneScaleX = ObjectAnimator.ofFloat(ivPhone, Config.SCALE_X, 0, 1).setDuration(320);
        phoneScaleY = ObjectAnimator.ofFloat(ivPhone, Config.SCALE_Y, 0, 1).setDuration(320);

        gamepadScaleX = ObjectAnimator.ofFloat(ivGamepad, Config.SCALE_X, 0, 1).setDuration(320);
        gamepadScaleY = ObjectAnimator.ofFloat(ivGamepad, Config.SCALE_Y, 0, 1).setDuration(320);

        bagScaleX = ObjectAnimator.ofFloat(ivBag, Config.SCALE_X, 0, 1).setDuration(320);
        bagScaleY = ObjectAnimator.ofFloat(ivBag, Config.SCALE_Y, 0, 1).setDuration(320);

        OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
        calendarScaleX.setInterpolator(overshootInterpolator);
        calendarScaleY.setInterpolator(overshootInterpolator);
        cupScaleY.setInterpolator(overshootInterpolator);
        cupScaleY.setInterpolator(overshootInterpolator);
        bookScaleX.setInterpolator(overshootInterpolator);
        bookScaleY.setInterpolator(overshootInterpolator);
        phoneScaleX.setInterpolator(overshootInterpolator);
        phoneScaleY.setInterpolator(overshootInterpolator);
        gamepadScaleX.setInterpolator(overshootInterpolator);
        gamepadScaleY.setInterpolator(overshootInterpolator);
        bagScaleX.setInterpolator(overshootInterpolator);
        bagScaleX.setInterpolator(overshootInterpolator);

        FastOutSlowInInterpolator fastOutSlowInInterpolator = new FastOutSlowInInterpolator();
        enterTitleAnim.setInterpolator(fastOutSlowInInterpolator);
        enterTaglineAnim.setInterpolator(fastOutSlowInInterpolator);
        loginAnim.setInterpolator(fastOutSlowInInterpolator);
    }

    void playAnimation() {
        titleTaglineSet = new AnimatorSet();
        titleTaglineSet.play(enterTitleAnim)
                .with(enterTaglineAnim);

        titleTaglineSet.start();

        calendarSet = new AnimatorSet();
        calendarSet.play(calendarScaleX).with(calendarScaleY);
        calendarSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivCalendar.setVisibility(View.VISIBLE);
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(() -> calendarSet.start(), 420);

        cupSet = new AnimatorSet();
        cupSet.play(cupScaleX).with(cupScaleY);
        cupSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivCup.setVisibility(View.VISIBLE);
            }
        });
        Handler handlerCup = new Handler();
        handlerCup.postDelayed(() -> cupSet.start(), 480);

        bookSet = new AnimatorSet();
        bookSet.play(bookScaleX).with(bookScaleY);
        bookSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivBook.setVisibility(View.VISIBLE);
            }
        });
        Handler handlerBook = new Handler();
        handlerBook.postDelayed(() -> bookSet.start(), 540);

        phoneSet = new AnimatorSet();
        phoneSet.play(phoneScaleX).with(phoneScaleY);
        phoneSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivPhone.setVisibility(View.VISIBLE);
            }
        });
        Handler handlerPhone = new Handler();
        handlerPhone.postDelayed(() -> phoneSet.start(), 600);

        bagSet = new AnimatorSet();
        bagSet.play(bagScaleX).with(bagScaleY);
        bagSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivBag.setVisibility(View.VISIBLE);
            }
        });
        Handler handlerBag = new Handler();
        handlerBag.postDelayed(() -> bagSet.start(), 660);

        gamepadSet = new AnimatorSet();
        gamepadSet.play(gamepadScaleX).with(gamepadScaleY);
        gamepadSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ivGamepad.setVisibility(View.VISIBLE);
            }
        });
        Handler handlerGamepad = new Handler();
        handlerGamepad.postDelayed(() -> gamepadSet.start(), 720);

        loginSet = new AnimatorSet();
        loginSet.play(loginAnim).with(loginAnimAlpha);
        Handler handlerLogin = new Handler();
        loginSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                tvLogin.setVisibility(View.VISIBLE);
            }
        });
        handlerLogin.postDelayed(() -> loginSet.start(), 720);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lottieAnimationView.clearAnimation();
    }
}
