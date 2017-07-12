package com.assenza.alejandro.myauctions.models.Login;

import android.content.Context;

import com.assenza.alejandro.myauctions.DbHandler.DbHandler;

/**
 * Created by alejandro on 09/07/17.
 */

public final class SignUp {

    public static boolean TrySignUp(Context context, User user) {

        DbHandler db = new DbHandler(context);
        try {
            db.AddUser(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
