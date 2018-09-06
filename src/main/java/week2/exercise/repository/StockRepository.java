package week2.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import week2.exercise.domain.Stock;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Serializable> {
    @Query("SELECT price FROM Stock s WHERE s.stockSymbol.symbol = :symbol AND DATE(s.date) = :date ORDER BY s.date DESC")
    List<Double> findDailyClosingPriceBySymbolAndDate(@Param("symbol") String symbol, @Param("date") Date date);

    @Query("SELECT price FROM Stock s WHERE s.stockSymbol.symbol = :symbol AND MONTH(DATE(s.date)) = MONTH(:date) ORDER BY s.date DESC")
    List<Double> findMonthlyClosingPriceBySymbolAndDate(@Param("symbol") String symbol, @Param("date") Date date);

    @Query("SELECT MAX(price), MIN(price), SUM(volume) FROM Stock s WHERE s.stockSymbol.symbol = :symbol AND DATE(s.date) = :date")
    List<Object[]> findDailySummaryBySymbolAndDate(@Param("symbol") String symbol, @Param("date") Date date);

    @Query("SELECT MAX(price), MIN(price), SUM(volume) FROM Stock s WHERE s.stockSymbol.symbol = :symbol AND MONTH(DATE(s.date)) = MONTH(:date)")
    List<Object[]> findMonthlySummaryBySymbolAndDate(@Param("symbol") String symbol, @Param("date") Date date);
}

