package org.art.playground.misc.reactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayDeque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class SinkTest {

    @Test
    @SneakyThrows
    void shouldEmitValuesWithSinkManyUnicastWithSingleSubscriberWithBackpressure() {
        /*
            FAST emitter tries emmit the values to the sink AFTER the subscriber is subscribed.
            The values are consumed by a SLOW subscriber.
            The emitter RESPECTS the backpressure strategy of the subscriber. It doesn't emit the values faster than the subscriber consumes them!
            The queue is empty.
         */

        CountDownLatch subscribersReady = new CountDownLatch(1);

        ArrayDeque<Integer> queue = new ArrayDeque<>(16);
        Sinks.Many<Integer> sink = Sinks.many()
            .unicast()
            .onBackpressureBuffer(queue);

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                // Waiting till subscriber subscribes
                try {
                    subscribersReady.await();
                } catch (InterruptedException e) {
                }

                for (int i = 0; i < 50; i++) {
                    try {
                        log.info("Emitter. Emitting value: {}", i);

                        sink.tryEmitNext(i);

                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            });

        Flux<Integer> flux = sink.asFlux();

        flux.doOnNext(val -> {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                }
                log.info("Subscriber 1. Consumed value: {}, queue size: {}", val, queue.size());
            })
            .doOnSubscribe(sub -> {
                log.info("Subscriber 1. Subscribed!");
                subscribersReady.countDown();
            })
            .subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
            .subscribe();

        Thread.sleep(20_000);
    }

    @Test
    @SneakyThrows
    void shouldEmitValuesWithSinkManyUnicastWithSingleSubscriberWithBuffering() {
        /*
            Emitter emits the values to the sink BEFORE the subscriber is subscribed.
            The values are buffered in the queue which is unbounded.
            When the subscriber is subscribed, it consumes the values from the queue.
         */

        CountDownLatch subscribersReady = new CountDownLatch(1);

        ArrayDeque<Integer> queue = new ArrayDeque<>(16);
        Sinks.Many<Integer> sink = Sinks.many()
            .unicast()
            .onBackpressureBuffer(queue);

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                for (int i = 0; i < 50; i++) {
                    try {
                        log.info("Emitter. Emitting value: {}", i);

                        sink.tryEmitNext(i);

                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
                subscribersReady.countDown();
            });

        Flux<Integer> flux = sink.asFlux();

        // Waiting till all values are emitted and buffered
        subscribersReady.await();

        flux.doOnNext(val -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
                log.info("Subscriber 1. Consumed value: {}, queue size: {}", val, queue.size());
            })
            .doOnSubscribe(sub -> log.info("Subscriber 1. Subscribed!"))
            .subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
            .subscribe();

        Thread.sleep(20_000);
    }

    @Test
    @SneakyThrows
    void shouldEmitValuesWithSinkManyMulticastWithTwoSubscriberWithBackpressure() {
         /*
            FAST emitter tries emmit the values to the sink AFTER the subscribers are subscribed.
            The values are consumed by SLOW subscribers with different slowness.
            The emitter RESPECTS the backpressure strategy of the slowest subscriber. It doesn't emit the values faster than the slowest subscriber consumes them!
         */

        CountDownLatch subscribersReady = new CountDownLatch(2);

        Sinks.Many<Integer> sink = Sinks.many()
            .multicast()
            .onBackpressureBuffer(16);

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                try {
                    subscribersReady.await();
                } catch (InterruptedException e) {
                }

                for (int i = 0; i < 50; i++) {
                    try {
                        log.info("Emitter. Emitting value: {}", i);

                        sink.tryEmitNext(i);

                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            });

        Flux<Integer> flux = sink.asFlux();

        flux.doOnNext(val -> {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                }
                log.info("Subscriber 1. Consumed value: {}", val);
            })
            .doOnSubscribe(sub -> {
                log.info("Subscriber 1. Subscribed!");
                subscribersReady.countDown();
            })
            .doOnRequest(req -> log.info("Subscriber 1. Requested: {}", req))
            .subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
            .subscribe();


        flux.doOnNext(val -> {
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                }
                log.info("Subscriber 2. Consumed value: {}", val);
            })
            .doOnSubscribe(sub -> {
                log.info("Subscriber 2. Subscribed!");
                subscribersReady.countDown();
            })
            .doOnRequest(req -> log.info("Subscriber 2. Requested: {}", req))
            .subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
            .subscribe();

        Thread.sleep(20_000);
    }

    @Test
    @SneakyThrows
    void shouldEmitValuesWithSinkManyMulticastWithTwoSubscriberWithBuffering() {
         /*
            Emitter emits the values to the sink BEFORE the subscribers are subscribed.
            The values are buffered somewhere in the queue which is BOUNDED.
            When the FIRST subscriber is subscribed, it consumes 8 first values from the queue. Another elements are DISCARDED?
            The second subscriber has NO elements consumed.
         */

        CountDownLatch subscribersReady = new CountDownLatch(1);

        Sinks.Many<Integer> sink = Sinks.many()
            .multicast()
            .onBackpressureBuffer(4);

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                for (int i = 0; i < 50; i++) {
                    try {
                        log.info("Emitter. Emitting value: {}", i);

                        sink.tryEmitNext(i);

                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
                subscribersReady.countDown();
            });

        // Waiting till all values are emitted and buffered
        subscribersReady.await();

        Flux<Integer> flux = sink.asFlux();

        flux.doOnNext(val -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
                log.info("Subscriber 1. Consumed value: {}", val);
            })
            .doOnSubscribe(sub -> {
                log.info("Subscriber 1. Subscribed!");
            })
            .doOnRequest(req -> log.info("Subscriber 1. Requested: {}", req))
            .subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
            .subscribe();


        flux.doOnNext(val -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
                log.info("Subscriber 2. Consumed value: {}", val);
            })
            .doOnSubscribe(sub -> {
                log.info("Subscriber 2. Subscribed!");
            })
            .doOnRequest(req -> log.info("Subscriber 2. Requested: {}", req))
            .subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
            .subscribe();

        Thread.sleep(20_000);
    }

    @Test
    @SneakyThrows
    void shouldEmitValuesWithSinkManyMulticastWithTwoSubscriberWithBackpressureWhenSecondSubscriberSubscribesLater() {
         /*
            FAST emitter tries emmit the values to the sink AFTER first subscriber is subscribed.
            The values are consumed by the first subscriber with backpressure.
            After some time the second SLOW subscriber subscribes and consumes the values.
            The emitter RESPECTS the backpressure strategy of the slowest subscriber. It doesn't emit the values faster than the slowest subscriber consumes them!
         */

        CountDownLatch subscribersReady = new CountDownLatch(1);

        Sinks.Many<Integer> sink = Sinks.many()
            .multicast()
            .onBackpressureBuffer(16);

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                try {
                    subscribersReady.await();
                } catch (InterruptedException e) {
                }

                for (int i = 0; i < 50; i++) {
                    try {
                        log.info("Emitter. Emitting value: {}", i);

                        sink.tryEmitNext(i);

                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            });

        Flux<Integer> flux = sink.asFlux();

        flux.doOnNext(val -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                log.info("Subscriber 1. Consumed value: {}", val);
            })
            .doOnSubscribe(sub -> {
                log.info("Subscriber 1. Subscribed!");
                subscribersReady.countDown();
            })
            .doOnRequest(req -> log.info("Subscriber 1. Requested: {}", req))
            .subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
            .subscribe();

        Thread.sleep(5_000);

        flux.doOnNext(val -> {
                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                }
                log.info("Subscriber 2. Consumed value: {}", val);
            })
            .doOnSubscribe(sub -> {
                log.info("Subscriber 2. Subscribed!");
            })
            .doOnRequest(req -> log.info("Subscriber 2. Requested: {}", req))
            .subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
            .subscribe();

        Thread.sleep(20_000);
    }

    @Test
    @SneakyThrows
    void shouldEmitValuesWithSinkManyUnicastWithSubscriberWithRequestWithBackpropagation() {
         /*
            Self-requesting subscriber.
            Emittor respects the requested by the consumer values.
         */

        CountDownLatch subscribersReady = new CountDownLatch(1);

        Sinks.Many<Integer> sink = Sinks.many()
            .multicast()
            .onBackpressureBuffer(16);

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                try {
                    subscribersReady.await();
                } catch (InterruptedException e) {
                }

                for (int i = 0; i < 10000; i++) {
                    try {
                        log.info("Emitter. Emitting value: {}", i);

                        sink.tryEmitNext(i);

                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                }
            });

        Flux<Integer> flux = sink.asFlux();

        final Subscription[] sub = {null};

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                flux.subscribe(new BaseSubscriber<>() {

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("Subscriber 1. Consumed value: {}", value);
                        Thread.sleep(100);

                        Subscription subscription = sub[0];
                        if (subscription != null) {

                            // Additional request
                            subscription.request(1);
                        }
                    }

                    @SneakyThrows
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        subscribersReady.countDown();

                        int request = 10;

                        // Intial request
                        subscription.request(request);
                        log.info("Subscriber 1. Subscribed And Requested {}", request);
                    }
                });

            });

        Thread.sleep(20_000);
    }

    @Test
    @SneakyThrows
    void shouldEmitValuesWithSinkManyMulticastWithTwoSubscribersWithBestEffortStrategy() {
         /*
            Best effort strategy. If the first subscriber is not requesting the values, the values are dropped,
            the second subscriber will consume the values and wouldn't be blocked.
         */

        CountDownLatch subscribersReady = new CountDownLatch(2);

        Sinks.Many<Integer> sink = Sinks.many()
            .multicast()
            .directBestEffort();

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                try {
                    subscribersReady.await();
                } catch (InterruptedException e) {
                }

                for (int i = 0; i < 10000; i++) {
                    try {
                        log.info("Emitter. Emitting value: {}", i);

                        sink.tryEmitNext(i);

                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                    }
                }
            });

        Flux<Integer> flux = sink.asFlux();

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                flux.subscribe(new BaseSubscriber<>() {

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("Subscriber 1. Consumed value: {}", value);
                        Thread.sleep(100);
                    }

                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        subscribersReady.countDown();

                        int request = 3;

                        subscription.request(request);
                        log.info("Subscriber 1. Subscribed And Requested {}", request);
                    }
                });
            });


        Thread.sleep(1_000);

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                flux.subscribe(new BaseSubscriber<>() {

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("Subscriber 2. Consumed value: {}", value);
                        Thread.sleep(10);
                    }

                    @SneakyThrows
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        subscribersReady.countDown();

                        int request = 100;

                        subscription.request(request);
                        log.info("Subscriber 2. Subscribed And Requested {}", request);
                    }
                });

            });

        Thread.sleep(20_000);
    }

    @Test
    @SneakyThrows
    void shouldEmitValuesWithSinkManyMulticastWithSubscribersWithDifferentPace() {
         /*
            Two subscribers with different pace.
            The first subscriber consumes the values faster than the second one.
            The emitter respects the backpressure strategy of the slowest subscriber.
         */

        CountDownLatch subscribersReady = new CountDownLatch(2);

        Sinks.Many<Integer> sink = Sinks.many()
            .multicast()
            .onBackpressureBuffer(16);

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                try {
                    subscribersReady.await();
                } catch (InterruptedException e) {
                }

                for (int i = 0; i < 10000; i++) {
                    try {
                        log.info("Emitter. Emitting value: {}", i);

                        sink.tryEmitNext(i);

                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                }
            });

        Flux<Integer> flux = sink.asFlux();

        final Subscription[] sub1 = {null};

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                flux.subscribe(new BaseSubscriber<>() {

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("Subscriber 1. Consumed value: {}", value);
                        Thread.sleep(100);

                        Subscription subscription = sub1[0];
                        if (subscription != null) {

                            // Additional request
                            subscription.request(1);
                        }
                    }

                    @SneakyThrows
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        subscribersReady.countDown();

                        int request = 1;

                        sub1[0] = subscription;

                        // Initial request
                        subscription.request(request);
                        log.info("Subscriber 1. Subscribed And Requested {}", request);
                    }
                });

            });

        final Subscription[] sub2 = {null};

        Executors.newSingleThreadExecutor()
            .submit(() -> {
                flux.subscribe(new BaseSubscriber<>() {

                    @SneakyThrows
                    @Override
                    protected void hookOnNext(Integer value) {
                        log.info("Subscriber 2. Consumed value: {}", value);
                        Thread.sleep(1_000);

                        Subscription subscription = sub2[0];
                        if (subscription != null) {

                            // Additional request
                            subscription.request(1);
                        }
                    }

                    @SneakyThrows
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        subscribersReady.countDown();

                        int request = 1;

                        sub2[0] = subscription;

                        // Initial request
                        subscription.request(request);
                        log.info("Subscriber 2. Subscribed And Requested {}", request);
                    }
                });

            });

        Thread.sleep(20_000);
    }

    @Test
    void testFluxSinkWithDropOnOverflow() throws InterruptedException {

        Flux<Object> fluxAsyncBackp = Flux.create(emitter -> {

                // Publish 1000 numbers
                for (int i = 0; i < 1000; i++) {
                    System.out.println(Thread.currentThread().getName() + " | Publishing = " + i);
                    emitter.next(i);
                }
                // When all values or emitted, call complete.
                emitter.complete();
            }, FluxSink.OverflowStrategy.DROP)
            .onBackpressureDrop(i -> System.out.println(Thread.currentThread().getName() + " | DROPPED = " + i));

        fluxAsyncBackp.
            subscribeOn(Schedulers.boundedElastic())
            .publishOn(Schedulers.boundedElastic())
            .subscribe(i -> {
                // Process received value.
                System.out.println(Thread.currentThread().getName() + " | Received = " + i);
                // 500 mills delay to simulate slow subscriber
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        /*
         * Notice above -
         *
         * OverflowStrategy.DROP - If subscriber can't keep up with values, then drop
         * the values.
         *
         * subscribeOn & publishOn - Put subscriber & publishers on different threads.
         */

        // Since publisher & subscriber run on different thread than main thread, keep
        // main thread active for 100 seconds.
        Thread.sleep(100000);
    }

    @Test
    void testFluxSinkWithErrorOnOverflow() throws InterruptedException {
        Flux<Object> fluxAsyncBackp = Flux.create(emitter -> {

            // Publish 1000 numbers
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " | Publishing = " + i);
                // BackpressureStrategy.ERROR will cause MissingBackpressureException when
                // subscriber can't keep up. So handle exception & call error handler.
                emitter.next(i);
            }
            // When all values or emitted, call complete.
            emitter.complete();

        }, FluxSink.OverflowStrategy.ERROR);

        fluxAsyncBackp
            .subscribeOn(Schedulers.boundedElastic())
            .publishOn(Schedulers.boundedElastic())
            .subscribe(i -> {
                // Process received value.
                System.out.println(Thread.currentThread().getName() + " | Received = " + i);
            }, e -> {
                // Process error
                System.err.println(Thread.currentThread().getName() + " | Error = " + e.getClass().getSimpleName() + " "
                    + e.getMessage());
            });
        /*
         * Notice above -
         *
         * OverflowStrategy.ERROR - Throws MissingBackpressureException is subscriber
         * can't keep up.
         *
         * subscribeOn & publishOn - Put subscriber & publishers on different threads.
         */

        // Since publisher & subscriber run on different thread than main thread, keep
        // main thread active for 100 seconds.
        Thread.sleep(100000);
    }

    /**
     * Order is not defined in this case.
     */
    @SneakyThrows
    @Test
    void runOnTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .parallel()
            .runOn(Schedulers.boundedElastic())
            .doOnNext(i -> {
                log.info("Received: {}", i);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(2_000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            })
            .sequential()
            .subscribe(i -> {
                log.info("Subscribed: {}", i);
            });

        Thread.sleep(20_000);
    }

    /**
     * Order is defined in this case.
     */
    @SneakyThrows
    @Test
    void publishOnTest() {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .publishOn(Schedulers.boundedElastic())
            .doOnNext(i -> {
                log.info("Received: {}", i);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(2_000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            })
            .subscribeOn(Schedulers.boundedElastic())
            .subscribe(i -> {
                log.info("Subscribed: {}", i);
            });

        Thread.sleep(20_000);
    }


}
