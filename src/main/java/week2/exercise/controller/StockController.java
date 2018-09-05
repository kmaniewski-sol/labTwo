package week2.exercise.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import week2.exercise.domain.Summary;
import week2.exercise.domain.Stock;
import week2.exercise.service.StockService;

import java.io.IOException;
import java.io.InputStream;
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

    @GetMapping(path="/{symbol}/{date}")
    public Summary daySummary(@PathVariable("symbol") String symbol, @PathVariable("date") String date){
        Double closingPrice = stockService.findDailyClosingPriceBySymbolAndDate(symbol, date);
        Double max = null; Double min = null; long volume = 0;
        List<Object[]> both = stockService.findDailySummaryBySymbolAndDate(symbol, date);
        for(Object o[]: both){
            max = (Double) o[0];
            min = (Double) o[1];
            volume = (long) o[2];
        }
        return new Summary(symbol, date, max, min, volume, closingPrice);
    }

    @GetMapping(path="/month/{symbol}/{date}")
    public Summary monthSummary(@PathVariable("symbol") String symbol, @PathVariable("date") String date){
        Double closingPrice = stockService.findMonthlyClosingPriceBySymbolAndDate(symbol, date);
        Double max = null; Double min = null; long volume = 0;
        List<Object[]> both = stockService.findMonthlySummaryBySymbolAndDate(symbol, date);
        for(Object o[]: both){
            max = (Double) o[0];
            min = (Double) o[1];
            volume = (long) o[2];
        }
        return new Summary(symbol, date, max, min, volume, closingPrice);
    }
}
