using System;
using System.Collections.Generic;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Media3D;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using System.Windows.Threading;

namespace AlzUI
{
    /// <summary>
    /// Interaction logic for PhotoAlbum.xaml
    /// </summary>

    public partial class PhotoAlbum : System.Windows.Controls.Page
    {
		Mesh3DObjects.Plane planeFactory = new Mesh3DObjects.Plane();
		Point3DAnimation pa;
		Random ran = new Random();
		Point3D[] p3s = new Point3D[16];
		ModelVisual3D[] mvs = new ModelVisual3D[16];
		int count = 0;
		DispatcherTimer dt = new DispatcherTimer();

        public PhotoAlbum()
		{
			InitializeComponent();
			dt.Interval = TimeSpan.FromSeconds(2);
			dt.Tick += new EventHandler(dt_Tick);
			for (int i = 0; i < 16; i++)
			{
                ImageBrush ib = new ImageBrush(new BitmapImage(new Uri("pack://application:,,,/images/albums/im" + i + ".jpg")));
				ib.Stretch = Stretch.Uniform;

                ModelVisual3D mv = new ModelVisual3D();
				Material mat = new DiffuseMaterial(ib);
				GeometryModel3D plane = new GeometryModel3D(planeFactory.Mesh, mat);
				mv.Content = plane;
				mvs[i] = mv;
				myViewPort3D.Children.Add(mv);
				Matrix3D trix = new Matrix3D();
				double x = ran.NextDouble() * 50 - 50;
				double y = ran.NextDouble() * 2 - 2;
				double z = -i * 10;
				p3s[i] = new Point3D(x, y, z);
				trix.Append(new TranslateTransform3D(x, y, z).Value);
				mv.Transform = new MatrixTransform3D(trix);
			}

			pa = new Point3DAnimation(p3s[0], TimeSpan.FromMilliseconds(300));
			pa.AccelerationRatio = 0.3;
			pa.DecelerationRatio = 0.3;
			pa.Completed += new EventHandler(pa_Completed);
			cam.BeginAnimation(PerspectiveCamera.PositionProperty, pa);
		}

		void dt_Tick(object sender, EventArgs e)
		{
			dt.Stop();
			if (count == 8) count = 0;
			else count++;
			for (int i = 0; i < 9; i++)
			{
				(((mvs[i].Content as GeometryModel3D).Material as DiffuseMaterial).Brush as ImageBrush).Opacity = 0.3;
			}
			(((mvs[count].Content as GeometryModel3D).Material as DiffuseMaterial).Brush as ImageBrush).Opacity = 1;
			pa = new Point3DAnimation(new Point3D(p3s[count].X, p3s[count].Y, p3s[count].Z + 2), TimeSpan.FromMilliseconds(500));
			pa.AccelerationRatio = 0.3;
			pa.DecelerationRatio = 0.3;
			pa.Completed += new EventHandler(pa_Completed);
			cam.BeginAnimation(PerspectiveCamera.PositionProperty, pa);
		}

		void pa_Completed(object sender, EventArgs e)
		{
			pa = new Point3DAnimation(new Point3D(p3s[count].X, p3s[count].Y, p3s[count].Z + 1.6), TimeSpan.FromMilliseconds(3100));
			pa.Completed += new EventHandler(dt_Tick);
			cam.BeginAnimation(PerspectiveCamera.PositionProperty, pa);
		}

        void Return_Click(object sender, EventArgs e)
        {
            CodeSnippets.Utilities.AudibleFeedback();
            NavigationService.GoBack();
        }
    }
}