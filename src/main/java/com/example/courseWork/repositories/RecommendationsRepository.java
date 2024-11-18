package com.example.courseWork.repositories;
import com.example.courseWork.models.UserRecom;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public class RecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getRandomTransactionAmount(UUID user){
        Integer result = jdbcTemplate.queryForObject(
                "SELECT amount FROM transactions t WHERE t.user_id = ? LIMIT 1",
                Integer.class,
                user);
        return result != null ? result : 0;
    }

    public int getRecommendation(UUID id){
        UserRecom userRecom;
        String name;
        int idRec;
        String text;
        String deposit = "DEPOSIT";
        String invest = "INVEST";
        String saving = "SAVING";


        Integer debitCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM transactions t INNER JOIN products p ON t.product_id = p.id WHERE t.user_id = ? AND t.type = ?",
                Integer.class, String.class,
                id, deposit);

        Integer investCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM transactions t INNER JOIN products p ON t.product_id = p.id WHERE t.user_id = ? AND t.type = ?",
                Integer.class, String.class,
                id, invest);

        Integer savingCount = jdbcTemplate.queryForObject(
                "SELECT SUM(t.amount) FROM transactions t INNER JOIN products p ON t.product_id = p.id WHERE t.user_id = ? AND t.type = ?",
                Integer.class, String.class,
                id, saving);

        if(debitCount >= 1 && investCount < 1 && savingCount > 1000)
            return 1;

        return 0;





    }
}
