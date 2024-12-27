package com.example.courseWork.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@Entity
@ToString
public class DynamicRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @Schema(description = "Название продукта", example = "Простой кредит")
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("product_text")
    private String productText;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "query", joinColumns = @JoinColumn(name = "rule_id"))
    @JsonProperty("rule")
    private List<Query> query;


//    private List<UserOf> userOf;
//    private List<ActiveUserOf> activeUserOf;
//    private List<TransactionSumCompare> transactionSumCompare;
//    private List<TransactionSumCompareDepositWithdraw> transactionSumCompareDepositWithdraw;
//    private boolean[] flag;
//
//    public DynamicRules(List<UserOf> userOf, List<ActiveUserOf> activeUserOf, List<TransactionSumCompare> transactionSumCompare, List<TransactionSumCompareDepositWithdraw> transactionSumCompareDepositWithdraw) {
//        this.userOf = userOf;
//        this.activeUserOf = activeUserOf;
//        this.transactionSumCompare = transactionSumCompare;
//        this.transactionSumCompareDepositWithdraw = transactionSumCompareDepositWithdraw;
//        this.flag = new boolean[]{false, false, false, false};
//    }
//
//    public List<UserOf> getUserOf() {
//        return userOf;
//    }
//
//    public void setUserOf(List<UserOf> userOf) {
//        this.userOf = userOf;
//        this.flag[0] = true;
//    }
//
//    public void addUserOf(UserOf userOf) {
//        this.userOf.add(userOf);
//        this.flag[0] = true;
//    }
//
//    public List<ActiveUserOf> getActiveUserOf() {
//        return activeUserOf;
//    }
//
//    public void setActiveUserOf(List<ActiveUserOf> activeUserOf) {
//        this.activeUserOf = activeUserOf;
//        this.flag[1] = true;
//    }
//
//    public void addActiveUserOf(ActiveUserOf activeUserOf) {
//        this.activeUserOf.add(activeUserOf);
//        this.flag[1] = true;
//    }
//
//    public List<TransactionSumCompare> getTransactionSumCompare() {
//        return transactionSumCompare;
//    }
//
//    public void setTransactionSumCompare(TransactionSumCompare transactionSumCompare) {
//        this.transactionSumCompare.add(transactionSumCompare);
//        this.flag[2] = true;
//    }
//
//    public List<TransactionSumCompareDepositWithdraw> getTransactionSumCompareDepositWithdraw() {
//        return transactionSumCompareDepositWithdraw;
//    }
//
//    public void setTransactionSumCompareDepositWithdraw(List<TransactionSumCompareDepositWithdraw> transactionSumCompareDepositWithdraw) {
//        this.transactionSumCompareDepositWithdraw = transactionSumCompareDepositWithdraw;
//        this.flag[3] = true;
//    }
//
//    public void addTransactionSumCompareDepositWithdraw(TransactionSumCompareDepositWithdraw transactionSumCompareDepositWithdraw) {
//        this.transactionSumCompareDepositWithdraw.add(transactionSumCompareDepositWithdraw);
//        this.flag[3] = true;
//    }
//
//    public boolean[] getFlag() {
//        return flag;
//    }
//
//    public void setFlag(boolean[] flag) {
//        this.flag = flag;
//    }
//
//    @Override
//    public String toString() {
//        return "rule:[" +
//                userOf +
//                activeUserOf +
//                transactionSumCompare +
//                transactionSumCompareDepositWithdraw +
//                "\n]";
//    }
}
