package baac.gim2mick.baacrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by gim2mick on 19/10/2015.
 */
public class FoodTABLE {

    //Explicit
    private gim2mickOpenHelper objGim2mickOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String FOOD_TABLE = "foodTABLE";
    public static final String COLUMN_ID_FOOD ="_id";
    public static final String COLUMN_FOOD ="Food";
    public static final String COLUMN_SOURCE ="Source";
    public static final String COLUMN_PRICE ="Price";

    public FoodTABLE(Context context) {

        objGim2mickOpenHelper = new gim2mickOpenHelper(context);
        writeSqLiteDatabase = objGim2mickOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objGim2mickOpenHelper.getReadableDatabase();

    }//Constructor

    public long addNewFood(String strFood, String strSource, String strPrice) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_FOOD, strFood);
        objContentValues.put(COLUMN_SOURCE, strSource);
        objContentValues.put(COLUMN_PRICE, strPrice);

        return readSqLiteDatabase.insert(FOOD_TABLE, null, objContentValues);

    }// addnewUser
}//Main Class
