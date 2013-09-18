/// <summary>
/// File Name	:	Main.cs
/// Description	:	This class describes the TaskList object. Each element
///                 must be decorated with necessary XMLSerializer Attributes
///                 for XML Serialization / De-serialization
/// Created Date:	15-Nov-2007
/// Created By	:	Pushkar Modi
/// </summary>

using System;
using System.Collections;
using System.Xml;
using System.IO;
using Serializer;

namespace SerializeXML
{
	/// <summary>
	/// This is a console application that will demo the XML Serializer or De-serializer
	/// </summary>
	class Demo
	{
		[STAThread]
		static void Main(string[] args)
		{
            try
            {
                // Please have one of these two regions commented out before running the code

                #region Serialize Demo
                // Step 1: This region will create a dummy object and serialize it to 
                // an XML file with the filename in yyyyMMdd.xml format. It will also output
                // the same file on the screen.

                //*
                
                // Creation of the mock object
                string[] list = new string[3];
                list[0] = "Go to the bathroom";
                list[1] = "Your brush & toothpaste are in the closet above the sink";
                list[2] = "Remember to use the mouth wash if today is a monday";

                string fileName = DateTime.Now.ToString("yyyyMMdd") + ".xml";

                Task t1 = new Task(
                    1, "Brush Teeth", false, "Please Brush your teeth.",
                    DateTime.Now.AddMinutes(2), 15, Task.TaskStatus.Pending, fileName,
                    list);
                Task t2 = new Task(
                    2, "Take Medication", true, "Please take your medication.",
                    DateTime.Now.AddMinutes(1), int.MaxValue, Task.TaskStatus.Pending, fileName,
                    list);
                TaskList tasks = new TaskList(20);
                tasks.Tasks[0] = t1;
                tasks.Tasks[1] = t2;

                XMLSerializerDeserializer xmlCore = new XMLSerializerDeserializer();
                XmlDocument xmlDoc = new XmlDocument();
                xmlDoc = xmlCore.ObjectToXML(tasks);
                xmlDoc.Save(fileName);

                Console.WriteLine(xmlDoc.OuterXml);
                
                //*/

                #endregion

                #region DeSerialize Demo
                // Step 2: This region will read an XML file of the format yyyyMMdd.xml 
                // and map the data to the TaskList class. It will output the description
                // of all the tasks found in the file.

                /*

                XmlDocument xmlDoc = new XmlDocument();

                string fileName = DateTime.Now.ToString("yyyyMMdd") + ".xml";
                xmlDoc.Load(fileName);

                XMLSerializerDeserializer xmlCore = new XMLSerializerDeserializer();
                TaskList taskList = (TaskList)xmlCore.XMLToObject(xmlDoc);

                foreach (Task t in taskList.Tasks)
                {
                    Console.WriteLine(t.Description);
                }

                */
                #endregion
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            finally
            {
                Console.ReadLine();
            }
		}
	}
}