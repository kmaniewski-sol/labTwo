package week2.exercise.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import week2.exercise.domain.DailySummary;
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
    public DailySummary dSummary(@PathVariable("symbol") String symbol, @PathVariable("date") String date){
        Double closingPrice = stockService.findClosingPriceBySymbolAndDate(symbol, date);
        Double max = null; Double min = null; long volume = 0;
        List<Object[]> both = stockService.findSummaryBySymbolAndDate(symbol, date);
        for(Object o[]: both){
            max = (Double) o[0];
            min = (Double) o[1];
            volume = (long) o[2];
        }
        return new DailySummary(symbol, date, max, min, volume, closingPrice);
    }
}
