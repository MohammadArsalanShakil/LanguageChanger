package android.example.languagechanger;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate;
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener;
import com.akexorcist.localizationactivity.ui.LocalizationActivity;

public class BaseActivity extends LocalizationActivity implements OnLocaleChangedListener {

    private final LocalizationActivityDelegate mLocalizationDelegate = new LocalizationActivityDelegate(this);

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(mLocalizationDelegate.attachBaseContext(newBase));
    }

    @Override
    public Context getApplicationContext() {
        return mLocalizationDelegate.getApplicationContext(super.getApplicationContext());
    }

    @Override
    public Resources getResources() {
        return mLocalizationDelegate.getResources(super.getResources());
    }

    @Override
    public void onResume() {
        super.onResume();
        mLocalizationDelegate.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onAfterLocaleChanged() {
        this.recreate();
    }

    @Override
    public void onBeforeLocaleChanged() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mLocalizationDelegate.addOnLocaleChangedListener(this);
        mLocalizationDelegate.onCreate();

        super.onCreate(savedInstanceState);
    }
}
