package com.foxdev.bellaolonje.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class FoodCollection {

    @SerializedName("meals")
    @Expose
    public Food[] meals;
}
