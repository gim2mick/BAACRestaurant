package baac.gim2mick.baacrestaurant;

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
    

    public UserTABLE(Context context) {
        objGim2mickOpenHelper = new gim2mickOpenHelper(context);
        writeSqLiteDatabase = objGim2mickOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objGim2mickOpenHelper.getReadableDatabase();
    }//Constructor

}//Main Class
