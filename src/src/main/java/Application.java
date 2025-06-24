import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import java.util.function.Function;

public class Application {

    interface RemoteService {
        int process(int i);
    }

    public static void main(String[] args) {
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(20)
                .slidingWindowSize(5)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .build();

        RemoteService service = i -> {
            System.out.println("Processing: " + i);
            return i * 2;
        };

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
        Function<Integer, Integer> decorated = CircuitBreaker
                .decorateFunction(circuitBreaker, service::process);

        int result = decorated.apply(10);

        System.out.println("Result: " + result);
    }


}
