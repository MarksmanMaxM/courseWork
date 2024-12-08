package com.example.courseWork.listener;

import com.example.courseWork.models.Recommendations;
import com.example.courseWork.models.UserRecom;
import com.example.courseWork.repositories.RecommendationsRepository;
import com.example.courseWork.services.UserRecomService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private RecommendationsRepository recommendationsRepository;
    private UserRecomService userRecomService;
    private UserRecom user;
    private long chatId;

    public TelegramBotUpdatesListener(RecommendationsRepository recommendationsRepository, UserRecomService userRecomService) {
        this.recommendationsRepository = recommendationsRepository;
        this.userRecomService = userRecomService;

    }

    @Autowired
    private TelegramBot telegramBot = new TelegramBot("BOT_TOKEN");

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {

        String messageText = "Hello!";

        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            chatId = update.message().chat().id();
            SendMessage message = new SendMessage(chatId, messageText);


            if (update.message().text().equals("/start")) {


                SendResponse response = telegramBot.execute(message);
            }


            String[] parts = update.message().text().split(" ", 2);
            String userName;
            UUID id = null;
            if (parts[0].equals("/recommend")) {
                userName = parts[1];
                id = recommendationsRepository.getUserIdByUsername(userName);
                System.out.println(userName);
                System.out.println(id);
            }



            if (id != null) {

                UserRecom userAdd;
                List<Recommendations> recs;
                user = userRecomService.getRecom(id);
                userAdd = userRecomService.getAdditionaltRecommendation(id);
                recs = userAdd.getRecomed();
                recs.addAll(user.getRecomed());
                user.setRecomed(recs);

                String messageTextResp = user.toString();
                SendMessage messageResp = new SendMessage(chatId, messageTextResp);
                SendResponse response = telegramBot.execute(messageResp);
                System.out.println("Отправка");
            }

//            System.out.println(user.toString());


        });


        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {

        String messageText = user.toString();
        SendMessage message = new SendMessage(chatId, messageText);
        SendResponse response = telegramBot.execute(message);
        System.out.println("Отправка");

    }

}



