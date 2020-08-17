package com.dev_incubator.dits.service.interfaces;

public interface MailService {

    void sendEmail(String email, String title, String msg);

    String getRegistrationMessage(String addresseeFirstName, String addresseeLastName, String login, String password);

    String getUnblockMessage(String addresseeFirstName, String addresseeLastName);

    String getBlockMessage(String addresseeFirstName, String addresseeLastName);
}
