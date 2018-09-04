package week2.exercise.service;

import org.springframework.stereotype.Service;
import week2.exercise.domain.Stock;
import week2.exercise.repository.StockRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void save(List<Stock> stocks) {
        stockRepository.saveAll(stocks);
    }

    public Double findMaxPriceBySymbolAndDate(String symbol, String date) {
        Date dateD = convertDate(date);
        return stockRepository.findMaxPriceBySymbolAndDate(symbol, dateD);
    }

    public Double findMinPriceBySymbolAndDate(String symbol, String date) {
        Date dateD = convertDate(date);
        return stockRepository.findMinPriceBySymbolAndDate(symbol, dateD);
    }

    public int findSumVolumeBySymbolAndDate(String symbol, String date) {
        Date dateD = convertDate(date);
        return stockRepository.findSumVolumeBySymbolAndDate(symbol, dateD);
    }

    public Double findClosingPriceBySymbolAndDate(String symbol, String date) {
        Date dateD = convertDate(date);
        return stockRepository.findClosingPriceBySymbolAndDate(symbol, dateD).get(0);
    }

    public Date convertDate(String date){
        Date dateD= null;
        try {
            dateD = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateD;
    }
}
