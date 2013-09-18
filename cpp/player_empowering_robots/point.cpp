#include <string>

using namespace std;

enum HelpStatus 
{ NONE, HELP, ONTHEWAY };

class	Point
{
	private:
	 string title;
	 int positionX;
	 int positionY;
	 int angle;
	 bool dockingPoint;

	public:
	 int SOS;
	 int idOfRobotRequestingSOS;
	 float distanceFromBase;
	 bool isNull;
	 Point(void);
	 Point(string, int, int, int, bool);
	 int GetX(void);
	 int GetY(void);
	 int GetAngle(void);
	 string GetTitle(void);
	 bool IsDockingPoint(void);
};
	Point::Point(void)
	{
		isNull = true;
	}

	Point::Point(string description, int x, int y, int angleInDegrees, bool isDockingPoint)
	{
		title = description;
		positionX = x;
		positionY = y;
		angle = angleInDegrees;
		distanceFromBase = 0;
		dockingPoint = isDockingPoint;
		idOfRobotRequestingSOS = 0;
		SOS = NONE;
		isNull = false;
	}

	int Point::GetX(void)
	{
		return positionX;
	}

	int Point::GetY(void)
	{
		return positionY;
	}

	int Point::GetAngle(void)
	{
		return angle;
	}

	string Point::GetTitle(void)
	{
		return title;
	}

	bool Point::IsDockingPoint(void)
	{
		return dockingPoint;			
	}

