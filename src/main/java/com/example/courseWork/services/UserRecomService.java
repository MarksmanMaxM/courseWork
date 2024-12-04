package com.example.courseWork.services;

import com.example.courseWork.enums.Requests;
import com.example.courseWork.models.DynamicRules;
import com.example.courseWork.models.Query;
import com.example.courseWork.models.Recommendations;
import com.example.courseWork.models.UserRecom;
import com.example.courseWork.repositories.RecommendationsRepository;
import com.example.courseWork.repositories.RulesRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.courseWork.enums.Requests.*;

@Service
public class UserRecomService {
    private final RecommendationsRepository recommendationsRepository;
    private final RulesRepository rulesRepository;
    private final RulesService rulesService;


    public UserRecomService(RecommendationsRepository recommendationsRepository, RulesRepository rulesRepository, RulesService rulesService) {
        this.recommendationsRepository = recommendationsRepository;
        this.rulesRepository = rulesRepository;
        this.rulesService = rulesService;
    }

    public List<Recommendations> setRecommendations() {
        List<Recommendations> recommendations = new ArrayList<>(List.of(new Recommendations("Invest 500", UUID.fromString("147f6a0f-3b91-413b-ab99-87f081d60d5a"), "Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка! Воспользуйтесь налоговыми льготами и начните инвестировать с умом. Пополните счет до конца года и получите выгоду в виде вычета на взнос в следующем налоговом периоде. Не упустите возможность разнообразить свой портфель, снизить риски и следить за актуальными рыночными тенденциями. Откройте ИИС сегодня и станьте ближе к финансовой независимости!"),
                new Recommendations("Top Saving", UUID.fromString("59efc529-2fff-41af-baff-90ccd7402925"), "Откройте свою собственную «Копилку» с нашим банком! «Копилка» — это уникальный банковский инструмент, который поможет вам легко и удобно накапливать деньги на важные цели. Больше никаких забытых чеков и потерянных квитанций — всё под контролем! \n" +
                        "Преимущества «Копилки»:\n" +
                        "Накопление средств на конкретные цели. Установите лимит и срок накопления, и банк будет автоматически переводить определенную сумму на ваш счет.\n" +
                        "Прозрачность и контроль. Отслеживайте свои доходы и расходы, контролируйте процесс накопления и корректируйте стратегию при необходимости.\n" +
                        "Безопасность и надежность. Ваши средства находятся под защитой банка, а доступ к ним возможен только через мобильное приложение или интернет-банкинг.\n" +
                        "Начните использовать «Копилку» уже сегодня и станьте ближе к своим финансовым целям!\n"),
                new Recommendations("Простой кредит", UUID.fromString("ab138afb-f3ba-4a93-b74f-0fcee86d447f"), "Откройте мир выгодных кредитов с нами!\n" +
                        "Ищете способ быстро и без лишних хлопот получить нужную сумму? Тогда наш выгодный кредит — именно то, что вам нужно! Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный подход к каждому клиенту.\n" +
                        "Почему выбирают нас:\n" +
                        "Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому процесс рассмотрения заявки занимает всего несколько часов.\n" +
                        "Удобное оформление. Подать заявку на кредит можно онлайн на нашем сайте или в мобильном приложении.\n" +
                        "Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные цели: покупку недвижимости, автомобиля, образование, лечение и многое другое.\n" +
                        "Не упустите возможность воспользоваться выгодными условиями кредитования от нашей компании!\n"),
                new Recommendations("Рекомендуемых продуктов нет", UUID.fromString("00000000-0000-0000-0000-000000000000"), "---------\n")));
        return recommendations;

    }


    public UserRecom getRecom(UUID id) {
        int rec = recommendationsRepository.getRecommendation(id);
        List<Recommendations> recommendations = setRecommendations();
        UserRecom userRecom = null;
        List<Recommendations> recomed = new ArrayList<>();
        if (rec == 1) {
            recomed.add(recommendations.get(0));
            UserRecom userRecom1 = new UserRecom(id, recomed);
            userRecom = userRecom1;

        }

        if (rec == 2) {
            recomed.add(recommendations.get(1));
            UserRecom userRecom1 = new UserRecom(id, recomed);
            userRecom = userRecom1;
        }
        if (rec == 3) {
            recomed.add(recommendations.get(2));
            UserRecom userRecom1 = new UserRecom(id, recomed);
            userRecom = userRecom1;
        }
        if (rec == 0) {
            recomed.add(recommendations.get(3));
            UserRecom userRecom1 = new UserRecom(id, recomed);
            userRecom = userRecom1;
        }
        return userRecom;
    }

