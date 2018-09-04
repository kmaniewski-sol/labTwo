package week2.exercise.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    public final String symbol;
    public final  double price;
    public final  int volume;
    public final Timestamp date;

    @JsonCreator
    public Stock(@JsonProperty("symbol") String symbol, @JsonProperty("price") double price,
                 @JsonProperty("volume") int volume, @JsonProperty("date") Timestamp date) {
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.date = date;
    }
}
