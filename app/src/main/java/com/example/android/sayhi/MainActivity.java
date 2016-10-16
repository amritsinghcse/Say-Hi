package com.example.android.sayhi;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Toast toast;
    String choice,string;
    MaterialBetterSpinner spinner;
    TextToSpeech tts;
    Locale locale ;
    Context context;
    CharSequence mess;
    int duration;
    private View myView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.revealiew);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.langs,
                R.layout.spinner_row);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (MaterialBetterSpinner)
                findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        Button button  = (Button) findViewById(R.id.button);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!= 0)

                ;
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                choice = adapterView.getItemAtPosition(i).toString();
                switch(choice){
                    case "Japanese"   :
                        locale  = new Locale("ja");
                        break;

                    case "French"     :
                        locale  = new Locale("fr");
                        break;

                    case "English UK" :
                        locale  = new Locale("en", "UK");
                        break;

                    case "Chinese"    :
                        locale = new Locale("zh");
                        break;

                    case "German"    :
                        locale = new Locale("de");
                        break;

                    case "Spanish"   :
                        locale = new Locale("es");
                        break;

                }





            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                locale  = new Locale("en", "UK");




            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                // previously invisible view


                // get the center for the clipping circle
                int cx = myView.getWidth() / 2;
                int cy = myView.getHeight() / 2;

                // get the final radius for the clipping circle
                int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

                // create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

                //Interpolator for giving effect to animation
                anim.setInterpolator(new AccelerateDecelerateInterpolator());
                // Duration of the animation
                anim.setDuration(500);

                    // make the view visible and start the animation
                myView.setVisibility(View.VISIBLE);
                anim.start();


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
        switch (item.getItemId()){

            case R.id.feedback  :   Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "amrits1408@gmail.com" });
                Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                Email.putExtra(Intent.EXTRA_TEXT, "Dear developer," + "");
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
                break;

            case R.id.about_app  :
               context = getApplicationContext();
                mess = "Type it in Editable, select language and get the pronunciation. Make sure your phone has the required language pack in TTS downloaded.\n\n More to follow...";
                duration = Toast.LENGTH_LONG;


                toast = Toast.makeText(context, mess, duration);
                toast.show();
                break;

            case R.id.about_me  :
               context = getApplicationContext();
                mess = "Under Construction, but here is a quote to make your day. \n\n \"If the King doesn't lead, how can he expect his subordinates to follow?\"";

                duration = Toast.LENGTH_LONG;


                toast = Toast.makeText(context, mess, duration);
                toast.show();
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    private class MoveToBackGround extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            tts.speak(  string, TextToSpeech.QUEUE_FLUSH,null);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
    public void removeRipple(View view){
        //making it gone
        myView.setVisibility(View.GONE);
    }
}

