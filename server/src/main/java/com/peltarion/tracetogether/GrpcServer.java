/* Copyright 2020 jonatanjonsson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.peltarion.tracetogether;

import java.io.IOException;
import java.net.InetSocketAddress;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;

public class GrpcServer
{
	public static void main(String[] args) throws IOException, InterruptedException
	{
		Server server = NettyServerBuilder.forAddress(new InetSocketAddress("0.0.0.0", 8080)) //
				.addService(new CaseNotifierServiceImpl()).build();

		server.start();
		server.awaitTermination();
	}
}
