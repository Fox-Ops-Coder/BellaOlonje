package com.foxdev.bellaolonje.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class CategoriesCollection {
    @SerializedName("categories")
    @Expose
    public Category[] categories;
}
