/**
 * MIT License
 *
 * Copyright (c) 2020 PChouse - Reflexão Estudos e Sistemas Informáticos, LDA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package pt.pchouse.jvmailer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

/**
 *
 * @author Rebelo
 */
public class Mailer {

    /**
     * The class LOG
     */
    protected static final Logger LOG = LogManager.getLogger();

    /**
     * Log level
     */
    public static final Level logLevel = Level.OFF;

    /**
     * Email from address
     */
    protected InternetAddress from;

    /**
     * To address stack
     */
    protected ArrayList<InternetAddress> to = new ArrayList<>();

    /**
     * Cc address stack
     */
    protected ArrayList<InternetAddress> cc = new ArrayList<>();

    /**
     * Bcc address stack
     */
    protected ArrayList<InternetAddress> bcc = new ArrayList<>();

    /**
     * Start TLS
     */
    protected boolean starttls = true;

    /**
     * Usert SSL
     */
    protected boolean ssl = false;

    /**
     * SMTP user
     */
    protected String user = "";

    /**
     * SMTP password
     */
    protected String password = "";

    /**
     * SMTP server name
     */
    protected String server = null;

    /**
     * Authenticate in the SMTP server
     */
    protected boolean auth = true;

    /**
     * SMTP server port
     */
    protected int port = 465;

    /**
     * Email subject
     */
    protected String subject;

    /**
     * The reply to address
     */
    protected InternetAddress replyTo;

    /**
     * Message body
     */
    protected String body;

    /**
     * Is or not html body
     */
    protected boolean html = true;

    /**
     * Attachment file stack
     */
    protected ArrayList<File> attachments = new ArrayList<>();

    /**
     * Delete or not the attachement files after send
     */
    protected boolean deleteAttachment = false;

    /**
     * Class to send mail
     */
    public Mailer() {
        Configurator.setLevel(getClass().getName(), logLevel);
        LOG.debug(() -> "Starting instance '" + getClass().getName() + "'");
    }

    /**
     * Get To address stack
     *
     * @return
     */
    public ArrayList<InternetAddress> getTo() {
        return to;
    }

    /**
     * Add to aaddress to the stack
     *
     * @param to
     * @throws AddressException
     */
    public void addToAddress(InternetAddress to) throws AddressException {
        to.validate();
        this.to.add(to);
        LOG.debug(() -> "Add to address '" + to.getAddress() + "'");
    }

    /**
     * Get Carbon copy address stack
     *
     * @return
     */
    public ArrayList<InternetAddress> getCc() {
        return cc;
    }

    /**
     * Add carbon copy aaddress to the stack
     *
     * @param cc
     * @throws AddressException
     */
    public void addCcAddress(InternetAddress cc) throws AddressException {
        cc.validate();
        this.cc.add(cc);
        LOG.debug(() -> "Add cc address '" + cc.getAddress() + "'");
    }

    /**
     * Get Blind carbon copy address stack
     *
     * @return
     */
    public ArrayList<InternetAddress> getBcc() {
        return bcc;
    }

    /**
     * Add blind carbon copy aaddress to the stack
     *
     * @param bcc
     * @throws javax.mail.internet.AddressException
     */
    public void addBccAddress(InternetAddress bcc) throws AddressException {
        bcc.validate();
        this.bcc.add(bcc);
        LOG.debug(() -> "Add bcc address '" + bcc.getAddress() + "'");
    }

    /**
     * Uses or not start tls
     *
     * @return
     */
    public boolean isStarttls() {
        return starttls;
    }

    /**
     * Uses or not start tls
     *
     * @param starttls
     */
    public void setStarttls(boolean starttls) {
        this.starttls = starttls;
        LOG.debug(() -> "Set starttls to '" + (starttls ? "true" : "false") + "'");
    }

    /**
     * Uses or not SSL
     *
     * @return
     */
    public boolean isSsl() {
        return ssl;
    }

    /**
     * Uses or not SSL
     *
     * @param ssl
     */
    public void setSsl(boolean ssl) {
        this.ssl = ssl;
        LOG.debug(() -> "Set ssl to '" + (ssl ? "true" : "false") + "'");
    }

    /**
     * Get SMTP user name
     *
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     * Set SMTP user name
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
        LOG.debug(() -> "Set user to '" + user + "'");
    }

    /**
     * Get SMTP password
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set SMTP password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
        LOG.debug(() -> "Set password to '****'");
    }

    /**
     * Needs or not to authenticate in the SMTP server
     *
     * @return
     */
    public boolean isAuth() {
        return auth;
    }

    /**
     * Needs or not to authenticate in the SMTP server
     *
     * @param auth
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
        LOG.debug(() -> "Set auth '" + (auth ? "true" : "false") + "'");
    }

    /**
     * Get SMTP server address or name
     *
     * @return
     */
    public String getServer() {
        return server;
    }

