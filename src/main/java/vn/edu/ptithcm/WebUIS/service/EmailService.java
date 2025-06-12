package vn.edu.ptithcm.WebUIS.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * Gửi email đặt lại mật khẩu
     * 
     * @param to        Email người nhận
     * @param resetLink Đường dẫn đặt lại mật khẩu
     */

    public void sendPasswordResetEmail(String to, String resetLink) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject("🔐 Đặt lại mật khẩu - Hệ thống PTITHCM");

            String htmlContent = "<div style=\"font-family: Arial, sans-serif; padding: 20px;\">" +
                    "<h2 style=\"color: #007BFF;\">Xin chào,</h2>" +
                    "<p>Bạn đã yêu cầu đặt lại mật khẩu. Vui lòng nhấp vào nút bên dưới để đặt lại mật khẩu của bạn:</p>"
                    +
                    "<div style=\"margin: 30px 0;\">" +
                    "<a href=\"" + resetLink
                    + "\" style=\"background-color: #007BFF; color: white; padding: 12px 24px; text-decoration: none; border-radius: 5px; font-weight: bold;\">Đặt lại mật khẩu</a>"
                    +
                    "</div>" +
                    "<p><strong>Lưu ý:</strong> Liên kết này sẽ hết hạn sau <strong>15 phút</strong>.</p>" +
                    "<p>Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.</p>" +
                    "<br><p style=\"color: #888; font-size: 12px;\">Trân trọng,<br>Hệ thống PTITHCM</p>" +
                    "</div>";

            helper.setText(htmlContent, true); // true = isHtml

            emailSender.send(mimeMessage);
            log.info("Password reset email sent to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send password reset email to: {}", to, e);
            throw new RuntimeException("Gửi email thất bại");
        }
    }
}