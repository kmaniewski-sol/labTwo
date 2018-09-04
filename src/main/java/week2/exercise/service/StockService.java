package week2.exercise.service;

import org.springframework.stereotype.Service;
import week2.exercise.domain.Stock;
import week2.exercise.repository.StockRepository;

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

    public Double findMaxPriceBySymbolAndDate(String symbol, Date date) {
        return stockRepository.findMaxPriceBySymbolAndDate(symbol, date);
    }

    public Double findMinPriceBySymbolAndDate(String symbol, Date date) {
        return stockRepository.findMinPriceBySymbolAndDate(symbol, date);
    }

    public int findSumVolumeBySymbolAndDate(String symbol, Date date) {
        return stockRepository.findSumVolumeBySymbolAndDate(symbol, date);
    }

    public Double findClosingPriceBySymbolAndDate(String symbol, Date date) {
        return stockRepository.findClosingPriceBySymbolAndDate(symbol, date).get(0);
    }

}
