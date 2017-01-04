package com.example.android.sayhi;

import android.content.Context;
import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        break;

                    case "French":
                        locale = new Locale("fr");
                        break;

                    case "English UK":
                        locale = new Locale("en", "UK");
                        break;

                    case "Chinese":
                        locale = new Locale("zh");
                        break;

                    case "Spanish":
                        locale = new Locale("es");
                        break;

                    case "German":
                        locale = new Locale("de");
                        break;

                    case "Italian":
                        locale = new Locale("it");
                        break;

                    case "Dutch":
                        locale = new Locale("nl");
                        break;

                    case "Korean":
                        locale = new Locale("ko");
                        break;
                    case "Polish":
                        locale = new Locale("pl");
                        break;
                    case "Russian":
                        locale = new Locale("ru");
                        break;
                    case "Arabic":
                        locale = new Locale("ar");
                        break;
                    case "Bulgarian":
                        locale = new Locale("bg");
                        break;
                    case "Catalan":
                        locale = new Locale("ca");
                        break;
                    case "Croatian":
                        locale = new Locale("hr");
                        break;
                    case "Danish":
                        locale = new Locale("da");
                        break;
                    case "Finnish":
                        locale = new Locale("fi");
                        break;
                    case "Greek":
                        locale = new Locale("el");
                        break;
                    case "Hebrew":
                        locale = new Locale("iw");
                        break;
                    case "Hindi":
                        locale = new Locale("hi");
                        break;
                    case "Hungarian":
                        locale = new Locale("hu");
                        break;
                    case "Indonesian":
                        locale = new Locale("in");
                        break;
                    case "Latvian":
                        locale = new Locale("lv");
                        break;
                    case "Norwegian":
                        locale = new Locale("nb");
                        break;
                    case "Lithuanian":
                        locale = new Locale("lt");
                        break;

                    case "Portuguese":
                        locale = new Locale("pt");
                        break;
                    case "Romanian":
                        locale = new Locale("ro");
                        break;
                    case "Serbian":
                        locale = new Locale("sr");
                        break;
                    case "Slovak":
                        locale = new Locale("sk");
                        break;
                    case "Slovenian":
                        locale = new Locale("sl");
                        break;
                    case "Swedish":
                        locale = new Locale("sv");
                        break;
                    case "Tagalog":
                        locale = new Locale("tl");
                        break;
                    case "Thai":
                        locale = new Locale("th");
                        break;
                    case "Turkish":
                        locale = new Locale("tr");
                        break;
                    case "Ukrainian":
                        locale = new Locale("uk");
                        break;

                    case "Vietnamese":
                        locale = new Locale("vi");
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
                TextView text = (TextView) findViewById(R.id.textView);
                string = edit.getText().toString();
                text.setText(string);
                tts.setLanguage(locale);

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

            case R.id.feedback:
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"amrits1408@gmail.com"});
                Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                Email.putExtra(Intent.EXTRA_TEXT, "Dear developer," + "");
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
                break;

            case R.id.about_app:
                context = getApplicationContext();
                mess = "Type it in Editable, select language and get the pronunciation. Make sure your phone has the required language pack in TTS downloaded.\n\n More to follow...";
                duration = Toast.LENGTH_LONG;


                toast = Toast.makeText(context, mess, duration);
                toast.show();
                break;

            case R.id.about_me:
                context = getApplicationContext();
                mess = "Under Construction, but here is a quote to make your day. \n\n \"If the King doesn't lead, how can he expect his subordinates to follow?\"";
                duration = Toast.LENGTH_LONG;
                toast = Toast.makeText(context, mess, duration);
                toast.show();
                break;
        }

        return super.onOptionsItemSelected(item);
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