package baac.gim2mick.baacrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.http.client.methods.HttpPost;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private UserTABLE objUserTABLE;
    private FoodTABLE objFoodTABLE;
    private OrderTABLE objOrderTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create & Connect Database
        createAndConnected();

        //test Add Value
        //testAddValue();

        //Delete All SQLite
        deleteAllSQLite();

        //Synchronice JSON to SQLite
        synJSONtoSQLite();

    }   //Main Method

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


            //2. Create JSON String


            //3. Update SQLite

        }
    }   //syn JSON to SQLite

    private void deleteAllSQLite() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("BAAC.db", MODE_PRIVATE, null);
        objSqLiteDatabase.delete("userTable", null, null);
        objSqLiteDatabase.delete("foodTable", null, null);
        objSqLiteDatabase.delete("orderTable", null, null);
    }

    private void testAddValue() {

        objUserTABLE.addNewUser("testUser", "testPassword", "testName");
        objFoodTABLE.addNewFood("testFood", "testSource", "testPrice");
        objOrderTABLE.addOrder("testOfficer", "testDesk", "testFood", "testItem");

    } //test Add value

    private void createAndConnected() {

        objUserTABLE =new UserTABLE(this);
        objFoodTABLE = new FoodTABLE(this);
        objOrderTABLE = new OrderTABLE(this);

    }// Create & Connect Database

}   //Main Class

