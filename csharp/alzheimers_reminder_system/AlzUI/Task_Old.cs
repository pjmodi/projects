using System;
using System.Collections;

namespace AlzUI
{
    public class Task
    {
        int         id;
        string      title;
        bool        important;
        string      description;
        DateTime    startTime;
        int         timeOut;
        TaskStatus  status;
        string[]    instructions;

        public enum TaskStatus
        {
            Pending,
            Complete,
            Skipped
        };

        #region Properties

        public int ID
        { 
            get { return id; }  
        }

        public string Title
        {
            get { return title;  }
        }

        public bool Important
        {
            get { return important; }
        }

        public string Description
        {
            get { return description; }
        }

        public DateTime StartTime
        {
            get { return startTime; }
        }

        public int TimeOut
        {
            get { return timeOut; }
        }

        public TaskStatus Status
        {
            get { return status; }
            set { status = value; }
        }

        #endregion Properties

        public Task(
            int id, string title, bool important, string description, 
            DateTime startTime, int timeOut, TaskStatus status
            )
        {
            this.id = id;
            this.title = title;
            this.important = important;
            this.description = description;
            this.startTime = startTime;
            this.timeOut = timeOut;
            this.status = status;
        }
    }
}
