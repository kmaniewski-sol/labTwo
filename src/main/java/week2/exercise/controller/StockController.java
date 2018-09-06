package week2.exercise.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import week2.exercise.domain.Summary;
import week2.exercise.domain.Stock;
import week2.exercise.service.StockService;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
public class StockController {
    private StockService stockService;

    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @PostMapping("/load")
    public String load(){
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Stock>> typeReference = new TypeReference<List<Stock>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/stocks");
        try{
            List<Stock> stocks = objectMapper.readValue(inputStream, typeReference);
            stockService.save(stocks);
            return("Data was loaded into the database!");
        } catch (IOException ioE){
            return("Data was not uploaded due to issue with reading values: " + ioE);
        }
    }

    @PostMapping("/loadFromUrl")
    public String loadFromUrl(){
        String urlString = "https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json";
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Stock>> typeReference = new TypeReference<List<Stock>>(){};
        try {
            URL url = new URL(urlString);
            InputStream inputStream = (InputStream)url.getContent();
            List<Stock> stocks = objectMapper.readValue(inputStream, typeReference);
            stockService.save(stocks);
            return("Data was loaded into the database!");
        } catch (MalformedURLException e) {
            return("Data was not uploaded due to issue with URL: " + e);
        } catch (IOException e) {
            return("Data was not uploaded due to issue with reading content: " + e);
        }
    }

    @GetMapping(path="/{symbol}/{date}")
    public Summary daySummary(@PathVariable("symbol") String symbol, @PathVariable("date") String date){
        Double closingPrice = stockService.findDailyClosingPriceBySymbolAndDate(symbol, date);
        Double max = 0.0; Double min = 0.0; long volume = 0;
        List<Object[]> summary = stockService.findDailySummaryBySymbolAndDate(symbol, date);
        for(Object o[]: summary){
            max = (Double) o[0];
            min = (Double) o[1];
            volume = (long) o[2];
        }
        return new Summary(symbol, date, max, min, volume, closingPrice);
    }

    @GetMapping(path="/month/{symbol}/{date}")
    public Summary monthSummary(@PathVariable("symbol") String symbol, @PathVariable("date") String date){
        Double closingPrice = stockService.findMonthlyClosingPriceBySymbolAndDate(symbol, date);
        Double max = 0.0; Double min = 0.0; long volume = 0;
        List<Object[]> summary = stockService.findMonthlySummaryBySymbolAndDate(symbol, date);
        for(Object o[]: summary){
            max = (Double) o[0];
            min = (Double) o[1];
            volume = (long) o[2];
        }
        return new Summary(symbol, date, max, min, volume, closingPrice);
    }
}
