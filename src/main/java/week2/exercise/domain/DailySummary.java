package week2.exercise.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DailySummary {

    public final String symbol;
    public final String date;
    public final  double maxPrice;
    public final  double minPrice;
    public final  int sumVolume;
    public final  double closingPrice;

    @JsonCreator
    public DailySummary(@JsonProperty("symbol") String symbol, @JsonProperty("date") String date,
                        @JsonProperty("maxPrice") double maxPrice, @JsonProperty("minPrice") double minPrice,
                        @JsonProperty("sumVolume") int sumVolume, @JsonProperty("closingPrice") double closingPrice){
        this.symbol = symbol;
        this.date = date;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.sumVolume = sumVolume;
        this.closingPrice = closingPrice;
    }
}
