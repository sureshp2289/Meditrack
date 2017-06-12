package medicine.example.com.dailyalert.sqlite;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseHelper extends SQLiteOpenHelper {

	static final String dbName="medicinedb";


	static final String usertable="users";
	static final String Id="id";
	static final String name="name";
	static final String age="age";
	static final String sosname="sosname";
	static final String sosnumber="sosnumber";
	static final String uid="uid";

	static final String medicinetable="medicine";
	static final String Id1="id1";
	static final String mname="mname";
	static final String dose="dose";
	static final String quantity="quantity";
	static final String noofdoseperday="noofdoseperday";
	static final String reminder1 ="reminder1";
	static final String reminder2 ="reminder2";
	static final String noofmedicine ="noofmedicine";
	static final String mid="mid";
	static final String userid="userid";



	public DatabaseHelper(Context context) {
		super(context, dbName, null,33);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		
		db.execSQL("CREATE TABLE "+usertable+" ("+Id+" INTEGER PRIMARY KEY AUTOINCREMENT," +name+" TEXT, "+age+" TEXT, "+sosname+" TEXT,"+sosnumber+" TEXT,"+uid+" TEXT)");
		db.execSQL("CREATE TABLE "+medicinetable+" ("+Id1+" INTEGER PRIMARY KEY AUTOINCREMENT," +mname+" TEXT, "+dose+" TEXT, "+quantity+" TEXT,"+noofdoseperday+" TEXT,"+reminder1+" TEXT,"+reminder2+" TEXT,"+noofmedicine+" TEXT,"+mid+" TEXT,"+userid+" TEXT)");

		//InsertDepts(db);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		db.execSQL("DROP TABLE IF EXISTS "+usertable);
		db.execSQL("DROP TABLE IF EXISTS "+medicinetable);


		onCreate(db);
	}
	// Inserting the questions

	public long InsertData(HashMap<String,String> map) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(name, map.get("name")); // Contact Name
		values.put(age, map.get("age"));
		values.put(sosname,map.get("sosname"));
		values.put(sosnumber, map.get("sosnumber"));
		values.put(uid,map.get("uid"));/// Contact Phone

		// Inserting Row
		long insertId=db.insert(usertable, null, values);
		db.close(); // Closing database connectionr
		return insertId;
	}
	public ArrayList<HashMap<String,String>> getUserdetails(String name2) {
		ArrayList<HashMap<String,String>> userlist = new ArrayList<HashMap<String,String>>();
		// Select All Query
	//	String selectQuery = "SELECT  * FROM " +usertable+"WHERE" +name+"=?";
		String[] selection={name,age,sosname,sosnumber,uid};
		String[] selection1={name2};
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor=db.query(usertable, selection,name+"=?",selection1, null, null, null);


		// looping through all rows and adding to list
		int i=cursor.getCount();

		if (cursor.moveToFirst()) {
			do {
				HashMap<String,String> map=new HashMap<String, String>();
				map.put("name",cursor.getString(0));
				map.put("age",cursor.getString(1));
				map.put("sosname",cursor.getString(2));
				map.put("sosnumber",cursor.getString(3));
				map.put("uid",cursor.getString(4));
				userlist.add(map);

			} while (cursor.moveToNext());
		}

		// return contact list
		return userlist;
	}

	public long UpdateSosData(String sosname1,String sosnumber1,String uid1) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(sosname,sosname1);
		values.put(sosnumber,sosnumber1);

		// Inserting Row
		String[] selection1={uid1};
		long i=db.update(usertable,values, uid+"=?",selection1);
		// Closing database connectionr
        return i;
	}
	public long EditUsers(String name1,String age1,String uid1) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(name,name1);
		values.put(age,age1);

		// Inserting Row
		String[] selection1={uid1};
		long i=db.update(usertable,values, uid+"=?",selection1);
		// Closing database connectionr
		return i;
	}
	public long InsertMedicine(HashMap<String,String> map) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(mname, map.get("mname")); // Contact Name
		values.put(dose, map.get("dose"));
		values.put(quantity,map.get("quantity"));
		values.put(noofdoseperday, map.get("noofdoseperday"));
		values.put(reminder1, map.get("reminder1"));
		values.put(reminder2, map.get("reminder2"));
		values.put(noofmedicine, map.get("noofmedicine"));
		values.put(mid, map.get("mid"));
		values.put(userid,map.get("userid"));/// Contact Phone

		// Inserting Row
		long insertId=db.insert(medicinetable, null, values);
		db.close(); // Closing database connectionr
		return insertId;
	}

	public int CheckMedicineisExist(String name2) {
		// Select All Query
		//	String selectQuery = "SELECT  * FROM " +usertable+"WHERE" +name+"=?";
		String[] selection={mname};
		String[] selection1={name2};
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor=db.query(medicinetable, selection,mname+"=?",selection1, null, null, null);


		// looping through all rows and adding to list
		int i=cursor.getCount();


		// return contact list
		return i;
	}

	public long UpdateMedicineDetails(HashMap<String,String> map) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(mname, map.get("mname")); // Contact Name
		values.put(dose, map.get("dose"));
		values.put(quantity,map.get("quantity"));
		values.put(noofdoseperday, map.get("noofdoseperday"));
		values.put(reminder1, map.get("reminder1"));
		values.put(reminder2, map.get("reminder2"));
		values.put(noofmedicine, map.get("noofmedicine"));

		// Inserting Row
		String[] selection1={map.get("mid")};
		long i=db.update(medicinetable,values, mid+"=?",selection1);
		// Closing database connectionr
		return i;
	}
	public ArrayList<HashMap<String,String>> getMedicineDetalils(String uid) {
		ArrayList<HashMap<String,String>> medicinelist = new ArrayList<HashMap<String,String>>();
		// Select All Query
		//	String selectQuery = "SELECT  * FROM " +usertable+"WHERE" +name+"=?";
		String[] selection={mname,dose,quantity,noofdoseperday,reminder1,reminder2,noofmedicine,mid,userid};
		String[] selection1={uid};
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor=db.query(medicinetable, selection,userid+"=?",selection1, null, null, null);


		// looping through all rows and adding to list
		int i=cursor.getCount();

		if (cursor.moveToFirst()) {
			do {
				HashMap<String,String> map=new HashMap<String, String>();
				map.put("mname",cursor.getString(0));
				map.put("dose",cursor.getString(1));
				map.put("quantity",cursor.getString(2));
				map.put("noofdoseperday",cursor.getString(3));
				map.put("reminder1",cursor.getString(4));
				map.put("reminder2",cursor.getString(5));
				map.put("noofmedicine",cursor.getString(6));
				map.put("mid",cursor.getString(7));
				map.put("userid",cursor.getString(8));
				medicinelist.add(map);

			} while (cursor.moveToNext());
		}

		// return contact list
		return medicinelist;
	}
	public ArrayList<HashMap<String,String>> getDayMedicineDetalils(String uid,String days) {
		ArrayList<HashMap<String,String>> daymedicinelist = new ArrayList<HashMap<String,String>>();
		Cursor cursor = null;
		String[] selection1;
		SQLiteDatabase db = this.getReadableDatabase();


		// Select All Query
		//	String selectQuery = "SELECT  * FROM " +usertable+"WHERE" +name+"=?";
		String[] selection={mname,dose,quantity,noofdoseperday,reminder1,reminder2,noofmedicine,mid,userid};
		if(days.equalsIgnoreCase("Daily")) {
			selection1 = new String[]{uid, days};
			cursor = db.query(medicinetable, selection, userid + "=?" + " AND " + dose + "=?", selection1, null, null, null);
		}
		else {
			selection1 = new String[]{uid};
			cursor = db.query(medicinetable, selection, userid + "=?", selection1, null, null, null);
		}

		// looping through all rows and adding to list
		int i=cursor.getCount();

		if (cursor.moveToFirst()) {
			do {
				HashMap<String,String> map=new HashMap<String, String>();
				map.put("mname",cursor.getString(0));
				map.put("dose",cursor.getString(1));
				map.put("quantity",cursor.getString(2));
				map.put("noofdoseperday",cursor.getString(3));
				map.put("reminder1",cursor.getString(4));
				map.put("reminder2",cursor.getString(5));
				map.put("noofmedicine",cursor.getString(6));
				map.put("mid",cursor.getString(7));
				map.put("userid",cursor.getString(8));
				daymedicinelist.add(map);

			} while (cursor.moveToNext());
		}

		// return contact list
		return daymedicinelist;
	}
}
