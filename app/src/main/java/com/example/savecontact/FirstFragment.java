package com.example.savecontact;

import static android.app.Activity.RESULT_OK;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.savecontact.databinding.FragmentFirstBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class FirstFragment extends Fragment {

    private Button button_submit;
    private EditText et_name, et_number,et_country;
    private ImageView iv_mic;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    TextToSpeech textToSpeech;
    FloatingActionButton float_button;
    View mainView;

    Boolean SavethisContact = false;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.fragment_first, container, false);


        button_submit = mainView.findViewById(R.id.button_submit);
        et_name = (EditText) mainView.findViewById(R.id.et_name);
        et_number = (EditText) mainView.findViewById(R.id.et_number);
        et_country = (EditText) mainView.findViewById(R.id.et_country);
        iv_mic = (ImageView) mainView.findViewById(R.id.iv_mic);
        float_button = (FloatingActionButton) mainView.findViewById(R.id.float_button);


        et_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    textToSpeech.speak("Please Say 10 digit Mobile Number",TextToSpeech.QUEUE_FLUSH,null);
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, 1);
                }
                catch (Exception e) {
                    Toast.makeText(getActivity(), " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });

        et_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // textToSpeech.speak("Please Say Name of the person to Save Number",TextToSpeech.QUEUE_FLUSH,null);
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, 2);
                }
                catch (Exception e) {
                    Toast
                            .makeText(getActivity(), " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });

        et_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, 4);
                }
                catch (Exception e) {
                    Toast
                            .makeText(getActivity(), " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }


            }
        });

        float_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),GuidelinesActivity.class);
                getActivity().startActivity(intent);
            }
        });

        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if (i == TextToSpeech.SUCCESS) {
                    Log.e("TTS", "Initilization Success!");
                    int result = textToSpeech.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    } else {
                        String anyThing = " ";
                      //  speakIt(anyThing);
                    }
                    return;
                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                String Name = et_name.getEditableText().toString().trim();
