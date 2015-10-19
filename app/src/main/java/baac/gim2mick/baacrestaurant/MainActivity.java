package baac.gim2mick.baacrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private UserTABLE objUserTABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create & Connect Database
        createAndConnected();
    }   //Main Method

    private void createAndConnected() {

        objUserTABLE =new UserTABLE(this);

    }// Create & Connect Database

}   //Main Class

