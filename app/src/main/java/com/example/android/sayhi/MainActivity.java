package com.example.android.sayhi;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Toast toast;
    String choice, string;
    Spinner spinner;
    TextToSpeech tts;
    Locale locale;
    Context context;
    CharSequence mess;
    int duration;
    private String languagePair;
    static public TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        String[] list = getResources().getStringArray(R.array.langs);
        Arrays.sort(list);
        final ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getApplicationContext(), R.layout.spinner_row, list);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        final RippleView button = (RippleView) findViewById(R.id.button);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != 0)

                    ;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                choice = adapterView.getItemAtPosition(i).toString();
                switch (choice) {
                    case "Japanese":
                        locale = new Locale("ja");
                        languagePair = "en-ja";
                        break;

                    case "French":
                        locale = new Locale("fr");
                        languagePair = "en-fr";
                        break;

                    case "English UK":
                        locale = new Locale("en", "UK");
                        languagePair = "en-en";
                        break;

                    case "Chinese":
                        locale = new Locale("zh");
                        languagePair = "en-zh";
                        break;

                    case "Spanish":
                        locale = new Locale("es");
                        languagePair = "en-es";
                        break;

                    case "German":
                        locale = new Locale("de");
                        languagePair = "en-de";
                        break;

                    case "Italian":
                        locale = new Locale("it");
                        languagePair = "en-it";
                        break;

                    case "Dutch":
                        locale = new Locale("nl");
                        languagePair = "en-nl";
                        break;

                    case "Korean":
                        locale = new Locale("ko");
                        languagePair = "en-ko";
                        break;
                    case "Polish":
                        locale = new Locale("pl");
                        languagePair = "en-pl";
                        break;
                    case "Russian":
                        locale = new Locale("ru");
                        languagePair = "en-ru";
                        break;
                    case "Arabic":
                        locale = new Locale("ar");
                        languagePair = "en-ar";
                        break;
                    case "Bulgarian":
                        locale = new Locale("bg");
                        languagePair = "en-bg";
                        break;
                    case "Catalan":
                        locale = new Locale("ca");
                        languagePair = "en-ca";
                        break;
                    case "Croatian":
                        locale = new Locale("hr");
                        languagePair = "en-hr";
                        break;
                    case "Danish":
                        locale = new Locale("da");
                        languagePair = "en-da";
                        break;
                    case "Finnish":
                        locale = new Locale("fi");
                        languagePair = "en-fi";
                        break;
                    case "Greek":
                        locale = new Locale("el");
                        languagePair = "en-el";
                        break;
                    case "Hebrew":
                        locale = new Locale("iw");
                        languagePair = "en-iw";
                        break;
                    case "Hindi":
                        locale = new Locale("hi");
                        languagePair = "en-hi";
                        break;
                    case "Hungarian":
                        locale = new Locale("hu");
                        languagePair = "en-hu";
                        break;
                    case "Indonesian":
                        locale = new Locale("in");
                        languagePair = "en-in";
                        break;
                    case "Latvian":
                        locale = new Locale("lv");
                        languagePair = "en-lv";
                        break;
                    case "Norwegian":
                        locale = new Locale("nb");
                        languagePair = "en-nb";
                        break;
                    case "Lithuanian":
                        locale = new Locale("lt");
                        languagePair = "en-lt";
                        break;

                    case "Portuguese":
                        locale = new Locale("pt");
                        languagePair = "en-pt";
                        break;
                    case "Romanian":
                        locale = new Locale("ro");
                        languagePair = "en-ro";
                        break;
                    case "Serbian":
                        locale = new Locale("sr");
                        languagePair = "en-sr";
                        break;
                    case "Slovak":
                        locale = new Locale("sk");
                        languagePair = "en-sk";
                        break;
                    case "Slovenian":
                        locale = new Locale("sl");
                        languagePair = "en-sl";
                        break;
                    case "Swedish":
                        locale = new Locale("sv");
                        languagePair = "en-sv";
                        break;
                    case "Tagalog":
                        locale = new Locale("tl");
                        languagePair = "en-tl";
                        break;
                    case "Thai":
                        locale = new Locale("th");
                        languagePair = "en-th";
                        break;
                    case "Turkish":
                        locale = new Locale("tr");
                        languagePair = "en-tr";
                        break;
                    case "Ukrainian":
                        locale = new Locale("uk");
                        languagePair = "en-uk";
                        break;

                    case "Vietnamese":
                        locale = new Locale("vi");
                        languagePair = "en-vi";
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                locale = new Locale("en", "UK");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText edit = (EditText) findViewById(R.id.text);
                string = edit.getText().toString();
                tts.setLanguage(locale);
                Translate(string, languagePair);

                new MoveToBackGround().execute();

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        tts.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.about_app:
                context = getApplicationContext();
                mess = "Type it in Editable, select language and get the pronunciation. Make sure your phone supports the required language.";
                duration = Toast.LENGTH_LONG;
                toast = Toast.makeText(context, mess, duration);
                toast.show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    void Translate(String textToBeTranslated,String languagePair){
        TranslatorBackgroundTask translatorBackgroundTask= new TranslatorBackgroundTask(context);
        translatorBackgroundTask.execute(textToBeTranslated,languagePair); // Returns the translated text as a String
         // Logs the result in Android Monitor
    }

    private class MoveToBackGround extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            tts.speak(string, TextToSpeech.QUEUE_FLUSH, null);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

}