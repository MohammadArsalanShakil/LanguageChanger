package android.example.languagechanger;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button mLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
}