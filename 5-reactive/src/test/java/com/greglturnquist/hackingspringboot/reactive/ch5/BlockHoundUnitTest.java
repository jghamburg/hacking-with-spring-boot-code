/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.greglturnquist.hackingspringboot.reactive.ch5;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * @author Greg Turnquist
 */
class BlockHoundUnitTest {

    // tag::obvious-failure[]
    @Test
    void threadSleepIsABlockingCall() {
        Mono.delay(Duration.ofSeconds(1)) // <1>
            .flatMap(tick -> {
                try {
                    Thread.sleep(10); // <2>
                    return Mono.just(true);
                } catch (InterruptedException e) {
                    return Mono.error(e);
                }
            })
            .as(StepVerifier::create)
            .verifyComplete();
    }
    // end::obvious-failure[]

}