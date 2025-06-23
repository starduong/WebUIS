package vn.edu.ptithcm.WebUIS.service;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptithcm.WebUIS.domain.entity.ClassEntity;
import vn.edu.ptithcm.WebUIS.domain.entity.TrainingScore;

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

    /**
     * Gửi email thông báo đánh giá điểm rèn luyện đến sinh viên
     * 
     * @param to            Email người nhận
     * @param trainingScore Điểm rèn luyện
     */
    public void sendNotificationOfTrainingScoreToStudent(String to, TrainingScore trainingScore) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String formattedStartDate = trainingScore.getStartDate().format(formatter);
            String formattedEndDate = trainingScore.getEndDate().format(formatter);

            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject("📌 Thông báo đánh giá điểm rèn luyện - Hệ thống PTITHCM");

            String htmlContent = """
                    <html>
                    <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                        <h2 style="color: #2c3e50;">📢 Thông báo đánh giá điểm rèn luyện</h2>
                        <p>Kính gửi các lớp sinh viên,</p>
                        <p>Học viện cơ sở gửi thông báo hướng dẫn chấm điểm rèn luyện học kỳ
                            <strong>%d</strong> năm học <strong>%s</strong>.</p>
                        <p><strong>Lưu ý:</strong></p>
                        <ul>
                            <li>Thời gian đánh giá: từ <strong>%s</strong> đến <strong>%s</strong></li>
                            <li>Vui lòng truy cập hệ thống để thực hiện đánh giá</li>
                        </ul>
                        <p>Trân trọng,<br/>Hệ thống PTITHCM</p>
                    </body>
                    </html>
                    """.formatted(
                    trainingScore.getSemester().getOrder(),
                    trainingScore.getSemester().getAcademicYear(),
                    formattedStartDate,
                    formattedEndDate);

            helper.setText(htmlContent, true); // true = isHtml

            emailSender.send(mimeMessage);
            log.info("Notification of training score email sent to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send notification of training score email to: {}", to, e);
            throw new RuntimeException("Gửi email thất bại");
        }
    }

    /**
     * Gửi email thông báo đánh giá điểm rèn luyện đến CLASS_COMMITTEE,
     * ACADEMIC_ADVISOR
     * 
     * @param to            Email người nhận
     * @param trainingScore Điểm rèn luyện
     * @param classEntity   Lớp
     * @param numberStudent Số sinh viên đã đánh giá
     */
    public void sendNotificationOfTrainingScoreToClassCommitteeAndAcademicAdvisor(String to,
            TrainingScore trainingScore, ClassEntity classEntity, int numberStudentSended) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String formattedStartDate = trainingScore.getStartDate().format(formatter);
            String formattedEndDate = trainingScore.getEndDate().format(formatter);

            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject("📌 Thông báo đánh giá điểm rèn luyện - Hệ thống PTITHCM");

            String htmlContent = """
                    <html>
                    <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                        <h2 style="color: #2c3e50;">📢 Thông báo đánh giá điểm rèn luyện</h2>
                        <p>Kính gửi Cố vấn học tập và Ban cán sự lớp <strong>%s</strong>,</p>
                        <p>Học viện cơ sở gửi thông báo hướng dẫn chấm điểm rèn luyện học kỳ
                            <strong>%d</strong> năm học <strong>%s</strong>.</p>
                        <p>Lớp <strong>%s</strong> có <strong>%d</strong> sinh viên</p>
                        <p>✅ Số sinh viên đã đánh giá: <strong>%d</strong> sinh viên</p>
                        <p><strong>Lưu ý:</strong></p>
                        <ul>
                            <li>Thời gian đánh giá: từ <strong>%s</strong> đến <strong>%s</strong></li>
                            <li>Vui lòng truy cập hệ thống để thực hiện đánh giá</li>
                        </ul>
                        <p>Trân trọng,<br/>Hệ thống PTITHCM</p>
                    </body>
                    </html>
                    """.formatted(
                    classEntity.getClassId(), // %s
                    trainingScore.getSemester().getOrder(), // %d
                    trainingScore.getSemester().getAcademicYear(), // %s → phải là String
                    classEntity.getClassId(), // %s
                    classEntity.getStudents().size(), // %d
                    numberStudentSended, // %d
                    formattedStartDate, // %s
                    formattedEndDate // %s
            );

            helper.setText(htmlContent, true); // true = isHtml

            emailSender.send(mimeMessage);
            log.info("Notification of training score email sent to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send notification of training score email to: {}", to, e);
            throw new RuntimeException("Gửi email thất bại");
        }
    }

}