    public UserRecom getAdditionaltRecommendation(UUID id) {
        String debit = "DEBIT";
        String invest = "INVEST";
        String saving = "SAVING";
        String deposit = "DEPOSIT";
        String withdraw = "WITHDRAW";
        String credit = "CREDIT";

        final String userOf = "USER_OF";
        final String activeUserOf = "ACTIVE_USER_OF";
        final String transactionSumCompare = "TRANSACTION_SUM_COMPARE";
        final String transactionSumCompareDepositWithdraw = "TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW";


        Integer debitCount = 0;
        Integer investCount = 0;
        Integer savingAmount = 0;
        Integer debitDepositAmount = 0;
        Integer debitWithdrawAmount = 0;
        Integer creditCount = 0;

        UserRecom userRecom = new UserRecom(id, null);
//        userRecom.setUserId(id);
        List<DynamicRules> allRules = rulesService.getAll();

        List<Integer> index = new ArrayList<>();

        for (int i = 0; i < allRules.size(); i++) {
            List<Query> query = allRules.get(i).getQuery();
            int result = 0;
            for (int j = 0; j < query.size(); j++) {

                String sqlText;
                boolean negate;


                switch (query.get(j).getQuery()) {

                    case USER_OF:
                        sqlText = setSql(USER_OF, query.get(j).getArguments(), id);
                        negate = query.get(j).isNegate();
                        if (recommendationsRepository.testRecomendation(sqlText, id, negate, USER_OF)) {
                            result++;
                        }
                        break;

                    case ACTIVE_USER_OF:
                        sqlText = setSql(ACTIVE_USER_OF, query.get(j).getArguments(), id);
                        negate = query.get(j).isNegate();
                        if (recommendationsRepository.testRecomendation(sqlText, id, negate, ACTIVE_USER_OF)) {
                            result++;
                        }
                        break;

                    case TRANSACTION_SUM_COMPARE:
                        sqlText = setSql(TRANSACTION_SUM_COMPARE, query.get(j).getArguments(), id);
                        negate = query.get(j).isNegate();
                        Integer sum = recommendationsRepository.getSqlResponse(sqlText, id);
                        if (sum == null) {
                            sum = 0;
                        }
                        int flag = 0;
                        switch (query.get(j).getArguments().get(2)) {
                            case "<":
                                if (Integer.parseInt(query.get(j).getArguments().get(3)) < sum) {
                                    flag = 1;
                                }
                                break;
                            case ">":
                                if (Integer.parseInt(query.get(j).getArguments().get(3)) > sum) {
                                    flag = 1;
                                }
                                break;
                            case "=":
                                if (Integer.parseInt(query.get(j).getArguments().get(3)) == sum) {
                                    flag = 1;
                                }
                                break;
                            case "<=":
                                if (Integer.parseInt(query.get(j).getArguments().get(3)) <= sum) {
                                    flag = 1;
                                }
                                break;
                            case ">=":
                                if (Integer.parseInt(query.get(j).getArguments().get(3)) >= sum) {
                                    flag = 1;
                                }
                                break;
                        }

                        if (flag == 1 && !negate) {
                            result++;
                            flag = 0;
                        }
                        break;

                    case TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW:
                        List<String> array = new ArrayList<>(List.of(query.get(j).getArguments().get(0), "DEBIT"));
                        sqlText = setSql(TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW, array, id);
                        negate = query.get(j).isNegate();
                        sum = recommendationsRepository.getSqlResponse(sqlText, id);
                        array = new ArrayList<>(List.of(query.get(j).getArguments().get(0), "WITHDRAW"));

                        sqlText = setSql(TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW, array, id);
                        Integer sum1 = recommendationsRepository.getSqlResponse(sqlText, id);

                        if (sum == null) {
                            sum = 0;
                        }
                        if (sum1 == null) {
                            sum1 = 0;
                        }

                        int flag1 = 0;
                        switch (query.get(j).getArguments().get(1)) {
                            case "<":
                                if (sum < sum1) {
                                    flag1 = 1;
                                }
                                break;
                            case ">":
                                if (sum > sum1) {
                                    flag1 = 1;
                                }
                                break;
                            case "=":
                                if (sum == sum1) {
                                    flag1 = 1;
                                }
                                break;
                            case "<=":
                                if (sum <= sum1) {
                                    flag1 = 1;
                                }
                                break;
                            case ">=":
                                if (sum >= sum1) {
                                    flag1 = 1;
                                }
                                break;
                        }

                        if (flag1 == 1 && !negate) {
                            result++;
                            flag1 = 0;
                        }
                        break;


                }


            }
            if (result == query.size()) {
                index.add(i);
            }

        }
        List<DynamicRules> recRules = new ArrayList<>();
        List<Recommendations> recs = new ArrayList<>();
        int index_rec = 0;

        for (int i = 0; i < allRules.size(); i++) {
            for (int j = 0; j < index.size(); j++) {
                if (i == index.get(j)) {
                    recs.add(index_rec, new Recommendations(allRules.get(i).getProductName(),
                            UUID.fromString(allRules.get(i).getProductId()),
                            allRules.get(i).getProductText()));
                    index_rec++;
                    recRules.add(allRules.get(i));
                }
            }
        }

        userRecom.setRecomed(recs);

        return userRecom;


    }

    public String setSql(Requests type, List<String> arg, UUID id) {

        String sqlText = null;


        if (type.equals(USER_OF) || type.equals(ACTIVE_USER_OF)) {
            sqlText = "SELECT COUNT(*) FROM transactions t INNER JOIN products p ON t.product_id =" +
                    " p.id WHERE t.user_id = ? AND p.type = '" +
                    arg.get(0) + "'";
        }

        if (type.equals(TRANSACTION_SUM_COMPARE)) {
            sqlText = "SELECT SUM(t.amount) FROM transactions t INNER JOIN products p ON" +
                    " t.product_id = p.id WHERE t.user_id = ? AND p.type = '" +
                    arg.get(0) + "' AND t.type = '" + arg.get(1) + "'";
        }

        if (type.equals(TRANSACTION_SUM_COMPARE_DEPOSIT_WITHDRAW)) {
            sqlText = "SELECT SUM(t.amount) FROM transactions t INNER JOIN products p ON " +
                    "t.product_id = p.id WHERE t.user_id = ? AND p.type = '" +
                    arg.get(0) + "' AND t.type = '" + arg.get(1) + "'";

        }

        System.out.println(sqlText);

        return sqlText;
    }


}
