package pro.sky.SpringBootTelegram.service;

import org.springframework.stereotype.Service;
import pro.sky.SpringBootTelegram.model.NotificationTask;
import pro.sky.SpringBootTelegram.model.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationTaskService {
    private NotificationTaskRepository repository;

    public NotificationTaskService(NotificationTaskRepository repository) {
        this.repository = repository;
    }

    public void saveNotificationTask(Long chatId, String message, LocalDateTime dateTime) {
        repository.save(new NotificationTask(chatId, message, dateTime));
    }

    public List<NotificationTask> getMessagesForSend(LocalDateTime dateTime) {
        return repository.findByDateTime(dateTime);
    }
}
