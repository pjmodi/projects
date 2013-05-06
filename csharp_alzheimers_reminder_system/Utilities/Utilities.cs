using System;
using System.Collections.Generic;
using System.Media;
using System.Net;
using System.Net.Mail;
using System.Text;

namespace CodeSnippets
{
    public class Utilities
    {
        public static void EMail(string subject, string message)
        {
            MailAddress from = new MailAddress(Properties.Settings.Default.FromEmail, Properties.Settings.Default.FromEmailDisplayText);
            MailAddress to = new MailAddress(Properties.Settings.Default.ToEmail, Properties.Settings.Default.ToEmailDisplayText);
            MailMessage email = new MailMessage(from, to);

            email.Body = message;
            email.Subject = Properties.Settings.Default.SubjectPrefix + " - " + subject;

            SmtpClient smtp = new SmtpClient(Properties.Settings.Default.SmtpServer, Properties.Settings.Default.SmtpPort);
            smtp.SendCompleted += new SendCompletedEventHandler(smtp_SendCompleted);
            
            smtp.EnableSsl = Properties.Settings.Default.SmtpSSLEnabled;
            smtp.Credentials = new NetworkCredential(Properties.Settings.Default.SmtpUsername, Properties.Settings.Default.SmtpPassword);

            //smtp.Send(email);
            //smtp.SendAsync(email, new Object());
        }

        public static void AudibleFeedback()
        {
            SoundPlayer player = new SoundPlayer("click.wav");
            player.Play();
        }

        static void smtp_SendCompleted(object sender, System.ComponentModel.AsyncCompletedEventArgs e)
        {
            
        }
    }
}
