package week2.exercise.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Summary {
    public final String symbol;
    public final String date;
    public final  double maxPrice;
    public final  double minPrice;
    public final  long sumVolume;
    public final  double closingPrice;

    @JsonCreator
    public Summary(@JsonProperty("symbol") String symbol, @JsonProperty("date") String date,
                   @JsonProperty("maxPrice") double maxPrice, @JsonProperty("minPrice") double minPrice,
                   @JsonProperty("sumVolume") long sumVolume, @JsonProperty("closingPrice") double closingPrice){
        this.symbol = symbol;
        this.date = date;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.sumVolume = sumVolume;
        this.closingPrice = closingPrice;
    }
}
