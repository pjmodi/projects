using System;
using System.Collections.Generic;
using System.Text;
using System.Timers;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using CodeSnippets;

namespace AlzUI
{
    /// <summary>
    /// Interaction logic for TaskReminder.xaml
    /// </summary>

    public partial class TaskReminder : System.Windows.Controls.Page
    {
        Task currentTask;

        public TaskReminder(ref Task showTask)
        {
            try
            {
                currentTask = showTask;
                InitializeComponent();
                taskDescription.Text = currentTask.Description;
                BitmapImage bmp = new BitmapImage(new Uri(currentTask.ImageFilePath, UriKind.Relative));
                imgTask.Source = bmp;
            }
            finally
            {
            }
        }

        void Yes_Click(object sender, RoutedEventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();

            currentTask.Status = Task.TaskStatus.Complete;
            string subject = currentTask.Title + " - Completed! at " + DateTime.Now.ToLongTimeString();
            Utilities.EMail(subject, subject);

            NavigationService.GoBack();
        }

        void No_Click(object sender, RoutedEventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            currentTask.Status = Task.TaskStatus.Skipped;

            string subject = currentTask.Title + " - SKIPPED at " + DateTime.Now.ToLongTimeString();
            Utilities.EMail(subject, subject);

            NavigationService.GoBack();
        }

        void Instructions_Click(object sender, RoutedEventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            new InstructionsPopup(currentTask.Title, currentTask.Instructions.Instruction);
        }
        
    }
}