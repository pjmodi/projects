using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.Xml;
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
    /// Interaction logic for WnMain.xaml
    /// </summary>

    public partial class MainWindow : System.Windows.Window
    {
        public static ArrayList allTasks;
        public static Queue regularTasks;
        public static Frame Frame_TopBar;
        public static Frame Frame_Main;

        int period = 1000;
        private delegate void NoArgDelegate();
        Timer trackTasks;

        
        public Window WindowMain
        {
            get { return mainWindow; }
        }

        public MainWindow()
        {
            InitializeComponent();

            allTasks = new ArrayList();
            regularTasks = new Queue();
            Frame_TopBar = new Frame();
            Frame_Main = new Frame();
            
            RowDefinition row0 = new RowDefinition();
            row0.Height = new GridLength(100, GridUnitType.Pixel);
            RowDefinition row1 = new RowDefinition();
            row1.Height = new GridLength(Double.MaxValue, GridUnitType.Star);
            wn_Grid.RowDefinitions.Add(row0);
            wn_Grid.RowDefinitions.Add(row1);

            Frame_TopBar.Source = new Uri("TopBar.xaml", UriKind.Relative);
            Frame_TopBar.NavigationUIVisibility = NavigationUIVisibility.Hidden;
            Frame_Main.Source = new Uri("Welcome.xaml", UriKind.Relative);
            Frame_Main.NavigationUIVisibility = NavigationUIVisibility.Hidden;
            wn_Grid.Children.Add(Frame_TopBar);
            Grid.SetRow(Frame_TopBar, 0);
            wn_Grid.Children.Add(Frame_Main);
            Grid.SetRow(Frame_Main, 1);

            #region Create Dummy Tasks

            string[] list = new string[3];
            list[0] = "Go to the bathroom";
            list[1] = "Your brush & toothpaste are in the closet above the sink";
            list[2] = "Remember to use the mouth wash if today is a monday";

            Task t1 = new Task(
                1, "Brush Teeth", false, "Please Brush your teeth.",
                DateTime.Now.AddMinutes(1), 15, Task.TaskStatus.Pending, "a.jpg", list);

            Task t2 = new Task(
                2, "Take Medication", true, "Please take your medication.",
                DateTime.Now.AddMinutes(2), int.MaxValue, Task.TaskStatus.Pending, "b.jpg", list);

            Task t3 = new Task(
                3, "Take Shower", false, "Please take a bath.",
                DateTime.Now.AddMinutes(3), 45, Task.TaskStatus.Pending, "c.jpg", list);

            Task t4 = new Task(
                4, "Safety Check", false, "Please turn off the stove, close all windows and switch off the lights.",
                DateTime.Now.AddMinutes(4), 10, Task.TaskStatus.Pending, "d.jpg", list);


            allTasks.Add(t1);
            allTasks.Add(t2);
            allTasks.Add(t3);
            allTasks.Add(t4);

            #endregion

            #region Read from XML

            //XmlDocument xmlDoc = new XmlDocument();
            //DateTime today = DateTime.Now;

            ////string folder = @"C:\TaskXml\";
            //string fileName = DateTime.Now.ToString("yyyyMMdd") + ".xml";
            ////string filePath = folder + fileName;

            //try
            //{
            //    //xmlDoc.Load(filePath);
            //    xmlDoc.Load(fileName);

            //    XMLSerializerDeserializer xmlCore = new XMLSerializerDeserializer();
            //    TaskList taskList = (TaskList)xmlCore.XMLToObject(xmlDoc);

            //    if (taskList.Task.Length > 0)
            //    {
            //        foreach (Task task in taskList.Task)
            //            allTasks.Add(task);
            //    }
            //}
            //catch (Exception ex)
            //{
            //    MessageBox.Show(
            //        ex.Message,
            //        "Error in retrieving tasks for today",
            //        MessageBoxButton.OK,
            //        MessageBoxImage.Error);

            //    this.Close();
            //}

            #endregion

            allTasks.TrimToSize();

            trackTasks = new Timer(period);
            trackTasks.Elapsed += new ElapsedEventHandler(trackTasks_Elapsed);
            trackTasks.Start();
        }

        void trackTasks_Elapsed(object sender, ElapsedEventArgs e)
        {
            Frame_Main.Dispatcher.BeginInvoke(
              DispatcherPriority.Loaded,
              new NoArgDelegate(this.ParseTasks));
        }

        void ParseTasks()
        {
            for (int i = 0; i < allTasks.Count; i++)
            {
                Task task = (Task)allTasks[i];

                if ((task.StartTime.Hour.Equals(DateTime.Now.Hour) &&
                    task.StartTime.Minute.Equals(DateTime.Now.Minute)) && task.Status == Task.TaskStatus.Pending)
                {
                    if (task.Important)
                    {
                        task.Status = Task.TaskStatus.OnScreen;

                        TaskAlert alert = new TaskAlert();
                        alert.ShowAlertScreen(true);

                        Frame_Main.Navigate(new TaskReminder(ref task));
                        break;
                    }
                    else // for regular tasks
                    {
                        task.Status = Task.TaskStatus.OnScreen;

                        TaskAlert alert = new TaskAlert();
                        bool showTask = alert.ShowAlertScreen(false);

                        if (showTask)
                        {
                            Frame_Main.Navigate(new TaskReminder(ref task));
                        }
                        else
                        {
                            task.Status = Task.TaskStatus.Queued;
                            regularTasks.Enqueue(task);
                        }
                    }
                }
            }
         }
    }
}