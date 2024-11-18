package com.example.courseWork.services;

import com.example.courseWork.models.Recommendations;
import com.example.courseWork.models.UserRecom;
import com.example.courseWork.repositories.RecommendationsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserRecomService {
    private final RecommendationsRepository recommendationsRepository;


    public UserRecomService(RecommendationsRepository recommendationsRepository) {
        this.recommendationsRepository = recommendationsRepository;
    }

    public List<Recommendations> setRecommendations() {
        List<Recommendations> recommendations = new ArrayList<>(List.of(new Recommendations("Invest 500", "147f6a0f-3b91-413b-ab99-87f081d60d5a", "Откройте свой путь к успеху с индивидуальным инвестиционным счетом (ИИС) от нашего банка! Воспользуйтесь налоговыми льготами и начните инвестировать с умом. Пополните счет до конца года и получите выгоду в виде вычета на взнос в следующем налоговом периоде. Не упустите возможность разнообразить свой портфель, снизить риски и следить за актуальными рыночными тенденциями. Откройте ИИС сегодня и станьте ближе к финансовой независимости!"),
                new Recommendations("Top Saving", "59efc529-2fff-41af-baff-90ccd7402925", "Откройте свою собственную «Копилку» с нашим банком! «Копилка» — это уникальный банковский инструмент, который поможет вам легко и удобно накапливать деньги на важные цели. Больше никаких забытых чеков и потерянных квитанций — всё под контролем! \n" +
                        "Преимущества «Копилки»:\n" +
                        "Накопление средств на конкретные цели. Установите лимит и срок накопления, и банк будет автоматически переводить определенную сумму на ваш счет.\n" +
                        "Прозрачность и контроль. Отслеживайте свои доходы и расходы, контролируйте процесс накопления и корректируйте стратегию при необходимости.\n" +
                        "Безопасность и надежность. Ваши средства находятся под защитой банка, а доступ к ним возможен только через мобильное приложение или интернет-банкинг.\n" +
                        "Начните использовать «Копилку» уже сегодня и станьте ближе к своим финансовым целям!\n"),
                new Recommendations("Простой кредит", "ab138afb-f3ba-4a93-b74f-0fcee86d447f", "Откройте мир выгодных кредитов с нами!\n" +
                        "Ищете способ быстро и без лишних хлопот получить нужную сумму? Тогда наш выгодный кредит — именно то, что вам нужно! Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный подход к каждому клиенту.\n" +
                        "Почему выбирают нас:\n" +
                        "Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому процесс рассмотрения заявки занимает всего несколько часов.\n" +
                        "Удобное оформление. Подать заявку на кредит можно онлайн на нашем сайте или в мобильном приложении.\n" +
                        "Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные цели: покупку недвижимости, автомобиля, образование, лечение и многое другое.\n" +
                        "Не упустите возможность воспользоваться выгодными условиями кредитования от нашей компании!\n")));
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

        return userRecom;

    }

}
