package model.map;

import com.google.gson.annotations.SerializedName;
import model.JsonImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/19/14.
 */
public class NumbersImpl extends JsonImpl {

    @SerializedName("2")
    private List<LocationImpl> number2;
    @SerializedName("3")
    private List<LocationImpl> number3;
    @SerializedName("4")
    private List<LocationImpl> number4;
    @SerializedName("5")
    private List<LocationImpl> number5;
    @SerializedName("6")
    private List<LocationImpl> number6;
    @SerializedName("8")
    private List<LocationImpl> number8;
    @SerializedName("9")
    private List<LocationImpl> number9;
    @SerializedName("10")
    private List<LocationImpl> number10;
    @SerializedName("11")
    private List<LocationImpl> number11;
    @SerializedName("12")
    private List<LocationImpl> number12;

    public NumbersImpl() {
        this.number2 = new ArrayList<>();
        this.number3 = new ArrayList<>();
        this.number4 = new ArrayList<>();
        this.number5 = new ArrayList<>();
        this.number6 = new ArrayList<>();
        this.number8 = new ArrayList<>();
        this.number9 = new ArrayList<>();
        this.number10 = new ArrayList<>();
        this.number11 = new ArrayList<>();
        this.number12 = new ArrayList<>();
    }
}
