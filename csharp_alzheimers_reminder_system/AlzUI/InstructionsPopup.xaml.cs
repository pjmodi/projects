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
using System.Windows.Shapes;

namespace AlzUI
{
    /// <summary>
    /// Interaction logic for Instructions.xaml
    /// </summary>

    public partial class InstructionsPopup : System.Windows.Window
    {
        Transparent blurApplication;

        public InstructionsPopup(string titleIn, string[] instructions)
        {
            try
            {
                blurApplication = new Transparent();
                blurApplication.Show();
                InitializeComponent();
                title.Text = "Detailed Instructions - " + titleIn;

                if (instructions[0].Length > 0)
                    Ins1.Text = "1) " + instructions[0];

                if (instructions[1].Length > 0)
                    Ins2.Text = "2) " + instructions[1];

                if (instructions[2].Length > 0)
                    Ins3.Text = "3) " + instructions[2];

                this.ShowDialog();              
            }
            finally
            {
                if (blurApplication != null)
                    blurApplication.Close();
            }
        }

        void MoreHelp_Click(object sender, EventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            new Help(false);
        }

        void Close_Click(object sender, EventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            this.Close();
        }

    }
}