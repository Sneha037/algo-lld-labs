package Clock;

public class RealClockImpl implements Clock
{
    @Override
    public long currentTime() {
        return System.nanoTime();
    }
}