    /**
     * Set SMTP server address or name
     *
     * @param server
     */
    public void setServer(String server) {
        this.server = server;
        LOG.debug(() -> "Set server to '" + server + "'");
    }

    /**
     * Get SMTP port
     *
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     * Set smtp server port
     *
     * @param port
     * @throws pt.pchouse.jvmailer.MailerException
     */
    public void setPort(int port) throws MailerException {
        if (port < 1 || port > 65535) {
            String msg = String.format(
                    "Port number must be between '%s' and '%s'",
                    "1", "65535"
            );
            LOG.debug(() -> msg);
            throw new MailerException(msg);
        }
        this.port = port;
        LOG.debug(() -> "Set port to '" + String.valueOf(port) + "'");
    }

    /**
     * Get email from address
     *
     * @return
     */
    public InternetAddress getFrom() {
        return from;
    }

    /**
     * Set email from address
     *
     * @param from
     */
    public void setFrom(InternetAddress from) {
        this.from = from;
        LOG.debug(() -> "Set from to '" + from.getAddress() + "'");
    }

    /**
     * Get email subject
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Set email subject
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
        LOG.debug(() -> "Set subject to '" + subject + "'");
    }

    /**
     * Get reply to address
     *
     * @return
     */
    public InternetAddress getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(InternetAddress replyTo) {
        this.replyTo = replyTo;
        LOG.debug(() -> "Set reply to adrress '" + replyTo + "'");
    }

    /**
     * Get message body
     *
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     * Set message body
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
        LOG.debug(() -> "Message body setted");
    }

    /**
     * Email body is or not HTML
     *
     * @return
     */
    public boolean isHtml() {
        return html;
    }

    /**
     * Set if email´s body is HTML
     *
     * @param html
     */
    public void setHtml(boolean html) {
        this.html = html;
    }

    /**
     * Get Attachement files stack
     *
     * @return
     */
    public ArrayList<File> getAttachments() {
        return attachments;
    }

    /**
     * Add attachment file to stack
     *
     * @param file
     */
    public void addAttachments(File file) {
        this.attachments.add(file);
    }

    /**
     * Is attachment file to be deleted after send
     *
     * @return
     */
    public boolean isDeleteAttachment() {
        return deleteAttachment;
    }

    /**
     * Set if the attachment file is to be deleted after sended
     *
     * @param deleteAttachment
     */
    public void setDeleteAttachment(boolean deleteAttachment) {
        this.deleteAttachment = deleteAttachment;
        LOG.debug(() -> "Set delete attachment to '" + (deleteAttachment ? "true" : "false") + "'");
    }

    public void send() throws MessagingException, MailerException, IOException {

        if (server == null || server.equals("")) {
            throw new MailerException("No server defined");
        }

        if (from == null) {
            throw new MailerException("No from address defined");
        }

        if (to.isEmpty() && cc.isEmpty() && bcc.isEmpty()) {
            throw new MailerException("No addresses to send defined");
        }

        if (subject == null || subject.equals("")) {
            throw new MailerException("No mail's subject");
        }

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", server);
        properties.put("mail.smtp.port", String.valueOf(port));
        properties.put("mail.smtp.auth", auth ? "true" : "false");
        properties.put("mail.smtp.starttls.enable", starttls ? "true" : "false");

        if (ssl) {
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }
        // creates a new session with an authenticator
        Session session;
        if (auth) {
            session = Session.getInstance(
                    properties,
                    new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
        } else {
            session = Session.getDefaultInstance(properties);
        }
        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(from);

        if (false == to.isEmpty()) {
            msg.setRecipients(
                    Message.RecipientType.TO,
                    to.toArray(new InternetAddress[to.size()]));
        }

        if (false == cc.isEmpty()) {
            msg.setRecipients(
                    Message.RecipientType.CC,
                    cc.toArray(new InternetAddress[cc.size()]));
        }

        if (false == bcc.isEmpty()) {
            msg.setRecipients(
                    Message.RecipientType.BCC,
                    bcc.toArray(new InternetAddress[bcc.size()]));
        }

        msg.setSubject(subject);
        msg.setSentDate(new java.util.Date());

        if (replyTo != null) {
            msg.setReplyTo(new Address[]{replyTo});
        }

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, html ? "text/html" : "text/text");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        for (File file : attachments) {
            MimeBodyPart attachPart = new MimeBodyPart();
            attachPart.attachFile(file.getAbsoluteFile());
            multipart.addBodyPart(attachPart);
        }
        msg.setContent(multipart);

        Transport.send(msg);
        if (isDeleteAttachment()) {
            attachments.forEach((file) -> {
                file.delete();
            });
        }
    }
}
