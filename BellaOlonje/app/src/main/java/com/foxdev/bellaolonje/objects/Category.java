package com.foxdev.bellaolonje.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public final class Category implements Parcelable {
    @SerializedName("idCategory")
    public final int idCategory;

    @SerializedName("strCategory")
    public final String strCategory;

    @SerializedName("strCategoryDescription")
    public final String strCategoryDescription;

    @SerializedName("strCategoryThumb")
    public final String strCategoryThumb;

    protected Category(Parcel in) {
        idCategory = in.readInt();
        strCategory = in.readString();
        strCategoryDescription = in.readString();
        strCategoryThumb = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idCategory);
        dest.writeString(strCategory);
        dest.writeString(strCategoryDescription);
        dest.writeString(strCategoryThumb);
    }
}
