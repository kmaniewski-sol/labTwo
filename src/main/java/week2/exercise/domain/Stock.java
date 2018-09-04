package week2.exercise.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    private String symbol;
    private  double price;
    private  int volume;
    private Timestamp date;

    public Stock(){}

    @JsonGetter("symbol")
    public String symbol() {
        return symbol;
    }

    @JsonGetter("price")
    public double price(){
        return price;
    }

    @JsonGetter("volume")
    public int volume(){ return volume; }

    @JsonGetter("date")
    public Timestamp date(){ return date; }

    @JsonSetter("symbol")
    public void symbol(String symbol) { this.symbol = symbol; }

    @JsonSetter("price")
    public void price(double price){ this.price = price; }

    @JsonSetter("volume")
    public void volume(int volume){ this.volume = volume; }

    @JsonSetter("date")
    public void date(Timestamp date){ this.date = date; }
}
