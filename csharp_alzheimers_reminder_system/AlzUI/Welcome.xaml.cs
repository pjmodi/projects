using System;
using System.Collections.Generic;
using System.Text;
using System.Timers;
using System.Windows;
using System.Windows.Threading;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Media.Animation;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.IO;

namespace AlzUI
{
    /// <summary>
    /// Interaction logic for Welcome.xaml
    /// </summary>

    public partial class Welcome : System.Windows.Controls.Page
    {
        private delegate void NoArgDelegate();
        Timer monitorTaskQueue;

        // Variable for Slideshow
        List<string> showables;
        string[] files;
        DispatcherTimer dt = new DispatcherTimer();
        static int count = 0;
        int currentRect = 1;
        double fade = 3;
        int changeImageInterval = 10;

        public Welcome()
        {
            InitializeComponent();
            SetGreeting();
            ChangeMessage();

            //string folder = System.Environment.CurrentDirectory + @"\images\banners\";
            //string folder = @"E:\Photos\Jiya\6Months";
            string folder = @"E:\Photos\Nature";
            showables = GetImagePaths(folder);

            monitorTaskQueue = new Timer(1000);
            monitorTaskQueue.Elapsed += new ElapsedEventHandler(monitorTaskQueue_Elapsed);
            monitorTaskQueue.Start();

            dt.Tick += new EventHandler(dt_Tick);
            dt.Interval = TimeSpan.FromSeconds(changeImageInterval);
            dt_Tick(this, new EventArgs());
        }

        void monitorTaskQueue_Elapsed(object sender, ElapsedEventArgs e)
        {
            this.Dispatcher.BeginInvoke(
              DispatcherPriority.Loaded,
              new NoArgDelegate(this.ChangeMessage));
        }

        void ChangeMessage()
        {
            SolidColorBrush green = new SolidColorBrush(Color.FromArgb(200, 30, 100, 30));
            SolidColorBrush red = new SolidColorBrush(Color.FromArgb(200, 116, 48, 48));
            SolidColorBrush grey = new SolidColorBrush(Color.FromArgb(200, 50, 50, 50));

            if (MainWindow.regularTasks.Count > 0)
            {
                tbx_Task.Text = "Jack, You Have Some Pending Task(s). Touch Here To See Them";

                btn_Task.IsEnabled = true;
                btn_Task.Background = green;
                btn_Fun.Background = grey;
                btn_Task.Foreground = Brushes.White;
                btn_Fun.Foreground = Brushes.White;
            }
            else
            {
                tbx_Task.Text = "You Have No Incomplete Tasks";
                
                btn_Task.IsEnabled = false;
                btn_Task.Background = grey;
                btn_Fun.Background = green;
                btn_Task.Foreground = Brushes.White;
                btn_Fun.Foreground = Brushes.White;
            }
        }


        void Fun_Click(object sender, EventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            NavigationService.Navigate(new Fun());
        }

        void Task_Click(object sender, EventArgs e)
        {
            Task currentTask = (Task)MainWindow.regularTasks.Dequeue();
            CodeSnippets.Utilities.AudibleFeedback();
            NavigationService.Navigate(new TaskReminder(ref currentTask));
        }

        /// <summary>
        /// This method sets an appro
        /// </summary>
        void SetGreeting()
        {
            int currentHour = DateTime.Now.Hour;
            patientName.Text = "Jack!";
            
            if (currentHour < 12)
                greeting.Text= "Good Morning";
            else if (currentHour >= 12 && currentHour < 16)
                greeting.Text = "Good Afternoon";
            else
                greeting.Text = "Good Evening";
        }

        List<string> GetImagePaths(string folder)
        {
            List<string> filePaths = new List<string>();

            try
			{
				files = Directory.GetFiles(folder);
			}
			catch (DirectoryNotFoundException ex)
			{
			}

            foreach(string file in files)
            {
                if (file.ToUpper().EndsWith(".JPG") ||
                    file.ToUpper().EndsWith(".PNG") ||
                    file.ToUpper().EndsWith(".GIF") ||
                    file.ToUpper().EndsWith(".TIF"))
                {
                    filePaths.Add(file);
                }
            }

            filePaths.TrimExcess();

            return filePaths;
        }

        void dt_Tick(object sender, EventArgs e)
        {
            Image im = new Image();
            im.Source = new BitmapImage(new Uri(showables[count]));

            if (currentRect == 1)
            {
                rec1.Opacity = 0;
                VisualBrush vb = new VisualBrush(im);
                vb.Stretch = Stretch.UniformToFill;
                rec1.Fill = vb;
                rec1.BeginAnimation(FrameworkElement.OpacityProperty, new DoubleAnimation(1, TimeSpan.FromSeconds(fade)));
                rec2.BeginAnimation(FrameworkElement.OpacityProperty, new DoubleAnimation(0, TimeSpan.FromSeconds(fade)));
            }
            else
            {
                rec2.Opacity = 0;
                VisualBrush vb = new VisualBrush(im);
                vb.Stretch = Stretch.UniformToFill;
                rec2.Fill = vb;
                rec2.BeginAnimation(FrameworkElement.OpacityProperty, new DoubleAnimation(1, TimeSpan.FromSeconds(fade)));
                rec1.BeginAnimation(FrameworkElement.OpacityProperty, new DoubleAnimation(0, TimeSpan.FromSeconds(fade)));
            }
            dt.Start();

            if (currentRect == 1)
                currentRect = 2;
            else
                currentRect = 1;

            if (count == showables.Count - 1)
                count = 0;
            else
                count++;
        }
    }
}