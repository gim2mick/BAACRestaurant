package baac.gim2mick.baacrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        testAddValue();

    }   //Main Method

    private void createAndConnected() {

        objUserTABLE =new UserTABLE(this);
        objFoodTABLE = new FoodTABLE(this);
        objOrderTABLE = new OrderTABLE(this);

    }// Create & Connect Database

    private void testAddValue() {

        //objUserTABLE.addNewUser("testUser","testPassword","testName");
        //objFoodTABLE.addNewFood("testFood","testSource","testPrice");
        objOrderTABLE.addOrder("testOfficer","testDesk","testFood","testItem");

    } //test Add value

}   //Main Class

