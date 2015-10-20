package baac.gim2mick.baacrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by gim2mick on 19/10/2015.
 */
public class OrderTABLE {

    //Explicit
    private gim2mickOpenHelper objGim2mickOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String ORDER_TABLE ="orderTABLE";
    public static final String COLUMN_ID_ORDER ="_id";
    public static final String COLUMN_OFFICER ="Officer";
    public static final String COLUMN_DESK ="Desk";
    public static final String COLUMN_FOOD ="Food";
    public static final String COLUMN_ITEM ="Item";

    public OrderTABLE(Context context) {

        objGim2mickOpenHelper = new gim2mickOpenHelper(context);
        writeSqLiteDatabase = objGim2mickOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objGim2mickOpenHelper.getReadableDatabase();

    }//Constructor

    public long addOrder(String strOfficer, String strDesk, String strFood, String strItem) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_OFFICER, strOfficer);
        objContentValues.put(COLUMN_DESK, strDesk);
        objContentValues.put(COLUMN_FOOD, strFood);
        objContentValues.put(COLUMN_ITEM, strItem);

        return readSqLiteDatabase.insert(ORDER_TABLE, null, objContentValues);

    }// addNewOrder

}//Main Class
