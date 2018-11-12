package info.anwesha2k18.iitp.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import info.anwesha2k18.iitp.fragments.LogInFragment;
import info.anwesha2k18.iitp.fragments.SignUpFragment;

import info.anwesha2k18.iitp.R;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static info.anwesha2k18.iitp.FlexibleFrameLayout.ORDER_LOGIN_STATE;
import static info.anwesha2k18.iitp.FlexibleFrameLayout.ORDER_SIGN_UP_STATE;

public class LoginActivity extends AppCompatActivity {

    private info.anwesha2k18.iitp.databinding.ActivityLoginBinding binding;
    private boolean isLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        LogInFragment topLoginFragment = new LogInFragment();
        SignUpFragment topSignUpFragment = new SignUpFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_fragment, topLoginFragment)
                .replace(R.id.sign_up_fragment, topSignUpFragment)
                .commit();

        binding.loginFragment.setRotation(-90);

        binding.button.setOnSignUpListener(topSignUpFragment);
        binding.button.setOnLoginListener(topLoginFragment);

        binding.button.setOnButtonSwitched(isLogin -> {
            binding.getRoot()
                    .setBackgroundColor(ContextCompat.getColor(
                            this,
                            isLogin ? R.color.signUpColor : R.color.loginColor));
        });

        binding.loginFragment.setVisibility(INVISIBLE);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        binding.loginFragment.setPivotX(binding.loginFragment.getWidth() / 2);
        binding.loginFragment.setPivotY(binding.loginFragment.getHeight());
        binding.signUpFragment.setPivotX(binding.signUpFragment.getWidth() / 2);
        binding.signUpFragment.setPivotY(binding.signUpFragment.getHeight());
    }

    public void switchFragment(View v) {
        if (isLogin) {
            binding.loginFragment.setVisibility(VISIBLE);
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.loginColor));
            binding.loginFragment.animate().rotation(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    binding.signUpFragment.setVisibility(INVISIBLE);
                    binding.signUpFragment.setRotation(90);
                    binding.wrapper.setDrawOrder(ORDER_LOGIN_STATE);
                }
            });
        } else {
            binding.signUpFragment.setVisibility(VISIBLE);
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.signUpColor));
            binding.signUpFragment.animate().rotation(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    binding.loginFragment.setVisibility(INVISIBLE);
                    binding.loginFragment.setRotation(-90);
                    binding.wrapper.setDrawOrder(ORDER_SIGN_UP_STATE);
                }
            });
        }

        isLogin = !isLogin;
        binding.button.startAnimation();
    }

}
