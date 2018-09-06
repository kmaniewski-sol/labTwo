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

    public void save(List<Stock> stocks) { stockRepository.saveAll(stocks); }

    public Double findDailyClosingPriceBySymbolAndDate(String symbol, String date) {
        Date dateD = convertDate(date);
        return stockRepository.findDailyClosingPriceBySymbolAndDate(symbol, dateD).get(0);
    }

    public Double findMonthlyClosingPriceBySymbolAndDate(String symbol, String date) {
        Date dateD = convertDate(date);
        return stockRepository.findMonthlyClosingPriceBySymbolAndDate(symbol, dateD).get(0);
    }

    public List<Object[]> findDailySummaryBySymbolAndDate(String symbol, String date) {
        Date dateD = convertDate(date);
        return stockRepository.findDailySummaryBySymbolAndDate(symbol, dateD);
    }

    public List<Object[]> findMonthlySummaryBySymbolAndDate(String symbol, String date) {
        Date dateD = convertDate(date);
        return stockRepository.findMonthlySummaryBySymbolAndDate(symbol, dateD);
    }

    private Date convertDate(String date){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
