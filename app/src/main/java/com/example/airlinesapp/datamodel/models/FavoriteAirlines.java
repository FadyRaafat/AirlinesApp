package com.example.airlinesapp.datamodel.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class FavoriteAirlines {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @SerializedName("code")
    @ColumnInfo(name = "code")
    private String code;

    @SerializedName("defaultName")
    @ColumnInfo(name = "defaultName")
    private String defaultName;

    @SerializedName("logoURL")
    @ColumnInfo(name = "logoURL")
    private String logoURL;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    private String phone;

    @SerializedName("site")
    @ColumnInfo(name = "site")
    private String site;

    @SerializedName("usName")
    @ColumnInfo(name = "usName")
    private String usName;

    public FavoriteAirlines(@NonNull String code, String defaultName,
                            String logoURL, String name, String phone,
                            String site, String usName) {
        this.code = code;
        this.defaultName = defaultName;
        this.logoURL = logoURL;
        this.name = name;
        this.phone = phone;
        this.site = site;
        this.usName = usName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }


}
