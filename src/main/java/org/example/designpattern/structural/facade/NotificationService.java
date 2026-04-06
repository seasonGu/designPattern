package org.example.designpattern.structural.facade;

/**
 * 子系统④ —— 通知服务
 */
public class NotificationService {

    public void sendSms(String phone, String message) {
        System.out.printf("    [通知] 发送短信到 %s: %s%n", phone, message);
    }

    public void sendEmail(String email, String subject, String body) {
        System.out.printf("    [通知] 发送邮件到 %s: [%s] %s%n", email, subject, body);
    }
}
