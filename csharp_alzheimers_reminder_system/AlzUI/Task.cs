using System;
using System.Xml.Serialization;

namespace AlzUI
{
    [XmlRootAttribute("TaskList")]
    public class TaskList
    {
        private Task[] task;

        [XmlElementAttribute("Task")]
        public Task[] Task
        {
            get { return task; }
            set { task = value; }
        }

        public TaskList(int noOfTasks)
        {
            task = new Task[noOfTasks];
        }

        public TaskList()
        { }
    }


    [XmlTypeAttribute("Task")]
    public class Task
    {
        int id;
        string title;
        bool important;
        string description;
        DateTime startTime;
        int timeOut;
        TaskStatus status;
        Instructions instructions;
        string imageFilePath;

        public enum TaskStatus
        {
            Pending,
            Complete,
            Skipped,
            OnScreen,
            Queued
        };

        #region Properties

        [XmlElementAttribute("ID")]
        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        [XmlElementAttribute("Title")]
        public string Title
        {
            get { return title; }
            set { title = value; }
        }

        [XmlElementAttribute("Important")]
        public bool Important
        {
            get { return important; }
            set { important = value; }
        }

        [XmlElementAttribute("Description")]
        public string Description
        {
            get { return description; }
            set { description = value; }
        }

        [XmlElementAttribute("StartTime")]
        public DateTime StartTime
        {
            get { return startTime; }
            set { startTime = value; }
        }

        [XmlElementAttribute("TimeOut")]
        public int TimeOut
        {
            get { return timeOut; }
            set { timeOut = value; }
        }

        [XmlElementAttribute("Status")]
        public TaskStatus Status
        {
            get { return status; }
            set { status = value; }
        }

        [XmlElementAttribute("Instructions")]
        public Instructions Instructions
        {
            get { return instructions; }
            set { instructions = value; }
        }

        [XmlElementAttribute("ImageFilePath")]
        public string ImageFilePath
        {
            get { return imageFilePath; }
            set { imageFilePath = value; }
        }

        #endregion

        #region Constructors

        public Task(
            int id, string title, bool important, string description,
            DateTime startTime, int timeOut, TaskStatus status, string filePath,
            string[] listOfInstructions
            )
        {
            this.id = id;
            this.title = title;
            this.important = important;
            this.description = description;
            this.startTime = startTime;
            this.timeOut = timeOut;
            this.status = status;
            this.imageFilePath = filePath;
            this.instructions = new Instructions(listOfInstructions);
        }

        public Task()
        { }

        #endregion
    }

    [XmlTypeAttribute("Instructions")]
    public class Instructions
    {
        private string[] instruction;

        [XmlElementAttribute("Instruction")]
        public string[] Instruction
        {
            get { return instruction; }
            set { instruction = value; }
        }

        public Instructions()
        {
        }

        public Instructions(string[] listOfInstructions)
        {
            instruction = listOfInstructions;
        }
    }
}