/**
 * Copyright © 2018 Sandor Nemeth (sandor.nemeth.1986 at gmail dot com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jux.bodyparser.jackson;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class JacksonBodyWriterTest {

    private JacksonBodyWriter writer = new JacksonBodyWriter();

    @Test
    void testSupportsJson() {
        Assertions.assertThat(writer.supportedMediaTypes())
                .hasSize(1)
                .containsExactlyInAnyOrder("application/json");
    }

    @Test
    void testWritesJsonBody() throws Exception {
        TestPojo pojo = new TestPojo("str", 1);
        String body = writer.write(pojo);
        Assertions.assertThat(body)
                .isEqualTo("{\"stringValue\":\"str\",\"intValue\":1}");
    }
}