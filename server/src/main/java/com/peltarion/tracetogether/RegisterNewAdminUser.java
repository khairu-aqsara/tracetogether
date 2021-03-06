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

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class RegisterNewAdminUser
{
	public static void main(String[] args)
	{
		// Create an admin user
		// long idForCase = 6;
		String newUser = "jonatan";
		String newUserPassword = "admin124";
		// TODO(jontejj): expose config (and use tracetogether.se in prod)
		// TODO: don't use plaintext
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();

		CaseNotifierServiceGrpc.CaseNotifierServiceBlockingStub stub = CaseNotifierServiceGrpc.newBlockingStub(channel);

		AdminUser newAdminUser = AdminUser.newBuilder().setUser(newUser).setPassword(Password.newBuilder().setPassword(newUserPassword).build())
				.build();

		stub.requestNewUser(NewUserRequest.newBuilder().setCreator(SystemConfig.systemUser()).setNewUser(newAdminUser).build());

		// stub.sendConfirmationNotification(ConfirmedCaseNotificationRequest.newBuilder()
		// .setIdForConfirmedCase(com.peltarion.tracetogether.Id.newBuilder().setId(idForCase).build()).setUser(newAdminUser).build());
		// CasePassword casePassword =
		// stub.requestCasePassword(CasePasswordRequest.newBuilder().setIdForConfirmedCase(Id.newBuilder().setId(idForCase))
		// .setUser(newAdminUser).build());

		// System.out.println("Case password for " + idForCase + ": " + casePassword);

		// stub.requestCasePassword(CasePasswordRequest.newBuilder().set)

		// Do only once, and save id in local db
		// Id myId = stub.register(Empty.getDefaultInstance());

		// TODO(each app): Do some bluetooth stuff here, catch some other id:s
		// long valueFromBluetoothScanner = 42;
		// Id potentialCase = Id.newBuilder().setId(valueFromBluetoothScanner).build();

		// TODO(each app): Get confirmation in the UI (have the user enter a password)

		// Send (TODO retry if it fails??)
		// stub.confirmedCase(PotentialCases.newBuilder().setConfirmedId(myId).addPotentialCases(potentialCase).build());

		channel.shutdown();
	}
}