//                String Number = et_number.getEditableText().toString().trim();
//
//                System.out.println("Name" + Name + Number);
//
//                String[] names = {Name, "Vishnu", "Mahesh"};
//                String[] numbers = {Number, "3865986365", "5286746529"};
//
//                for(int i=0; i< names.length;i++){
//                    writeContact(Name, Number);
//                }


              //  Uri sound = Uri. parse (ContentResolver. SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/raw/quite_impressed.mp3" ) ;

            }
        });

        iv_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, 3);
                }
                catch (Exception e) {
                    Toast
                            .makeText(getActivity(), " " + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        return mainView;


    }

    private void speakIt(String someThing) {
        Log.e("something: ", someThing);
        textToSpeech.speak(someThing, TextToSpeech.QUEUE_ADD, null);
        Log.e("TTS", "called");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                et_number.setText("");

                String Number = Objects.requireNonNull(result).get(0);
                Number = Number.replaceAll(" ", "");


                et_number.setText(Number);

            }
        }else if(requestCode == 2) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                et_name.setText("");
                et_name.setText(
                        Objects.requireNonNull(result).get(0));
            }
        }else if(requestCode == 4) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);

                if(Objects.requireNonNull(result).get(0).equalsIgnoreCase("India")){
                    et_country.setText("+91");


                }else if(Objects.requireNonNull(result).get(0).equalsIgnoreCase("USA")){
                    et_country.setText("+44");
                }

            }
        }
        else if(requestCode == 3) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                System.out.println("ou"+Objects.requireNonNull(result).get(0));


                if(Objects.requireNonNull(result).get(0).trim().equalsIgnoreCase("save this contact")){


                    String Name = et_name.getEditableText().toString().trim();
                    String Number = et_number.getEditableText().toString().trim();


                    if(Number.equals("")){
                        textToSpeech.speak("Number IS empty",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(getActivity(), "Number IS empty ", Toast.LENGTH_SHORT).show();

                    }else if(Name.equals("")){
                        textToSpeech.speak("Name IS empty",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(getActivity(), "Name IS empty ", Toast.LENGTH_SHORT).show();

                    }else if(Number.length()>10){
                        textToSpeech.speak("Number IS greater than 10 Digit",TextToSpeech.QUEUE_FLUSH,null);
                        Toast.makeText(getActivity(), "Number IS greater than 10 Digit ", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getActivity(), "Number" + Number + "and Name" + Name + "+are you sure want to confirm this ? tap mic and Say Yes", Toast.LENGTH_SHORT).show();
                        textToSpeech.speak("Number" + Number + "and Name" + Name + "+are you sure want to confirm", TextToSpeech.QUEUE_FLUSH, null);
                        SavethisContact = true;
                    }

                }
                else if(Objects.requireNonNull(result).get(0).trim().equalsIgnoreCase("call now")){
                    String Number = et_number.getEditableText().toString().trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+Number));
                    startActivity(intent);
                }else if(Objects.requireNonNull(result).get(0).trim().equalsIgnoreCase("message now")){
                    String Number = et_number.getEditableText().toString().trim();
                    Uri uri = Uri.parse("smsto:"+Number);
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body", "The SMS text");
                    startActivity(intent);
                }else if(Objects.requireNonNull(result).get(0).trim().equalsIgnoreCase("email now")){
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "kjhjhb");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@gmailcom"});
                    Intent mailer = Intent.createChooser(intent, "Choose an Email client :");
                    startActivity(mailer);
                }
                else if(Objects.requireNonNull(result).get(0).trim().equalsIgnoreCase("yes")){

                    if(SavethisContact){
                        String Name = et_name.getEditableText().toString().trim();
                        String Number = et_number.getEditableText().toString().trim();

                       String Finalnumber = et_country.getText().toString().trim()+Number;

                        writeContact(Name,Finalnumber);
                        Toast.makeText(getActivity(), "Number" + Number + "and Name" + Name + "+ Saved Successfully", Toast.LENGTH_SHORT).show();
                        textToSpeech.speak("Number" + Number + "and Name" + Name + "+Saved Successfully", TextToSpeech.QUEUE_FLUSH, null);

                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity(),
                                default_notification_channel_id )
                                .setSmallIcon(R.drawable. ic_launcher_foreground )
                                .setContentTitle( "Contact Saved" )
                                .setContentText( "+Name+"+Name+"+Number"+Number);
                        NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context. NOTIFICATION_SERVICE ) ;
                        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
                            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                                    .setContentType(AudioAttributes. CONTENT_TYPE_SONIFICATION )
                                    .setUsage(AudioAttributes. USAGE_ALARM )
                                    .build() ;
                            int importance = NotificationManager. IMPORTANCE_HIGH ;
                            NotificationChannel notificationChannel = new
                                    NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
                            notificationChannel.enableLights( true ) ;
                            notificationChannel.setLightColor(Color. RED ) ;
                            notificationChannel.enableVibration( true ) ;
                            notificationChannel.setVibrationPattern( new long []{ 100 , 200 , 300 , 400 , 500 , 400 , 300 , 200 , 400 }) ;
                            // notificationChannel.setSound(sound , audioAttributes) ;
                            mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
                            assert mNotificationManager != null;
                            mNotificationManager.createNotificationChannel(notificationChannel) ;
                        }
                        assert mNotificationManager != null;
                        mNotificationManager.notify(( int ) System. currentTimeMillis (), mBuilder.build()) ;


                    }
                }
            }
        }
    }
    
    
    private void writeContact(String displayName, String number) {
        ArrayList contentProviderOperations = new ArrayList();
        //insert raw contact using RawContacts.CONTENT_URI
        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
        //insert contact display name using Data.CONTENT_URI
        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, displayName).build());
        //insert mobile number using Data.CONTENT_URI
        contentProviderOperations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0).withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number).withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());
        try {
            getActivity().getContentResolver().
                    applyBatch(ContactsContract.AUTHORITY, contentProviderOperations);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
        System.out.println("Contact saved");
    }



}