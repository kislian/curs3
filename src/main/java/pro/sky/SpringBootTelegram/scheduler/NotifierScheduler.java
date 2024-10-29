package pro.sky.SpringBootTelegram.scheduler;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.SpringBootTelegram.model.NotificationTask;
import pro.sky.SpringBootTelegram.service.NotificationTaskService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class NotifierScheduler {
    private final NotificationTaskService notificationTaskService;
    private final TelegramBot telegramBot;
    private final Logger logger = LoggerFactory.getLogger(NotifierScheduler.class);

    public NotifierScheduler(NotificationTaskService notificationTaskService,
                             TelegramBot telegramBot) {
        this.notificationTaskService = notificationTaskService;
        this.telegramBot = telegramBot;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void sendMessages() {
        LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<NotificationTask> list = notificationTaskService.getMessagesForSend(dateTime);

        logger.info("Found {} message for send", list.size());

        list.stream().forEach(task -> {
            SendMessage message = new SendMessage(task.getChatId(), task.getMessage());

            SendResponse response = telegramBot.execute(message);
            if (!response.isOk()) {
                logger.error("Can't send message, errorCode {}", response.errorCode());
            } else {
                logger.info("Message \"{}\" was sent", task.getMessage());
            }
        });
    }
}
