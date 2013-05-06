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
using System.Speech.Synthesis;

namespace AlzUI
{
    /// <summary>
    /// Interaction logic for Help.xaml
    /// </summary>

    public partial class Help : System.Windows.Window
    {
        Transparent blurApplication;
        //SpeechSynthesizer s;	

        public Help(bool enableBlurring)
        {
            try
            {
                if (enableBlurring)
                {
                    blurApplication = new Transparent();
                    blurApplication.Show();
                }

                InitializeComponent();
                this.ShowDialog();
            }
            catch (Exception ex)
            {
            }
            finally
            {
                if (blurApplication != null)
                    blurApplication.Close();

                //if (s != null)
                //    s.Dispose();
            }
        }

        void Help_Load(object sender, EventArgs e)
        {
            //s = new SpeechSynthesizer();
            //string message = "Dear Jack, Please call me on 612-123-4567. Love, Jill";
            //s.SpeakAsync(message);
        }

        void Close_Click(object sender, EventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            this.Close();
        }
    }
}