package TokenBucket;

import Clock.Clock;

public class TokenBucket {
    private final double capacity;      // max tokens
    private final double refillRate;    // tokens per second
    private double currentTokens;
    private long lastRefillTime;
    private Clock clock;

    // in nanoseconds

    // constructor sets capacity, refillRate, fills currentTokens to capacity,
    // sets lastRefillTime to System.nanoTime()

    public TokenBucket(double capacity, double refillRate, Clock clock) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        currentTokens = capacity;
        this.clock = clock;
        lastRefillTime  = clock.currentTime();
    }

    public synchronized boolean tryAcquire() {
        // 1. calculate how much time has passed since lastRefillTime
        // 2. add (elapsed seconds × refillRate) tokens, capped at capacity
        // 3. update lastRefillTime
        // 4. if currentTokens >= 1, subtract 1 and return true
        // 5. otherwise return false

        long now = clock.currentTime();
        double elapsedTime = (now - lastRefillTime)/ 1000000000.0;

        currentTokens = Math.min(capacity, currentTokens + elapsedTime * refillRate);

        /*long timePassed = System.currentTimeMillis() - lastRefillTime;
        currentTokens = Math.min(capacity, timePassed * refillRate);
        */
        lastRefillTime = now;

        if(currentTokens >= 1)
        {
            currentTokens--;
            return true;
        }

        return  false;
    }
}