using System;
using System.IO;
using System.Net;
using System.Windows;
using System.Collections.Generic;
using System.Windows.Threading;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Media.Animation;
using System.Windows.Navigation;

namespace Slideshow
{
	public partial class TheShow
	{
        string path;
        string[] files;
        List<string> showables = new List<string>();
        int count = 0;
        DispatcherTimer dt = new DispatcherTimer();
		MediaElement me;
		int currentRect = 1;
		double fade = 5;
        int changeImageInterval = 15;

		public TheShow(string p)
		{
            path = p;
            //this.InitializeComponent();
            //this.Show();
			try
			{
				files = Directory.GetFiles(path);
			}
			catch (DirectoryNotFoundException ex)
			{
                //MessageBox.Show(ex.Message);
                //this.Close();
			}
            for (int i = 0; i < files.Length; i++)
            {
                if (files[i].ToUpper().EndsWith(".JPG") ||
                    files[i].ToUpper().EndsWith(".PNG") ||
                    files[i].ToUpper().EndsWith(".GIF") ||
                    //files[i].ToUpper().EndsWith(".AVI") ||
                    //files[i].ToUpper().EndsWith(".WMV") ||
                    files[i].ToUpper().EndsWith(".TIF"))
                {
                    showables.Add(files[i]);
                }
            }
			dt_Tick(this, new EventArgs());
            dt.Interval = TimeSpan.FromSeconds(changeImageInterval);
            dt.Tick += new EventHandler(dt_Tick);
		}

        void dt_Tick(object sender, EventArgs e)
        {
            //if (showables.Count < 1)
            //{
            //    MessageBox.Show("No image files");
            //    this.Close();
            //    dt.Stop();
            //}
            //else
            //{
                //if (showables[count].ToUpper().EndsWith(".WMV") || showables[count].ToUpper().EndsWith(".AVI"))
                //{
                //    me = new MediaElement();
                //    me.MediaEnded += new RoutedEventHandler(me_MediaEnded);
                //    me.Source = new Uri(showables[count]);
                //    if (currentRect == 1)
                //    {
                //        rec1.Opacity = 0;
                //        VisualBrush vb = new VisualBrush(me);
                //        vb.Stretch = Stretch.UniformToFill;
                //        rec1.Fill = vb;
                //        rec1.BeginAnimation(FrameworkElement.OpacityProperty, new DoubleAnimation(1, TimeSpan.FromSeconds(fade)));
                //        rec2.BeginAnimation(FrameworkElement.OpacityProperty, new DoubleAnimation(0, TimeSpan.FromSeconds(fade)));
                //    }
                //    else
                //    {
                //        rec2.Opacity = 0;
                //        VisualBrush vb = new VisualBrush(me);
                //        vb.Stretch = Stretch.UniformToFill;
                //        rec2.Fill = vb;
                //        rec2.Width = me.Width;
                //        rec2.Height = me.Height;
                //        rec2.BeginAnimation(FrameworkElement.OpacityProperty, new DoubleAnimation(1, TimeSpan.FromSeconds(fade)));
                //        rec1.BeginAnimation(FrameworkElement.OpacityProperty, new DoubleAnimation(0, TimeSpan.FromSeconds(fade)));
                //    }
                //    dt.Stop();
                //}
                //else
                //{
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
                //}

				if (currentRect == 1) 
                    currentRect = 2;
				else 
                    currentRect = 1;

            //}
            if (count == showables.Count - 1)
                count = 0;
			else 
                count++;
        }

		void me_MediaEnded(object sender, RoutedEventArgs e)
		{
			dt_Tick(this, new EventArgs());
			dt.Start();
		}

		void newDelay(Object sender, EventArgs e)
		{
			MenuItem mi = sender as MenuItem;
			if (mi.Name == "sec1")
			{
				mi.IsChecked = true;
				sec2.IsChecked = false;
				sec3.IsChecked = false;
				sec4.IsChecked = false;
				sec5.IsChecked = false;
				dt.Interval = TimeSpan.FromSeconds(1);
			}
			if (mi.Name == "sec2")
			{
				mi.IsChecked = true;
				sec1.IsChecked = false;
				sec3.IsChecked = false;
				sec4.IsChecked = false;
				sec5.IsChecked = false;
				dt.Interval = TimeSpan.FromSeconds(2);
			}
			if (mi.Name == "sec3")
			{
				mi.IsChecked = true;
				sec2.IsChecked = false;
				sec1.IsChecked = false;
				sec4.IsChecked = false;
				sec5.IsChecked = false;
				dt.Interval = TimeSpan.FromSeconds(3);
			}
			if (mi.Name == "sec4")
			{
				mi.IsChecked = true;
				sec2.IsChecked = false;
				sec3.IsChecked = false;
				sec1.IsChecked = false;
				sec5.IsChecked = false;
				dt.Interval = TimeSpan.FromSeconds(4);
			}
			if (mi.Name == "sec5")
			{
				mi.IsChecked = true;
				sec2.IsChecked = false;
				sec3.IsChecked = false;
				sec4.IsChecked = false;
				sec1.IsChecked = false;
				dt.Interval = TimeSpan.FromSeconds(5);
			}
		}

		void closer(Object sender, EventArgs e)
		{
			this.Close();
		}
    }
}