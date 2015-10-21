package baac.gim2mick.baacrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public String[] readAllData(int intType) {

        String[] strResult = null;
        Cursor objCursor = readSqLiteDatabase.query(FOOD_TABLE,
                new String[]{COLUMN_ID_FOOD,COLUMN_FOOD,COLUMN_SOURCE,COLUMN_PRICE},
                null,null,null,null,null);
        if (objCursor != null) {

            objCursor.moveToFirst();
            strResult = new String[objCursor.getCount()];

            for (int i = 0; i < objCursor.getCount(); i++) {

                switch (intType) {
                    case 0:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_FOOD));
                        break;
                    case 1:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_SOURCE));
                        break;
                    case 2:
                        strResult[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_PRICE));
                        break;
                    default:
                        break;
                }   //switch

                objCursor.moveToNext();

            }   //for

        }   //if

        objCursor.close();
        return strResult;
    }

    public long addNewFood(String strFood, String strSource, String strPrice) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_FOOD, strFood);
        objContentValues.put(COLUMN_SOURCE, strSource);
        objContentValues.put(COLUMN_PRICE, strPrice);

        return readSqLiteDatabase.insert(FOOD_TABLE, null, objContentValues);

    }// add new Food

}//Main Class
