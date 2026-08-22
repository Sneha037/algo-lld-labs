package RateLimiters;/* RateLimiters;

import clients.Client;
import TokenBucket.TokenBucket;

import java.util.HashMap;

public class RateLimiter
{
    HashMap<Client, TokenBucket> map = new HashMap<>();

    public RateLimiter(Client client)
}

 */


import Clock.Clock;
import TokenBucket.TokenBucket;

import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final double capacity;
    private final double refillRate;
    private Clock clock;
    private final ConcurrentHashMap<String, TokenBucket> buckets;

    public RateLimiter(double capacity, double refillRate, Clock clock) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.clock = clock;
        buckets = new ConcurrentHashMap<>();
    }

    public boolean tryAcquire(String clientId) {
        //TokenBucket tokenBucket = buckets.get(clientId);
        TokenBucket tokenBucket  = buckets.computeIfAbsent(clientId, k -> new TokenBucket(capacity, refillRate, clock));

        //long timeElapsed = System.nanoTime() - tokenBucket.lastRefillTime;

        return tokenBucket.tryAcquire();
    }
}
