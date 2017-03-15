package co.edu.udea.compumovil.gr08_20171.Lab2.Activity.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.Models.DAO.DBInfomation;
import co.edu.udea.compumovil.gr08_20171.Lab2.Activity.Models.DAO.DbHelper;
import co.edu.udea.compumovil.gr08_20171.Lab2.R;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    private static DbHelper dbHelper;
    private static SQLiteDatabase db;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "lucas123:123456", "maria12:hola123"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView usernameView;
    private EditText mPasswordView;
    private Button buttonRegister;
    private Button signInButton;

    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login");

        dbHelper = new DbHelper(this); //Instancia de DbHelper
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBInfomation.ColumnUser.ID, "1"); //Se pasan pares nombre-valor
        values.put(DBInfomation.ColumnUser.NAME, "Luisa");
        values.put(DBInfomation.ColumnUser.PASSWORD, "123");
        values.put(DBInfomation.ColumnUser.EMAIL, "luisa@gmail.com");
        values.put(DBInfomation.ColumnUser.AGE, "12");
        values.put(DBInfomation.ColumnUser.USERNAME, "Luisita");
        values.put(DBInfomation.ColumnUser.PHOTO, "Imagen");
        db.insertWithOnConflict(DBInfomation.USER_TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        usernameView = (AutoCompleteTextView) findViewById(R.id.username_login);
        mPasswordView = (EditText) findViewById(R.id.password_login);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        signInButton = (Button) findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        buttonRegister = (Button) findViewById(R.id.register_button);

        buttonRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intRegister = new Intent(Login.this, Register.class);
                    startActivity(intRegister);
                }catch(Exception e){
                    //TODO: Revisar recurso
                    //Snackbar.make(findViewById(R.id.login_layout), "Try again later.", Snackbar.LENGTH_LONG).setAction("action", null).show();
                }
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        /**Comprobar si el objeto para el usuario esta vacio o no*/
        if (mAuthTask != null) {
            return;
        }

        /**Resetea los Errores*/
        usernameView.setError(null);
        mPasswordView.setError(null);

        /**Obtiene y guarda los valores respectivos para el email y el password*/
        String username = usernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        /**
         * Bandera evidenciar algun error durante la validación de los datos
         * Variable para contener el campo a ser enfocado
         */
        boolean cancel = false;
        View focusView = null;

        /**Comprobar si el password ingresado no es nulo y es valido*/
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            /**Envia el error a la caja de Texto*/
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        /**Comprobar si el campo para el username esta vacio. */
        if (TextUtils.isEmpty(username)) {
            /**Envia el error a la caja de Texto*/
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        }
        /**Comprobar si el UserName Ingresado es valido.*/
        else if (!isUsernameValid(username)) {
            /**Envia el error a la caja de Texto*/
            usernameView.setError(getString(R.string.error_invalid_email));
            focusView = usernameView;
            cancel = true;
        }

        /**Comprobar si hubo un fallo durante el ingreso de datos*/
        if (cancel) {
            /**Enfocar el Campo del Error*/
            focusView.requestFocus();
        } else {
            /**Cargar Animación con una barra de progreso*/
            showProgress(true);

            /**Crea un nuevo Usuario a partir de la clase  mAuthTask*/
            mAuthTask = new UserLoginTask(username, password);
            /**Lanzar el Hilo para la Autenticación del Usuario*/
            mAuthTask.execute((Void) null);
        }

    }

    private boolean isUsernameValid(String username) {
        //TODO: Definit logica para las validaciones del username
        return username.length() > 5;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Definit logica para las validaciones del password
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> username = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            username.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

       /*Con la Base de Datos
       if(dbHelper.loginQuery(db, username, password)){
           Log.i("Login.java","El usuario esta en la d :D");

       }else{
           Log.e("Login.java","El usuario no existe en la db");
       }*/
    //TODO: Hacerlo con la Base de Datos
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    //Clase para Almacenar los Usuarios
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private final String mPassword;
        private int op;

        UserLoginTask(String username, String password) {
            mUsername = username;
            mPassword = password;
        }

        //------------------------------------------------------------------------------------------
        //Hilo para validar si el Correo y contraseña ingresados son correctos
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                /**Ejecución del Hilo con un retraso de 2 segundos*/
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            /**Ciclo en el cual se comparan los Emails y Contraseñas alamacenados en el Array tipo
             * string definido al inicio del activity y el email y clave ingresados por el usuario
             * en el formulario de Login*/
            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mUsername)) {
                    /**Retorna verdadero si  la Contraseña Coincide*/
                    return pieces[1].equals(mPassword);
                }
            }

            op = 1;
            return false;
        }
        //------------------------------------------------------------------------------------------
        //Muestra en el Activity actual el resultado de la tarea que se ejecuto en el Hilo

        /**
         * En este caso Abre el Activity MenuPrincipal si los datos Fueron correctos de lo contrario
         * Lanza un mensaje Evidenciando el problema
         */
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Intent i = new Intent(Login.this, MenuPrincipal.class);
                startActivity(i);
            } else {
                if (op == 0) {
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                    mPasswordView.requestFocus();
                } else {
                    Toast toast = Toast.makeText(Login.this, R.string.alert_not_found_username, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
            }
        }

        //------------------------------------------------------------------------------------------
        //En caso de que se cancele la Tarea inmersa en el Hilo
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

