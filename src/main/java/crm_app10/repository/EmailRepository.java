package crm_app10.repository;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailRepository {
    private final String email = "ndat04913@gmail.com";
    private final String password = "wpyy zjfi jhhq cdcc";

    public boolean sendTaskNotification(String toEmail, String employeeName, String taskName, String projectName, String deadline) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(email, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email, "Hệ thống CRM"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("🔔 [CRM] Bạn có công việc mới: " + taskName);

            String htmlContent = """
            <!DOCTYPE html>
            <html lang="vi">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f4f7f6; }
                    .container { max-width: 600px; margin: 20px auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 15px rgba(0,0,0,0.05); }
                    .header { background-color: #4A90E2; color: white; padding: 25px; text-align: center; }
                    .header h1 { margin: 0; font-size: 24px; font-weight: 600; }
                    .content { padding: 30px; }
                    .content h2 { color: #333; font-size: 20px; margin-top: 0; }
                    .content p { color: #555; line-height: 1.6; }
                    .task-details { border: 1px solid #e8e8e8; border-radius: 6px; margin: 25px 0; }
                    .detail-item { padding: 15px; display: flex; align-items: center; border-bottom: 1px solid #e8e8e8; }
                    .detail-item:last-child { border-bottom: none; }
                    .detail-item strong { color: #333; width: 120px; flex-shrink: 0; }
                    .detail-item span { color: #555; }
                    .deadline { color: #D0021B; font-weight: bold; }
                    .cta-button { display: block; width: fit-content; margin: 30px auto 10px; background-color: #4A90E2; color: #ffffff; padding: 12px 30px; text-decoration: none; border-radius: 50px; font-weight: bold; text-align: center; }
                    .footer { background-color: #f4f7f6; padding: 20px; text-align: center; font-size: 12px; color: #888; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>📋 Thông Báo Công Việc Mới</h1>
                    </div>
                    <div class="content">
                        <h2>Xin chào %s,</h2>
                        <p>Bạn vừa được giao một công việc mới từ hệ thống CRM. Vui lòng xem chi tiết bên dưới và hoàn thành đúng hạn nhé.</p>
                        
                        <div class="task-details">
                            <div class="detail-item"><strong>🎯 Công việc:</strong> <span><strong>%s</strong></span></div>
                            <div class="detail-item"><strong>📁 Dự án:</strong> <span>%s</span></div>
                            <div class="detail-item"><strong>⏰ Deadline:</strong> <span class="deadline">%s</span></div>
                        </div>
                        
                        <a href="http://localhost:8082/crm_app10/tasks" class="cta-button">🚀 Xem chi tiết công việc</a>
                    </div>
                    <div class="footer">
                        <p>Đây là email tự động, vui lòng không trả lời.</p>
                        <p>&copy; 2025 CRM. All rights reserved.</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(employeeName, taskName, projectName, deadline);

            message.setContent(htmlContent, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("Đã gửi email cho: " + toEmail);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}