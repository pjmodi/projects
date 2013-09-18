#include <libplayerc++/playerc++.h>
#include "robot.cpp"

using namespace PlayerCc;

const int noOfRobots = 16;
const int noOfObservationPoints = 8;
const int startingPort = 6665;
Robot robot[noOfRobots];

int main(int argc, char *argv[]) 
{ 
	Point docking[noOfRobots];
	Point observation[noOfObservationPoints];

	docking[0] = Point("Docking Station 1", 12, -1, 180, true);
	docking[1] = Point("Docking Station 2", 13, -3, 180, true);
	docking[2] = Point("Docking Station 3", 15, -3, 0, true);
	docking[3] = Point("Docking Station 4", 16, -1, 0, true);
	docking[4] = Point("Docking Station 5", 12, 1, 180, true);
	docking[5] = Point("Docking Station 6", 13, 3, 180, true);
	docking[6] = Point("Docking Station 7", 15, 3, 0, true);
	docking[7] = Point("Docking Station 8", 16, 1, 0, true);

	docking[8] = Point("Docking Station 9", 11, -6, 90, true);
	docking[9] = Point("Docking Station 10", 13, -5, 90, true);
	docking[10] = Point("Docking Station 11", 15, -5, 90, true);
	docking[11] = Point("Docking Station 12", 17, -6, 90, true);
	docking[12] = Point("Docking Station 13", 11, 6, 270, true);
	docking[13] = Point("Docking Station 14", 13, 5, 270, true);
	docking[14] = Point("Docking Station 15", 15, 5, 270, true);
	docking[15] = Point("Docking Station 16", 17, 6, 270, true);

	observation[0] = Point("Observation Pt 1", -5, -13, 0, false);
	observation[1] = Point("Observation Pt 2", 8, -13, 180, false);
	observation[2] = Point("Observation Pt 3", 21, -13, 90, false);
	observation[3] = Point("Observation Pt 4", 36, -13, 180, false);
	observation[4] = Point("Observation Pt 5", -5, 13, 0, false);
	observation[5] = Point("Observation Pt 6", 8, 13, 180, false);
	observation[6] = Point("Observation Pt 7", 21, 13, 90, false);
	observation[7] = Point("Observation Pt 8", 36, 13, 180, false);
	
	int i = 0;

	for(i=0; i<noOfRobots; i++)
		robot[i] = Robot(i+1, startingPort+i, &docking[i]);

	for(i=0; i<noOfObservationPoints; i++)
		robot[i].GoToPoint(&observation[i]);

	while(1)
	{
		for(i=0; i<noOfRobots; i++)
		{
			int robotReturningHome = 0;
			robotReturningHome = robot[i].TrackMovement();

			if (robotReturningHome > 0)			
			{
				robotReturningHome--;
				robot[robotReturningHome].GoToPoint(&docking[robotReturningHome]);
			}

			if (robot[i].CheckBattery())
				robot[i].GoToPoint(&docking[i]);

			if (robot[i].IsAvailable())
			{			
				bool rescuedSomeone = false;

				for (int y=0; y<noOfObservationPoints; y++)
				{
					if( observation[y].SOS == HELP && !rescuedSomeone)
					{
						cout << "Robot" << i+1 << " will rescue " << observation[y].GetTitle() << endl;
						observation[y].SOS = ONTHEWAY;
						robot[i].GoToPoint(&observation[y]);
						rescuedSomeone = true;
					}
				}
			}
		}
	}
}

