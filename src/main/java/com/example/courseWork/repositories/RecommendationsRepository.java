package com.example.courseWork.repositories;

import com.example.courseWork.enums.Requests;
import com.example.courseWork.models.UserRecom;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public class RecommendationsRepository {
    private final JdbcTemplate jdbcTemplate;

    public RecommendationsRepository(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getRandomTransactionAmount(UUID user) {
        Integer result = jdbcTemplate.queryForObject(
                "SELECT amount FROM transactions t WHERE t.user_id = ? LIMIT 1",
                Integer.class,
                user);
        return result != null ? result : 0;
    }

    public int getRecommendation(UUID id) {

        String debit = "DEBIT";
        String invest = "INVEST";
        String saving = "SAVING";
        String deposit = "DEPOSIT";
        String withdraw = "WITHDRAW";
        String credit = "CREDIT";

        Requests requests;

        Integer debitCount = 0;
        Integer investCount = 0;
        Integer savingAmount = 0;
        Integer debitDepositAmount = 0;
        Integer debitWithdrawAmount = 0;
        Integer creditCount = 0;

        List<Boolean> flag = new ArrayList<>();

        debitCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM transactions t INNER JOIN products p ON t.product_id = p.id WHERE t.user_id = ? AND p.type = ? AND t.type = ?",
                Integer.class, id, debit, deposit);


        investCount = jdbcTemplate. queryForObject(
                "SELECT COUNT(*) FROM transactions t INNER JOIN products p ON t.product_id = p.id WHERE t.user_id = ? AND p.type = ?",
                Integer.class,
                id, invest);

        savingAmount = jdbcTemplate.queryForObject(
                "SELECT SUM(t.amount) FROM transactions t INNER JOIN products p ON t.product_id = p.id WHERE t.user_id = ? AND p.type = ? AND t.type = ?",
                Integer.class,
                id, saving, deposit);

        debitDepositAmount = jdbcTemplate.queryForObject(
                "SELECT SUM(t.amount) FROM transactions t INNER JOIN products p ON t.product_id = p.id WHERE t.user_id = ? AND p.type = ? AND t.type = ?",
                Integer.class,
                id, debit, deposit);

        debitWithdrawAmount = jdbcTemplate.queryForObject(
                "SELECT SUM(t.amount) FROM transactions t INNER JOIN products p ON t.product_id = p.id WHERE t.user_id = ? AND p.type = ? AND t.type = ?",
                Integer.class,
                id, debit, withdraw);

        creditCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM transactions t INNER JOIN products p ON t.product_id = p.id WHERE t.user_id = ? AND p.type = ? AND t.type = ?",
                Integer.class, id, debit, credit);

        if (savingAmount == null) {
            savingAmount = 0;
        }

        if (debitDepositAmount == null) {
            debitDepositAmount = 0;
        }

        if (debitWithdrawAmount == null) {
            debitWithdrawAmount = 0;
        }

        System.out.println(debitCount.toString() + " " + investCount.toString() + " " + savingAmount.toString() + " " + debitDepositAmount.toString() + " " + debitWithdrawAmount.toString() + '\n');

        if (creditCount < 1 && debitDepositAmount > debitWithdrawAmount && debitWithdrawAmount > 100000)
            return 3;

        if (debitCount >= 1 && (savingAmount >= 50000 || debitDepositAmount >= 50000) && debitDepositAmount > debitWithdrawAmount)
            return 2;

        if (debitCount >= 1 && investCount < 1 && savingAmount > 1000)
            return 1;


        return 0;
    }

    public boolean testRecomendation(String sqlText, UUID user, boolean negate, Requests type) {
        Integer count = 0;

/*        List<String> reqType = List.of();
        reqType.add("USER_OF");
        reqType.add("ACTIVE_USER_OF");*/
/*        reqType.add("TRANSACTION_SUM_COMPARE");
        reqType.add("TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW");*/

        if (type.equals(Requests.USER_OF)) {
            count = jdbcTemplate.queryForObject(
                    sqlText,
                    Integer.class, user.toString());
            if (!negate) {
                if (count > 0) {
                    return true;
                } else return false;
            } else {
                if (count > 0) {
                    return false;
                } else return true;
            }
        }

        if (type.equals(Requests.ACTIVE_USER_OF)) {
            count = jdbcTemplate.queryForObject(
                    sqlText,
                    Integer.class, user.toString());
            if (!negate) {
                if (count >= 5) {
                    return true;
                } else return false;
            } else {
                if (count >= 5) {
                    return false;
                } else return true;
            }
        }


        return false;


/*        if (reqType.get(2).equals(type)) {
            count = jdbcTemplate.queryForObject(
                    sqlText,
                    Integer.class);
            if (negate) {
                if (count >= 5) {
                    return true;
                } else return false;
            } else {
                if (count >= 5) {
                    return false;
                } else return true;
            }
        }*/
    }

    public Integer getSqlResponse(String sqlText, UUID user) {
        return jdbcTemplate.queryForObject(sqlText, Integer.class, user.toString());
    }

    public UUID getUserIdByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT users.id FROM users " +
                "WHERE users.username = ?", UUID.class, username);
    }



}
