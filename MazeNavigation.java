import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
public class MazeNavigation
{

	private final SensorPort ultrasonicPort;
	private final UltrasonicSensor ultrasonicSensor;
	public static void main(String[] args)
	{
		final MazeNavigation robot;
		robot = new MazeNavigation();
		robot.run();
	}
	public MazeNavigation()
	{
		ultrasonicPort = SensorPort.S1;
		ultrasonicSensor = new UltrasonicSensor(ultrasonicPort);
	}
	public void run()
	{
		Motor.B.setSpeed(720);
		Motor.C.setSpeed(720);
		// go forward...
		moveForward();
		// ... until the ultrasonic sees something within 30cm
		waitForDistance(25);
		turnRight();
		moveForward();
		waitForDistance(25);
		turnLeft();
		moveForward();
		waitForDistance(10);
		
	}
	private int waitForDistance(final int max)
	{
		int distance;
		do
		{
			try
			{
				// pause 100 ms between reads...
				// this is a LeJOS issue
				// later versions of LeJOS won't require this
				Thread.sleep(100);
			}
			catch (InterruptedException ex)
			{
			}
			distance = ultrasonicSensor.getDistance();
		}
		while(distance > max);
		return (distance);
	}
	private void moveForward()
	{
		Motor.B.forward();
		Motor.C.forward();
	}
	private void turnRight()
	{
		Motor.B.setSpeed(60);
		Motor.B.rotate(360 * 2, true);
		Motor.C.rotate((360 * 4) - 210, true);
		// keep the program running until both motors
		// have stopped.
		while(Motor.B.isMoving() &&
		Motor.C.isMoving())
		{
		}
		Motor.B.setSpeed(720);
	}
	
	private void turnLeft()
	{
		Motor.C.setSpeed(60);
		Motor.B.rotate((360 * 4) - 180, true);
		Motor.C.rotate(360 * 2, true);
		// keep the program running until both motors
		// have stopped.
		while(Motor.B.isMoving() &&
		Motor.C.isMoving())
		{
		}
		Motor.C.setSpeed(720);
	}
}