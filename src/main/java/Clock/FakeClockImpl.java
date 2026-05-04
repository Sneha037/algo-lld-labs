package Clock;

public class FakeClockImpl implements Clock
{
    private long currentTime;
    @Override
    public long currentTime() {
        return currentTime;
    }

    public void setCurrenTime(long currentTime)
    {
        this.currentTime = currentTime;
    }

    public void advanceTime(long time)
    {
        currentTime = currentTime + time;
    }
}
