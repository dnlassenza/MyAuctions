package com.assenza.alejandro.myauctions.models.Login;

import android.content.Context;

import com.assenza.alejandro.myauctions.DbHandler.DbHandler;

/**
 * Created by alejandro on 09/07/17.
 */

public final class SignUp {

    public static long TrySignUp(Context context, User user) {

        DbHandler db = new DbHandler(context);
        try {
            return db.AddUser(user);
        } catch (Exception e) {
            return DbHandler.USER_DOESNT_EXIST;
        }
    }
}
