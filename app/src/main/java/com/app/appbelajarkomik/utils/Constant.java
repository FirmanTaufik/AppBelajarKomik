package com.app.appbelajarkomik.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Constant {
    public static SharedPreferences sharedPreferences;
    private static final String PREF ="pref";


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

    public static String htmlGenre(){
        return "<div class=\"panel-category\"> \n" +
                "<p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-2\" title=\"Action Manga\">Action</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-4\" title=\"Adventure Manga\">Adventure</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-6\" title=\"Comedy Manga\">Comedy</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-7\" title=\"Cooking Manga\">Cooking</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-9\" title=\"Doujinshi Manga\">Doujinshi</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-10\" title=\"Drama Manga\">Drama</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-48\" title=\"Erotica Manga\">Erotica</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-12\" title=\"Fantasy Manga\">Fantasy</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-13\" title=\"Gender bender Manga\">Gender bender</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-14\" title=\"Harem Manga\">Harem</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-15\" title=\"Historical Manga\">Historical</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-16\" title=\"Horror Manga\">Horror</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-45\" title=\"Isekai Manga\">Isekai</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-17\" title=\"Josei Manga\">Josei</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-44\" title=\"Manhua Manga\">Manhua</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-43\" title=\"Manhwa Manga\">Manhwa</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-19\" title=\"Martial arts Manga\">Martial arts</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-20\" title=\"Mature Manga\">Mature</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-21\" title=\"Mecha Manga\">Mecha</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-22\" title=\"Medical Manga\">Medical</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-24\" title=\"Mystery Manga\">Mystery</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-25\" title=\"One shot Manga\">One shot</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-47\" title=\"Pornographic Manga\">Pornographic</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-26\" title=\"Psychological Manga\">Psychological</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-27\" title=\"Romance Manga\">Romance</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-28\" title=\"School life Manga\">School life</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-29\" title=\"Sci fi Manga\">Sci fi</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-30\" title=\"Seinen Manga\">Seinen</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-31\" title=\"Shoujo Manga\">Shoujo</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-32\" title=\"Shoujo ai Manga\">Shoujo ai</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-33\" title=\"Shounen Manga\">Shounen</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-34\" title=\"Shounen ai Manga\">Shounen ai</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-35\" title=\"Slice of life Manga\">Slice of life</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-36\" title=\"Smut Manga\">Smut</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-37\" title=\"Sports Manga\">Sports</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-38\" title=\"Supernatural Manga\">Supernatural</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-39\" title=\"Tragedy Manga\">Tragedy</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-40\" title=\"Webtoons Manga\">Webtoons</a></p><p class=\"pn-category-row\"><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-41\" title=\"Yaoi Manga\">Yaoi</a><a class=\"a-h text-nowrap\" href=\"https://manganato.com/genre-42\" title=\"Yuri Manga\">Yuri</a></p></div>";
    }
}
