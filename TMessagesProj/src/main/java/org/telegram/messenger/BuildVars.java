/*
 * This is the source code of Telegram for Android v. 4.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2017.
 */

package org.telegram.messenger;

import android.content.Context;
import android.content.SharedPreferences;

public class BuildVars {

    public static boolean DEBUG_VERSION = false;
    public static boolean DEBUG_PRIVATE_VERSION = false;
    public static int BUILD_VERSION = 1340;
    public static String BUILD_VERSION_STRING = "4.9.0";
    public static boolean LOGS_ENABLED = false;
    public static boolean CHECK_UPDATES = false;

    public static int APP_ID = 51586; //obtain your own APP_ID at https://core.telegram.org/api/obtaining_api_id
    public static String APP_HASH = "54cd7f6c5b2fa5092e2eac5e90bcbfae"; //obtain your own APP_HASH at https://core.telegram.org/api/obtaining_api_id
    public static String HOCKEY_APP_HASH = "cabc0fbe37344b1da9e491b9120f9ead"; // https://rink.hockeyapp.net/manage/dashboard
    public static String HOCKEY_APP_HASH_DEBUG = "aa07008c1dc74fb9eb5af61d6057738a"; // https://rink.hockeyapp.net/manage/dashboard
    public static String GCM_SENDER_ID = "AIzaSyBQguoECW_fF2DU8OL_zSVsA5binSJEK30";
    public static String SEND_LOGS_EMAIL = "developer.zhaitao@gmail.com";
    public static String FOURSQUARE_API_VERSION = "20150326";

    static {
        if (ApplicationLoader.applicationContext != null) {
            SharedPreferences sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", Context.MODE_PRIVATE);
            LOGS_ENABLED = sharedPreferences.getBoolean("logsEnabled", DEBUG_VERSION);
        }
    }
}
