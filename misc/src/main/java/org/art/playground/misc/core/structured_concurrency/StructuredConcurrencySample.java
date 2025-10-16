package org.art.playground.misc.core.structured_concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

@Slf4j
@SuppressWarnings("all")
public class StructuredConcurrencySample {

    record User(String name) {
    }

    record Orders(String details) {
    }

    public static void main(String[] args) throws InterruptedException {

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {

            var userTask = scope.fork(() -> fetchUser());
            var ordersTask = scope.fork(() -> fetchOrders());

            // Wait for both tasks to complete
            scope.join();
            scope.throwIfFailed(); // Throws if any task failed

            var user = userTask.get();
            var orders = ordersTask.get();

            log.info("✅ Successfully fetched: {}, {}", user.name(), orders.details);

        } catch (ExecutionException e) {
            // ignore
        }
    }

    static User fetchUser() throws InterruptedException {
        Thread.sleep(500); // Simulate delay
        return new User("Artem");
    }

    static Orders fetchOrders() throws InterruptedException {
        Thread.sleep(800); // Simulate delay
        return new Orders("3 recent orders");
    }
}
