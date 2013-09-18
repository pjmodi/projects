using System;
using System.Collections.Generic;
using System.Text;
using System.Timers;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Media;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace AlzUI
{
    /// <summary>
    /// Interaction logic for TaskAlert.xaml
    /// </summary>

    public partial class TaskAlert : System.Windows.Window
    {
        Timer audibleAlert;
        Transparent blurApplication;
        bool showRegularTask; 

        public TaskAlert()
        {
            audibleAlert = new Timer(10000);
            audibleAlert.Elapsed += new ElapsedEventHandler(AudibleAlert_Elapsed);
            SoundAlert();
        }

        public bool ShowAlertScreen(bool importantReminder)
        {
            try
            {
                blurApplication = new Transparent();
                blurApplication.Show();

                InitializeComponent();

                if (importantReminder)
                {
                    txt_Message.Text = "There is something you must do right now.";
                    btn_ImportantTask.Visibility = Visibility.Visible;
                    dock_RegularTask.Visibility = Visibility.Hidden;
                }
                else
                {
                    txt_Message.Text = "There is something you need to do.";
                    btn_ImportantTask.Visibility = Visibility.Hidden;
                    dock_RegularTask.Visibility = Visibility.Visible;
                }

                this.ShowDialog();

                return showRegularTask;
            }
            finally
            {
                if (blurApplication != null)
                    blurApplication.Close();
            }
        }

        void SeeTask_Click(object sender, EventArgs e)
        {
            audibleAlert.Stop();
            CodeSnippets.Utilities.AudibleFeedback();
            this.Close();
        }

        void LaunchRegularTask_Click(object sender, EventArgs e)
        {
            audibleAlert.Stop();
            CodeSnippets.Utilities.AudibleFeedback();
            showRegularTask = true;
            this.Close();
        }

        void Continue_Click(object sender, EventArgs e)
        {
            audibleAlert.Stop();
            CodeSnippets.Utilities.AudibleFeedback();
            this.Close();
        }

        void AudibleAlert_Elapsed(object sender, ElapsedEventArgs e)
        {
            SoundAlert();
        }

        void SoundAlert()
        {
            SoundPlayer player = new SoundPlayer("notify.wav");
            player.Play();
            audibleAlert.Start();
        }
    }
}