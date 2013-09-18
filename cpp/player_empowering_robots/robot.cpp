#include <libplayerc++/playerc++.h>
#include <iostream>
#include <string>
#include "point.cpp"

using namespace PlayerCc;
using namespace std;

class	Robot
{
	private:
	 int		 id;
	 PlayerClient	 *client;
	 PlannerProxy 	 *planner;
	 Position2dProxy *position2d;
	 Point 		 *currentPosition;
	 Point 		 *destination;
	 Point		 nullPoint;
	 float 		 battery;
	 float		 batteryReserved;
	 bool		 inMotion;
	 bool		 dead;
	 bool 		 toggle;

	public:
	 Robot(void);
	 Robot(int, int, Point*);
	 void GoToPoint(Point*);
	 int TrackMovement(void);
	 bool CheckBattery(void);
	 bool AreWeThereYet(void);
	 bool IsAvailable(void);
};

	Robot::Robot(void)
	{}

	Robot::Robot(int robotId, int port, Point *initialPosition)
	{
		id = robotId;
		battery = 100.00;
		batteryReserved = 0.00;
		currentPosition = initialPosition;
		inMotion = false;
		dead = false;
		toggle = false;

		client = new PlayerClient("localhost", port);
		planner = new PlannerProxy(client, 0);
		position2d = new Position2dProxy(client, 0);
		
		nullPoint = Point();
	}


	void Robot::GoToPoint(Point *setDestination)
	{	
		destination = setDestination;

		planner->SetGoalPose(destination->GetX(), destination->GetY(), destination->GetAngle());
		cout << "Robot" << id << " going from " << currentPosition->GetTitle() << " to " << destination->GetTitle() << ". ** Battery: " << battery << endl;
		inMotion = true;

		currentPosition = &nullPoint;
	}

	int Robot::TrackMovement(void)
	{
		client->Read();

		// If the robot has reached the destination
		//if (planner->GetPathDone() && inMotion)
		if (AreWeThereYet() && inMotion)
		{
			currentPosition = destination;
			//currentPosition->SetOccupancy(true);
			destination = &nullPoint;
			inMotion = false;
			cout << "Robot" << id << " has reached " << currentPosition->GetTitle() << " ** Battery: " << battery << endl;
			
			if (!currentPosition->IsDockingPoint())
			{
				batteryReserved = 100 - battery;
				currentPosition->distanceFromBase = batteryReserved;
				cout << currentPosition->GetTitle() << " away by units: " << batteryReserved << endl;
				batteryReserved *= 1.4;

				if (currentPosition->SOS != NONE)
				{
					currentPosition->SOS = NONE;

					// Ask Robot Holding Position to leave
					int idOfRobotThatMustLeave = currentPosition->idOfRobotRequestingSOS;
					currentPosition->idOfRobotRequestingSOS = 0;

					if (id != idOfRobotThatMustLeave)
						cout << "Robot" << id << " replacing Robot" << idOfRobotThatMustLeave << endl;
					
					return idOfRobotThatMustLeave;
				} 
			}
			else // it has reached a docking station
			{
				battery = 100.0;
				batteryReserved = 0.0;
				toggle = false;
				cout << "*** Robot" << id << " battery RECHARGED! ***" << endl;
			}
		}
		
		return 0;
	}

	bool Robot::AreWeThereYet(void)
	{
		bool flag = false;
		double tolerance = 1.0;
		double destX = destination->GetX(); 
		double destY = destination->GetY();
		double currX = planner->GetPx();
		double currY = planner->GetPy();

		if ((destX-currX)*(destX-currX) + (destY-currY)*(destY-currY) < 1.0)
			flag = true;

		return flag;
	}		

	bool Robot::CheckBattery(void)
	{
		// If the battery is dead, stop the robot wherever it is
		if (battery<0 && !dead)
		{
			battery = 0;
			dead = true;
			inMotion = false;
			planner->SetEnable(false);
			position2d->SetMotorEnable(false);
			cout << "*** Robot" << id << " BATTERY DEAD! ***" << endl;
		}
		else if (!dead)
		{
			if (inMotion) // If the robot is in Motion
				battery = battery - 0.05;
			else if (!currentPosition->IsDockingPoint())
			{
				battery = battery - 0.025;

				if (currentPosition->SOS==NONE && battery<currentPosition->distanceFromBase*2)
				{
					currentPosition->idOfRobotRequestingSOS = id;
					currentPosition->SOS = HELP;
					cout << "** SOS from " << currentPosition->GetTitle() << " ** Battery: " << battery << endl;
				}

				if (battery - batteryReserved <= 0 && !toggle)
				{
					toggle = true;
					return true;
				}
			}
		}

		return false;		
	}

	bool Robot::IsAvailable(void)
	{
		if (currentPosition->isNull)
			return false;

		return currentPosition->IsDockingPoint();
	}
