package baac.gim2mick.baacrestaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private UserTABLE objUserTABLE;
    private FoodTABLE objFoodTABLE;
    private OrderTABLE objOrderTABLE;

    private EditText userEditText, passwordEditText;
    private String userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        // Create & Connect Database
        createAndConnected();

        //test Add Value
        //testAddValue();

        //Delete All SQLite
        deleteAllSQLite();

        //Sync JSON to SQLite
        synJSONtoSQLite();

    }   //Main Method

    private void bindWidget() {
        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
    }

    public void clickLogin(View view) {
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        if (userString.equals("") || passwordString.equals("") ) {

            //Have Space
            errorDialog("Have Space","Please Fill All Every Blank");

        } else {

            //No Space
            checkUser();

        }
    }

    private void checkUser() {

        try {

            String[] strMyResult = objUserTABLE.searchUser(userString);

            if (passwordString.equals(strMyResult[2])) {

                Toast.makeText(MainActivity.this,"Welcome "+ strMyResult[3],Toast.LENGTH_LONG).show();

                //Intent to Order Activity
                Intent objIntent = new Intent(MainActivity.this,OrderActivity.class);
                objIntent.putExtra("Name", strMyResult[3]);
                startActivity(objIntent);
                finish();//pause main page. if you press undo you must login again

            } else {
                errorDialog("Password False","Please Try Again Password");
            }

        } catch (Exception e) {
            errorDialog("No This User","No "+ userString+" on my Database");
        }

    }

    private void errorDialog(String strTitle, String strMessage) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.danger);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objBuilder.show();
    }

    private void synJSONtoSQLite() {

        //0. Change Policy to connect protocal
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        int intTimes = 0;
        while (intTimes <= 1) {

            InputStream objInputStream = null;
            String strJSON = null;

            //ตำแหน่งที่มี JSON อยู่
            String strUserURL ="http://swiftcodingthai.com/baac/php_get_data_master.php";
            String strFoodURL = "http://swiftcodingthai.com/baac/php_get_food.php";
            HttpPost objHttpPost;

            //1. Create Input Stream
            try {

                HttpClient objHttpClient = new DefaultHttpClient();

                switch (intTimes) {
                    case 0:
                        objHttpPost = new HttpPost(strUserURL);
                        break;
                    case 1:
                        objHttpPost = new HttpPost(strFoodURL);
                        break;
                    default:
                        objHttpPost = new HttpPost(strUserURL);
                        break;
                }   //switch

                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d("baac","InputStream ===> "+ e.toString());
            }

            //2. Create JSON String
            try {

                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream,"UTF-8"));
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;

                while ((strLine = objBufferedReader.readLine()) != null) {

                    objStringBuilder.append(strLine);

                }   //while

                objInputStream.close();
                strJSON = objStringBuilder.toString();

            } catch (Exception e) {
                Log.d("baac", "strJSON ===> " + e.toString());
            }

            //3. Update SQLite
            try {

                JSONArray objJsonArray = new JSONArray(strJSON);

                for (int i = 0; i < objJsonArray.length(); i++) {

                    JSONObject object = objJsonArray.getJSONObject(i);

                    switch (intTimes) {
                        case 0:

                            //For User Table
                            String strUser = object.getString("User");
                            String strPassword = object.getString("Password");
                            String strName = object.getString("Name");
                            objUserTABLE.addNewUser(strUser, strPassword, strName);

                            break;

                        case 1:

                            //For Food Table
                            String strFood = object.getString("Food");
                            String strSource = object.getString("Source");
                            String strPrice = object.getString("Price");
                            objFoodTABLE.addNewFood(strFood, strSource, strPrice);

                            break;

                        default:
                            break;
                    }   //switch

                }

            } catch (Exception e) {
                Log.d("baac", "Update ===> " + e.toString());
            }

            intTimes +=1; // เพื่อให้ดาต้าไปเข้าอีกเทเบิลนึง
        }
    }   //syn JSON to SQLite

    private void deleteAllSQLite() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("BAAC.db", MODE_PRIVATE, null);
        objSqLiteDatabase.delete("userTable", null, null);
        objSqLiteDatabase.delete("foodTable", null, null);
        //objSqLiteDatabase.delete("orderTable", null, null);
    }

    private void testAddValue() {

        objUserTABLE.addNewUser("testUser", "testPassword", "testName");
        objFoodTABLE.addNewFood("testFood", "testSource", "testPrice");
        //objOrderTABLE.addOrder("testOfficer", "testDesk", "testFood", "testItem");

    } //test Add value

    private void createAndConnected() {

        objUserTABLE =new UserTABLE(this);
        objFoodTABLE = new FoodTABLE(this);
        //objOrderTABLE = new OrderTABLE(this);

    }// Create & Connect Database

}   //Main Class

