package com.company.notificationservice.service;

import com.company.notificationservice.client.AccountServiceClient;
import com.company.notificationservice.client.UserServiceClient;
import com.company.notificationservice.dto.AccountDto;
import com.company.notificationservice.dto.TransactionDto;
import com.company.notificationservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Value("${spring.mail.username}")
    private String username;

    private final UserServiceClient userServiceClient;
    private final AccountServiceClient accountServiceClient;
    private final JavaMailSender mailSender;

    public NotificationService(UserServiceClient userServiceClient, AccountServiceClient accountServiceClient, JavaMailSender mailSender) {
        this.userServiceClient = userServiceClient;
        this.accountServiceClient = accountServiceClient;
        this.mailSender = mailSender;
    }

    public void sendMessage(TransactionDto transactionDto) {
        AccountDto fromAccount = accountServiceClient.getAccountById(transactionDto.fromAccountId()).getBody();
        AccountDto toAccount = accountServiceClient.getAccountById(transactionDto.toAccountId()).getBody();

        UserDto fromUser = userServiceClient.getUserById(fromAccount.userId()).getBody();
        UserDto toUser = userServiceClient.getUserById(toAccount.userId()).getBody();

        String subject = "Transaction Notification";
        String fromMessage = String.format("Dear %s, your transaction of amount %s has been %s.",
                fromUser.firstName(), transactionDto.amount(), transactionDto.status().toString().toLowerCase());
        String toMessage = String.format("Dear %s, you have received a transaction of amount %s which has been %s.",
                toUser.firstName(), transactionDto.amount(), transactionDto.status().toString().toLowerCase());

        sendEmail(fromUser.email(), subject, fromMessage);
        sendEmail(toUser.email(), subject, toMessage);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(username);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

}
