package com.hypocrisy.maven.hypocrisypassport.mq.listener;


import com.hypocrisy.maven.hypocrisypassport.mq.custom.VerityMQName;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import utils.RedisKeyRule;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/17 20:38
 * @Description:
 */
@Component
@RabbitListener(queues = VerityMQName.EMAIL_QUEUE)
public class EmailQueueListener {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${passport.address}")
    private String passportAddress;

    @Value("${spring.mail.username}")
    private String fromMail;

    @RabbitHandler
    public void sendEmailHandler(String email) throws IllegalAccessException, MessagingException {
        final String code = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(RedisKeyRule.verityKeyRule(email, null), code, RedisKeyRule.expire, TimeUnit.SECONDS);
        this.sendEmail(email, code);
    }

    public void sendEmail(String email, String code) throws  MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setSubject("京东邮箱注册验证");
        mimeMessageHelper.setText("<div>尊敬的用户，您好：</div>" +
                "<div>您正在京东进行注册操作，" + this.getVerifyUrl(email, code) + "即可注册成功</div>" +
                "<div>本验证码10分钟内有效。如非本人操作，请忽略该邮件。</div>\n" +
                "</div>（这是一封自动发送的邮件，请不要直接回复）</div>", true);
        mimeMessageHelper.setFrom(fromMail);
        mimeMessageHelper.setTo(email);

        mailSender.send(mimeMessage);
    }
    public String getVerifyUrl(String email, String code) {
        String url = this.passportAddress + "?" + "email=" + email + "&" +  "verifyCode=" + code;
        String a = "<a href=" + url + ">点击此连接</a>";
        return  a;
    }
}
