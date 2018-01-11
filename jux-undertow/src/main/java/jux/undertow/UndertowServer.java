/**
 * Copyright 2018 Sandor Nemeth
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
package jux.undertow;

import io.undertow.Undertow;
import jux.PortProvider;
import jux.Router;
import jux.Server;

public class UndertowServer implements Server {

    private UndertowRoutingConverter routingConverter;

    private Undertow.Builder undertow;
    private Undertow server;

    public UndertowServer() {
        undertow = Undertow.builder();
        routingConverter = new UndertowRoutingConverter();
    }

    @Override
    public Server listenOn(int port) {
        // TODO host could be a parameter later on
        PortProvider portProvider = new PortProvider(port);
        undertow.addHttpListener(portProvider.get(), "localhost");
        return this;
    }

    @Override
    public Server use(Router router) {
        undertow.setHandler(routingConverter.convert(router));
        return this;
    }

    @Override
    public void start() {
        server = undertow.build();
        server.start();
    }

    @Override
    public void stop() {
        server.stop();
    }
}
