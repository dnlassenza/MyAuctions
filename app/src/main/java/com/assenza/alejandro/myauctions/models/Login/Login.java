package com.assenza.alejandro.myauctions.models.Login;

import android.content.Context;
import com.assenza.alejandro.myauctions.DbHandler.DbHandler;
/**
 * Created by alejandro on 09/07/17.
 */

public final class Login {

    public static long TryLogin(Context context, User user) {

        DbHandler db = new DbHandler(context);
        return db.GetUserId(user);
    }
}
