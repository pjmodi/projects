/// <summary>
/// File Name	:	XMLSerializerDeserializer.cs
/// Description	:	This class holds generic functionality to serialize an object
///					to a corresponding XmlDocument object and vice-versa. 
///					It is necessary for the class that is going to be serialized or
///					de-serialized to have the necessary XMLSerializer Attributes.
/// Created Date:	15-Apr-2005
/// Created By	:	Pushkar Modi
/// </summary>


using System;
using System.Collections;
using System.IO;
using System.Reflection;
using System.Xml;
using System.Xml.Serialization;


namespace AlzUI
{
	/// <summary>
	/// Class that holds functions that serialize and de-serialize an object 
	/// to and from XML Syntax.
	/// </summary>
	public class XMLSerializerDeserializer
	{
		#region Constructor(s)
		public XMLSerializerDeserializer()
		{
		}
		#endregion
		
		#region Function(s)
		/// <summary>
		/// This funtions takes a string holding the name of the class as the input.
		/// It then digs into the executing assembly, fetches all the types and 
		/// compares them to the class name provided. If a match is found then 
		/// it returns the respective type.
		/// </summary>
		/// <param name="className">Name of the class for which Type object is 
		/// required</param>
		/// <returns>System.Type of the class passed as a string attribute</returns>
		private Type getObjectTypeFromName(string className)
		{
			// Get the object of the executing assembly
			Assembly assembly = Assembly.GetExecutingAssembly();
			// Get all the types from the assembly object into an array
			Type[] typeList = assembly.GetTypes();

			// Looping through each type in the list
			foreach (Type type in typeList)
			{		
				// If the type in the list is same as the one we are looking for
				if (type.Name == className)
					// Return the respective type object
					return (type.UnderlyingSystemType);
			}

			return(null);
		}




		/// <summary>
		/// This function takes an XmlDocument object and returns a de-serialized 
		/// object using the inbuilt XmlSerializer class. It creates a dynamic 
		/// instance of the object based on the root element of the XML syntax.
		/// </summary>
		/// <param name="xmlDoc">The XmlDocument object to be de-serialized</param>
		/// <returns>An instance of the object that was de-serialized from XML</returns>
		public object XMLToObject(XmlDocument xmlDoc)
		{
			// Get the root node from the XmlDocument object
			XmlElement rootNode = xmlDoc.DocumentElement;
			// Get the Type object that corresponds to the root node
			Type objectType = getObjectTypeFromName(rootNode.LocalName);		
			
			if (objectType != null)
			{
				// Create an XmlSerializer object and pass the type to be deserialized
				XmlSerializer deSerializer = new XmlSerializer(objectType);
				// Create a dynamic object of the type to be returned
				object returningObj = Activator.CreateInstance(objectType);

				// Create an XmlNodeReader object that will read the XmlDocument
				XmlNodeReader reader = new XmlNodeReader(xmlDoc);
				// Deserialize the information read into the dynamic object
				returningObj = deSerializer.Deserialize(reader);
				// Close the reader
				reader.Close();

				// Return the deserialized object
				return (returningObj);
			}
			return (null);
		}




		/// <summary>
		/// This function takes an object, serializes it and sends its XML 
		/// representation in an XmlDocument object using the inbuilt 
		/// XmlSerializer class.
		/// </summary>
		/// <param name="obj">The object to be serialized</param>
		/// <returns>XmlDocument object that represents the XML Syntax</returns>
		public XmlDocument ObjectToXML(object obj)
		{
			// Create an XmlSerializer object and pass the type to be serialized
			XmlSerializer serializer = new XmlSerializer(obj.GetType());

			// Create a memory stream for temporarily storing the XML
			MemoryStream ms = new MemoryStream();

			// Create a StreamWriter object that will write to the memory stream
			StreamWriter tempWriter = new StreamWriter(ms);
			// Serialize the object and pass it to the StreamWriter
			serializer.Serialize(tempWriter, obj);
			
			// Reset MemoryStream to beginning
			ms.Seek(0, SeekOrigin.Begin);

			// Create, Load and return XmlDocument
			XmlDocument returnObj = new XmlDocument();
			returnObj.Load((Stream)ms);
			return (returnObj);
		}
		#endregion
	}
}