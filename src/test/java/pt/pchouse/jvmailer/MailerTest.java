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
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rebelo
 */
public class MailerTest {

    public MailerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of ToCcBccStack method, of class Mailer.
     */
    @Test
    public void testToCcBccStack() throws Exception {
        Mailer mailer = new Mailer();
        int nMax = 9;
        String addrpattern = "%s_teste_%s@teste.teste";
        for (int n = 0; n <= nMax; n++) {
            mailer.addToAddress(new InternetAddress(String.format(addrpattern, "TO", String.valueOf(n))));
            mailer.addCcAddress(new InternetAddress(String.format(addrpattern, "CC", String.valueOf(n))));
            mailer.addBccAddress(new InternetAddress(String.format(addrpattern, "BCC", String.valueOf(n))));
        }

        for (int n = 0; n <= nMax; n++) {
            assertTrue(mailer.getTo().get(n).getAddress().equals(String.format(addrpattern, "TO", String.valueOf(n))));
            assertTrue(mailer.getCc().get(n).getAddress().equals(String.format(addrpattern, "CC", String.valueOf(n))));
            assertTrue(mailer.getBcc().get(n).getAddress().equals(String.format(addrpattern, "BCC", String.valueOf(n))));
        }
    }

    /**
     * Test of Starttls method, of class Mailer.
     */
    @Test
    public void testStarttls() {
        System.out.println("isStarttls");
        Mailer mailer = new Mailer();
        assertTrue(mailer.isStarttls());
        mailer.setStarttls(false);
        assertFalse(mailer.isStarttls());
        mailer.setStarttls(true);
        assertTrue(mailer.isStarttls());
    }

    /**
     * Test of Ssl method, of class Mailer.
     */
    @Test
    public void testIsSsl() {
        System.out.println("isSsl");
        Mailer mailer = new Mailer();
        assertFalse(mailer.isSsl());
        mailer.setSsl(true);
        assertTrue(mailer.isSsl());
        mailer.setSsl(false);
        assertFalse(mailer.isSsl());
    }

    /**
     * Test of setGetUser method, of class Mailer.
     */
    @Test
    public void testSetGetUser() {
        System.out.println("getUser");
        Mailer mailer = new Mailer();
        String user = "smtp user";
        mailer.setUser(user);
        assertSame(user, mailer.getUser());
    }

    /**
     * Test of SetGetPassword method, of class Mailer.
     */
    @Test
    public void testGetPassword() {
        System.out.println("Password");
        Mailer mailer = new Mailer();
        String password = "pwd";
        mailer.setPassword(password);
        assertSame(password, mailer.getPassword());
    }

    /**
     * Test of SetGetAuth method, of class Mailer.
     */
    @Test
    public void testSetGetAuth() {
        System.out.println("setAuth");
        Mailer mailer = new Mailer();
        assertTrue(mailer.isAuth());
        mailer.setAuth(false);
        assertFalse(mailer.isAuth());
        mailer.setAuth(true);
        assertTrue(mailer.isAuth());
    }

    /**
     * Test of SetGetServer method, of class Mailer.
     */
    @Test
    public void testSetGetServer() {
        System.out.println("getServer");
        Mailer mailer = new Mailer();
        String server = "mail.teste.teste";
        mailer.setServer(server);
        assertSame(server, mailer.getServer());
    }

    /**
     * Test of SetGetPort method, of class Mailer.
     */
    @Test
    public void testSetGetPort() throws MailerException {
        System.out.println("getPort");
        Mailer mailer = new Mailer();
        assertEquals(465, mailer.getPort());
        mailer.setPort(25);
        assertEquals(25, mailer.getPort());
        mailer.setPort(587);
        assertEquals(587, mailer.getPort());

        try {
            mailer.setPort(0);
            fail("When set port number to less than 1 should throw MailerException");
        } catch (Exception e) {
            assertTrue(e instanceof MailerException);
        }
        try {
            mailer.setPort(0);
            fail("When set port number to above 65535 should throw MailerException");
        } catch (Exception e) {
            assertTrue(e instanceof MailerException);
        }

    }

    /**
     * Test of SetGetFrom method, of class Mailer.
     */
    @Test
    public void testSetGetFrom() throws AddressException {
        System.out.println("getFrom");
        Mailer mailer = new Mailer();
        InternetAddress from = new InternetAddress("from@teste.teste");
        mailer.setFrom(from);
        assertSame(from, mailer.getFrom());
    }

    /**
     * Test of SetGetSubject method, of class Mailer.
     */
    @Test
    public void testSetGetSubject() {
        System.out.println("getSubject");
        Mailer mailer = new Mailer();
        String subject = "mail subject";
        mailer.setSubject(subject);
        assertSame(subject, mailer.getSubject());
    }

