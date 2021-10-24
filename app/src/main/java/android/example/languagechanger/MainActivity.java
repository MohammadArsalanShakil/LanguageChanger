package android.example.languagechanger;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.akexorcist.localizationactivity.core.LocalizationActivityDelegate;
import com.akexorcist.localizationactivity.core.OnLocaleChangedListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnLocaleChangedListener {

    private final LocalizationActivityDelegate mLocalizationDelegate = new LocalizationActivityDelegate(this);
    private Button mLanguage;

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
    protected void onCreate(Bundle savedInstanceState) {
        mLocalizationDelegate.addOnLocaleChangedListener(this);
        mLocalizationDelegate.onCreate();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLanguage = findViewById(R.id.language_button);
        mLanguage.setOnClickListener(v -> popupMenu());
    }

    private void popupMenu() {
        //Creating the instance of PopupMenu
        android.widget.PopupMenu popup = new android.widget.PopupMenu(this, mLanguage);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_language, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case (R.id.english):
                    setLanguage("en");
                    break;
                case (R.id.arabic):
                    setLanguage("ar");
                    break;
                case (R.id.portuguese):
                    setLanguage("pt");
                    break;
                default:
                    return false;
            }
            return true;
        });
        //showing popup menu
        popup.show();
    }

    public final Locale getCurrentLanguage() {
        return mLocalizationDelegate.getLanguage(this);
    }

    void setLanguage(String language) {
        mLocalizationDelegate.setLanguage(this, language);
    }

    void setLanguage(Locale mLocale) {
        mLocalizationDelegate.setLanguage(this, mLocale);
    }
}