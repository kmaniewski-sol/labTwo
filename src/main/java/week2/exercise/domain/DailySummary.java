package week2.exercise.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;

public class DailySummary {

    private String symbol;
    private Date date;
    private  double maxPrice;
    private  double minPrice;
    private  int sumVolume;
    private  double closingPrice;

    public DailySummary(String symbol, Date date, double maxPrice, double minPrice, int sumVolume, double closingPrice){
        this.symbol = symbol;
        this.date = date;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.sumVolume = sumVolume;
        this.closingPrice = closingPrice;
    }

    @JsonGetter("symbol")
    public String symbol(){ return symbol; }

    @JsonGetter("date")
    public Date date(){ return date; }

    @JsonGetter("maxPrice")
    public double maxPrice(){ return maxPrice; }

    @JsonGetter("minPrice")
    public double minPrice(){ return minPrice; }

    @JsonGetter("sumVolume")
    public int sumVolume(){ return sumVolume; }

    @JsonGetter("closingPrice")
    public double closingPrice(){ return closingPrice; }

    @JsonSetter("symbol")
    public void symbol(String symbol){ this.symbol = symbol; }

    @JsonSetter("date")
    public void date(Date date){ this.date = date; }

    @JsonSetter("maxPrice")
    public void maxPrice(double maxPrice){ this.maxPrice = maxPrice; }

    @JsonSetter("minPrice")
    public void minPrice(double minPrice){ this.minPrice = minPrice; }

    @JsonSetter("sumVolume")
    public void sumVolume(int sumVolume){ this.sumVolume = sumVolume; }

    @JsonSetter("closingPrice")
    public void closingPrice(double closingPrice){ this.closingPrice = closingPrice; }
}
