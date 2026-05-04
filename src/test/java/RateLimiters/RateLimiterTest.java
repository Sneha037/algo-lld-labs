package RateLimiters;

import Clock.Clock;
import Clock.FakeClockImpl;

import static java.lang.Boolean.TRUE;

public class RateLimiterTest
{
    public static void main(String[] args) throws InterruptedException
    {
       /* RateLimiter rateLimiter = new RateLimiter(3, 1);

        for(int i=0; i<5; i++)
        {
            boolean allowed = rateLimiter.tryAcquire("Sneha");
            System.out.println("Request for Sneha is: " + ((allowed==TRUE)? "ALLOWED" : "DENIED"));
        }

        Thread.sleep(10000);

        for(int i=0; i<3; i++)
        {
            boolean allowed = rateLimiter.tryAcquire("Sneha");
            System.out.println("Request for Sneha is: " + (allowed ? "ALLOWED" : "DENIED"));
        }

        */

        FakeClockImpl clock = new FakeClockImpl();
        clock.setCurrenTime(0L);
        RateLimiter rateLimiter = new RateLimiter(2, 1, clock);

        for(int i=0; i<2; i++)
        {
            boolean result = rateLimiter.tryAcquire("Sneha");
            System.out.println("Request :" + result);
        }

        clock.advanceTime(1000000000L);

        for(int i=0; i<6; i++)
        {
            boolean result = rateLimiter.tryAcquire("Sneha");
            //Thread.sleep(1000);
            clock.advanceTime(1000000000L);
            System.out.println("Request :" + result);
        }
    }
}