    /**
     * Test of SetGetReplyTo method, of class Mailer.
     */
    @Test
    public void testSetGetReplyTo() throws AddressException {
        System.out.println("getReplyTo");
        Mailer mailer = new Mailer();
        InternetAddress reply = new InternetAddress("reply@teste.teste");
        mailer.setReplyTo(reply);
        assertSame(reply, mailer.getReplyTo());
    }

    /**
     * Test of SetGetBody method, of class Mailer.
     */
    @Test
    public void testSetGetBody() {
        System.out.println("getBody");
        Mailer mailer = new Mailer();
        String body = "mail body";
        mailer.setBody(body);
        assertSame(body, mailer.getBody());
    }

    /**
     * Test of isHtml method, of class Mailer.
     */
    @Test
    public void testIsHtml() {
        System.out.println("isHtml");
        Mailer mailer = new Mailer();
        assertTrue(mailer.isHtml());
        mailer.setHtml(false);
        assertFalse(mailer.isHtml());
        mailer.setHtml(true);
        assertTrue(mailer.isHtml());
    }

    /**
     * Test of getAttachments method, of class Mailer.
     */
    @Test
    public void testSetGetAttachments() {
        System.out.println("getAttachments");
        Mailer mailer = new Mailer();
        assertSame(0, mailer.getAttachments().size());
        int nMax = 9;
        String pattern = "path_%s";
        for (int n = 0; n < nMax; n++) {
            mailer.addAttachments(new File(String.format(pattern, n)));
            assertTrue(
                    mailer.getAttachments()
                            .get(n).getName()
                            .equals(String.format(pattern, n))
            );
        }
    }

    /**
     * Test of isDeleteAttachment method, of class Mailer.
     */
    @Test
    public void testIsDeleteAttachment() {
        System.out.println("isDeleteAttachment");
        Mailer mailer = new Mailer();
        assertFalse(mailer.isDeleteAttachment());
        mailer.setDeleteAttachment(true);
        assertTrue(mailer.isDeleteAttachment());
        mailer.setDeleteAttachment(false);
        assertFalse(mailer.isDeleteAttachment());
    }

    /**
     * Test of send method, of class Mailer.
     */
    @Test
    public void testSend() throws Exception {
        System.out.println("send");
        ClassLoader classLoader = getClass().getClassLoader();
        File resDir = new File(classLoader.getResource("./").getFile());
        File oriPdf = new File(
                resDir.getAbsolutePath().concat("/").concat("TEST_PCHOUSE_JVMAILER.pdf")
        );
        File attach = new File(
                resDir.getAbsolutePath().concat("/").concat("TEST_PCHOUSE.pdf")
        );
        File[] dbs = resDir.listFiles(
                (File dir, String name) -> name.matches("\\w+\\.properties$")
        );

        for (File propFile : dbs) {
            try {
                java.util.Properties prop = new java.util.Properties();
                prop.load(new FileInputStream(propFile));

                if (prop.getProperty("enable", "0").equals("1") == false) {
                    continue;
                }

                Mailer mailer = new Mailer();
                mailer.setAuth(true);
                mailer.setUser(prop.getProperty("user"));
                mailer.setPassword(prop.getProperty("password"));
                mailer.setFrom(new InternetAddress(prop.getProperty("from")));
                mailer.addToAddress(new InternetAddress(prop.getProperty("to")));
                mailer.addCcAddress(new InternetAddress(prop.getProperty("cc")));
                mailer.addBccAddress(new InternetAddress(prop.getProperty("bcc")));
                mailer.setReplyTo(new InternetAddress(prop.getProperty("reply-to")));
                mailer.setServer(prop.getProperty("server"));
                mailer.setPort(Integer.valueOf(prop.getProperty("port")));
                mailer.setStarttls(prop.getProperty("starttls", "0").equals("1"));
                mailer.setSsl(prop.getProperty("ssl", "0").equals("1"));
                mailer.setSubject(prop.getProperty("subject"));
                mailer.setBody(
                        "<p>Test of email with attachment<p>"
                        +"<p>" + (new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z").format(new Date())) + "<p>"
                );
                mailer.setDeleteAttachment(prop.getProperty("deleteattachment", "0").equals("1"));
                if (attach.exists()) {
                    java.nio.file.Files.delete(
                            Paths.get(attach.getAbsolutePath())
                    );
                }

                java.nio.file.Files.copy(
                            Paths.get(oriPdf.getAbsolutePath()),
                            Paths.get(attach.getAbsolutePath())
                    );
                
                for(int x = 0; x < Integer.valueOf(prop.getProperty("attachment")); x++) {
                    mailer.addAttachments(attach);
                }
                mailer.send();

                if(mailer.deleteAttachment && attach.exists()){
                    fail("Attachment was not deleted");
                }
                
            } catch (Exception e) {
                fail(String.format(
                        "Failed send the test '%s' with error '%s'",
                        propFile.getName(),
                        e.getMessage()
                ));
            }
        }
    }
}
