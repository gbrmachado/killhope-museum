package uk.ac.dur.group1.killhope_museum.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import uk.ac.dur.group1.killhope_museum.KillhopeApplication;
import uk.ac.dur.group1.killhope_museum.R;


public class QRCodeActivity extends ActionBarActivity {

    private ImageButton mButton;

    public static void launchActivity(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context is null");

        Intent i = new Intent(context, QRCodeActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        mButton = (ImageButton) findViewById(R.id.qr_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performScan();
            }
        });
    }

    public void performScan(){
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult == null) {
            finish();
            return;
        }

        String potentialRockID = scanResult.getContents();
        if (potentialRockID == null)
        {
            //the user cancelled.
            finish();
            return;
        }

        KillhopeApplication app = (KillhopeApplication) getApplication();

        if(!app.isValidRockID(potentialRockID))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(String.format("The supplied asset ID: %s was not found in the database.", potentialRockID))
                    .setCancelable(false).setPositiveButton("ok", null)
                    .create().show();
            return; //let the user try again, or back out.
        }

        finish();
        RockDisplayActivity.launchActivity(this, app.getRock(potentialRockID));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
        }
}
