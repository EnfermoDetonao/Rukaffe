package Database;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import Models.Inventory;
import Util.Constants;

public class DatabaseQueryClass {

        private Context context;

        public DatabaseQueryClass(Context context){
            this.context = context;
        }

        public long insertStudent(Inventory inventory){

            long id = -1;
            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
            SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.INVENTORY_NAME, inventory.getName());
            contentValues.put(Constants.INVENTORY_CANTIDAD, inventory.getCantidad());
            contentValues.put(Constants.INVENTORY_FECHA, inventory.getFecha());

            try {
                id = sqLiteDatabase.insertOrThrow(Constants.TABLE_INVENTORY, null, contentValues);
            } catch (SQLiteException e){
                Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                sqLiteDatabase.close();
            }

            return id;
        }

        public List<Inventory> getAllStudent(){

            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
            SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

            Cursor cursor = null;
            try {

                cursor = sqLiteDatabase.query(Constants.TABLE_INVENTORY, null, null, null, null, null, null, null);

                /**
                 // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

                 String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
                 cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
                 */

                if(cursor!=null)
                    if(cursor.moveToFirst()){
                        List<Inventory> studentList = new ArrayList<>();
                        do {
                            int id = cursor.getInt(cursor.getColumnIndex(Constants.INVENTORY_ID));
                            String name = cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_NAME));
                            int cantidad= Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_CANTIDAD)));
                            String Fecha = cursor.getString(cursor.getColumnIndex(Constants.INVENTORY_FECHA));

                            studentList.add(new Inventory(id, name, cantidad, Fecha));
                        }   while (cursor.moveToNext());

                        return studentList;
                    }
            } catch (Exception e){
                Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
            } finally {
                if(cursor!=null)
                    cursor.close();
                sqLiteDatabase.close();
            }

            return Collections.emptyList();
        }


        public long updateStudentInfo(Inventory inventory){

            long rowCount = 0;
            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
            SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.INVENTORY_NAME, inventory.getName());
            contentValues.put(Constants.INVENTORY_CANTIDAD, inventory.getCantidad());
            contentValues.put(Constants.INVENTORY_FECHA, inventory.getFecha());

            try {
                rowCount = sqLiteDatabase.update(Constants.TABLE_INVENTORY, contentValues,
                        Constants.INVENTORY_ID + " = ? ",
                        new String[] {String.valueOf(inventory.getId())});
            } catch (SQLiteException e){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                sqLiteDatabase.close();
            }

            return rowCount;
        }

        public long deleteStudentByRegNum(long registrationNum) {
            long deletedRowCount = -1;
            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
            SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

            try {
            } catch (SQLiteException e){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                sqLiteDatabase.close();
            }

            return deletedRowCount;
        }

        public boolean deleteAllStudents(){
            boolean deleteStatus = false;
            DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
            SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

            try {
                //for "1" delete() method returns number of deleted rows
                //if you don't want row count just use delete(TABLE_NAME, null, null)
                sqLiteDatabase.delete(Constants.TABLE_INVENTORY, null, null);

                long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Constants.TABLE_INVENTORY);

                if(count==0)
                    deleteStatus = true;

            } catch (SQLiteException e){
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                sqLiteDatabase.close();
            }
            return deleteStatus;
        }
    }

