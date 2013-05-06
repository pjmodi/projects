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

namespace AlzUI
{
    /// <summary>
    /// Interaction logic for Fun.xaml
    /// </summary>

    public partial class Fun : System.Windows.Controls.Page
    {
        public Fun()
        {
            InitializeComponent();
        }

        void PhotoAlbum_Click(object sender, EventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            NavigationService.Navigate(new PhotoAlbum());
        }

        void Message_Click(object sender, EventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            NavigationService.Navigate(new SendMessage());
        }

        void Schedule_Click(object sender, EventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            NavigationService.Navigate(new Schedule());
        }

        void Return_Click(object sender, EventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            NavigationService.GoBack();
        }

    }
}