package week2.exercise.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import week2.exercise.domain.DailySummary;
import week2.exercise.domain.Stock;
import week2.exercise.service.StockService;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        Date dateD= null;
        try {
            dateD = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Double max = stockService.findMaxPriceBySymbolAndDate(symbol, dateD);
        Double min = stockService.findMinPriceBySymbolAndDate(symbol, dateD);
        int volume = stockService.findSumVolumeBySymbolAndDate(symbol, dateD);
        Double closingPrice = stockService.findClosingPriceBySymbolAndDate(symbol, dateD);

        return new DailySummary(symbol, date, max, min, volume, closingPrice);
    }

}
