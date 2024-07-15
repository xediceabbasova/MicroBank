package com.company.notificationservice.kafka;

import com.company.notificationservice.dto.TransactionDto;
import com.company.notificationservice.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final NotificationService notificationService;

    public KafkaConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "transaction-topic", groupId = "notification-group")
    public void consume(String message) {
        logger.info("Kafkadan gelen mesaj : {}", message);

        TransactionDto transactionDto = convertMessageToOrderDto(message);
        notificationService.sendMessage(transactionDto);
    }

    private TransactionDto convertMessageToOrderDto(String message) {
        try {
            return objectMapper.readValue(message, TransactionDto.class);
        } catch (JsonProcessingException e) {
            logger.error("TransactionDto-ya donusturme xetasi");
            throw new RuntimeException(e);
        }
    }

}
