package co.edu.udea.compumovil.gr08_20171.Lab2.Activity.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.Models.DAO.DBInfomation;
import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.Models.DAO.DbHelper;
import co.edu.udea.compumovil.gr08_20171.Lab2.R;

public class Register extends AppCompatActivity {

    private  EditText mUsername;
    private  EditText mPassword;
    private  EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Register");

        mUsername = (EditText) findViewById(R.id.username_register);
        mPassword = (EditText) findViewById(R.id.password_register);
        mEmail = (EditText) findViewById(R.id.email_register);
    }

        //Guarda la informacion en la base de datos
        public void saveInfo(View v){
            DbHelper dbHelper = new DbHelper(this); //Instancia de DbHelper
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //Obtener instancia de la BD
            ContentValues values = new ContentValues();
            //values.put(user.Column.ID,); //Se pasan pares nombre-valor
            values.put(DBInfomation.ColumnUser.USERNAME,
                    mUsername.getText().toString());
            values.put(DBInfomation.ColumnUser.EMAIL,
                    mEmail.getText().toString());
            values.put(DBInfomation.ColumnUser.PASSWORD, mPassword.getText().toString());
            db.insertWithOnConflict(DBInfomation.USER_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
            finish();//Se guarda la fila en la base de datos
        }
}
