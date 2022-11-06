package com.app.appbelajarkomik.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Constant {
    public static SharedPreferences sharedPreferences;
    private static final String PREF ="pref";


    private static final String AdsId="AdsId";
    private static final String BannerId= "BannerId";
    private static final String InterId="InterId";
    private static final String NativeId="NativeId";
    public static String idLogin = "idLogin";

    public static String isFirstTime = "isFirstTime";


    public static void setIsFirstTime(Context context, boolean value){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(isFirstTime, value);
        editor.commit();
        editor.apply();
    }

    public static boolean getIsFirstTime(Context context){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(isFirstTime, true);
    }

    public static void setId(Context context, String value) {
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(idLogin, value);
        editor.commit();
        editor.apply();
    }

    public static String getId(Context context){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(idLogin, null);
    }


    public static void setBannerId(Context context, String id){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putString(BannerId, id);
        myEditor.commit();
        myEditor.apply();
    }

    public static String getBannerId(Context context){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(BannerId,null);
    }

    public static void setInterId(Context context, String id){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putString(InterId, id);
        myEditor.commit();
        myEditor.apply();
    }

    public static String getInterId(Context context){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(InterId,null);
    }

    public static void setNativeId(Context context, String id){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = sharedPreferences.edit();
        myEditor.putString(NativeId, id);
        myEditor.commit();
        myEditor.apply();
    }

    public static String getNativeId(Context context){
        sharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        return sharedPreferences.getString(NativeId,null);
    }


    public static String htmlGenre(){
       return "<div class=\"post-show\">\n" +
               "<ul class=\"genrelist\"><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/action/\" title=\"Komik Genre Action\">Action</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/adventure/\" title=\"Komik Genre Adventure\">Adventure</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/boys-love/\" title=\"Komik Genre Boys' Love\">Boys' Love</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/comedy/\" title=\"Komik Genre Comedy\">Comedy</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/crime/\" title=\"Komik Genre Crime\">Crime</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/drama/\" title=\"Komik Genre Drama\">Drama</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/fantasy/\" title=\"Komik Genre Fantasy\">Fantasy</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/girls-love/\" title=\"Komik Genre Girls' Love\">Girls' Love</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/harem/\" title=\"Komik Genre Harem\">Harem</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/historical/\" title=\"Komik Genre Historical\">Historical</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/horror/\" title=\"Komik Genre Horror\">Horror</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/isekai/\" title=\"Komik Genre Isekai\">Isekai</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/magical-girls/\" title=\"Komik Genre Magical Girls\">Magical Girls</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/mecha/\" title=\"Komik Genre Mecha\">Mecha</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/medical/\" title=\"Komik Genre Medical\">Medical</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/music/\" title=\"Komik Genre Music\">Music</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/mystery/\" title=\"Komik Genre Mystery\">Mystery</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/philosophical/\" title=\"Komik Genre Philosophical\">Philosophical</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/psychological/\" title=\"Komik Genre Psychological\">Psychological</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/romance/\" title=\"Komik Genre Romance\">Romance</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/sci-fi/\" title=\"Komik Genre Sci-Fi\">Sci-Fi</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/shoujo-ai/\" title=\"Komik Genre Shoujo Ai\">Shoujo Ai</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/shounen-ai/\" title=\"Komik Genre Shounen Ai\">Shounen Ai</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/slice-of-life/\" title=\"Komik Genre Slice of Life\">Slice of Life</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/sports/\" title=\"Komik Genre Sports\">Sports</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/superhero/\" title=\"Komik Genre Superhero\">Superhero</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/thriller/\" title=\"Komik Genre Thriller\">Thriller</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/tragedy/\" title=\"Komik Genre Tragedy\">Tragedy</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/wuxia/\" title=\"Komik Genre Wuxia\">Wuxia</a></li><li><a class=\"genrtil\" href=\"https://komikindo.id/genres/yuri/\" title=\"Komik Genre Yuri\">Yuri</a></li></ul>\n" +
               "</div>";
        }
}
