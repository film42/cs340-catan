package model.map;

import com.google.gson.annotations.SerializedName;
import model.JsonImpl;
import modelInterfaces.map.Location;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: film42 on: 3/19/14.
 */
public class NumbersImpl extends JsonImpl {

    @SerializedName("2")
    private List<Location> number2;
    @SerializedName("3")
    private List<Location> number3;
    @SerializedName("4")
    private List<Location> number4;
    @SerializedName("5")
    private List<Location> number5;
    @SerializedName("6")
    private List<Location> number6;
    @SerializedName("8")
    private List<Location> number8;
    @SerializedName("9")
    private List<Location> number9;
    @SerializedName("10")
    private List<Location> number10;
    @SerializedName("11")
    private List<Location> number11;
    @SerializedName("12")
    private List<Location> number12;

    public List<Location> getLocations(int number){
        switch(number){
            case 2:
                return number2;
            case 3:
                return number3;
            case 4:
                return number4;
            case 5:
                return number5;
            case 6:
                return number6;
            case 8:
                return number8;
            case 9:
                return number9;
            case 10:
                return number10;
            case 11:
                return number11;
            case 12:
                return number12;
            default:
                throw new InvalidParameterException("Number not supported");
        }

    }


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
