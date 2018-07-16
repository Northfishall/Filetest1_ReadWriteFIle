package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonSqliteOpenHelper extends SQLiteOpenHelper
{
    public PersonSqliteOpenHelper(Context context)
    {
        super(context,"np.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists np(id varchar(20) , password varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
