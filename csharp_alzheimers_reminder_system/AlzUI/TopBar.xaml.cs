using System;
using System.Collections.Generic;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Timers;
using System.Windows.Threading;

namespace AlzUI
{
    /// <summary>
    /// Interaction logic for TopBar.xaml
    /// </summary>

    public partial class TopBar : System.Windows.Controls.Page
    {
        int period = 1000;
        private delegate void NoArgDelegate();
        Timer t;

        public TopBar()
        {
            InitializeComponent();

            date.Text = DateTime.Now.ToLongDateString();
            currentTime.Text = DateTime.Now.ToShortTimeString();

            t = new Timer(period);
            t.Elapsed += new ElapsedEventHandler(t_Elapsed);
            t.Start();
        }

        void t_Elapsed(object sender, ElapsedEventArgs e)
        {
            currentTime.Dispatcher.BeginInvoke(
              DispatcherPriority.Normal,
              new NoArgDelegate(this.SetClock));
        }

        void Help_Click(object sender, RoutedEventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            new Help(true);
        }

        void Schedule_Click(object sender, RoutedEventArgs e)
        {
            Uri schedule = new Uri("Schedule.xaml", UriKind.Relative);

            if (MainWindow.Frame_Main.Source != schedule)
            {
                CodeSnippets.Utilities.AudibleFeedback();
                MainWindow.Frame_Main.NavigationService.Navigate(schedule);
            }
        }

        void SetClock()
        {
            date.Text = DateTime.Now.ToLongDateString();
            currentTime.Text = DateTime.Now.ToShortTimeString();

            t.Start();
        }
    }
}