using System;
using System.Collections.Generic;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Collections;
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
    /// Interaction logic for Schedule.xaml
    /// </summary>

    public partial class Schedule : System.Windows.Controls.Page
    {
        public Schedule()
        {
            InitializeComponent();
        }

        void Return_Click(object sender, EventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            NavigationService.GoBack();
        }
    }
}