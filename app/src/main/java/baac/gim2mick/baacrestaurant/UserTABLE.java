package baac.gim2mick.baacrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by gim2mick on 19/10/2015.
 */
public class UserTABLE {

    //Explicit
    private gim2mickOpenHelper objGim2mickOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String USER_TABLE ="userTABLE";
    public static final String COLUMN_ID_USER ="_id";
    public static final String COLUMN_USER ="User";
    public static final String COLUMN_PASSWORD ="Password";
    public static final String COLUMN_NAME ="Name";

    public UserTABLE(Context context) {
        objGim2mickOpenHelper = new gim2mickOpenHelper(context);
        writeSqLiteDatabase = objGim2mickOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objGim2mickOpenHelper.getReadableDatabase();
    }//Constructor

    public long addNewUser(String strUser, String strPassword, String strName) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_USER, strUser);
        objContentValues.put(COLUMN_PASSWORD, strPassword);
        objContentValues.put(COLUMN_NAME, strName);

        return readSqLiteDatabase.insert(USER_TABLE, null, objContentValues);

    }// addnewUser


}//Main Class
