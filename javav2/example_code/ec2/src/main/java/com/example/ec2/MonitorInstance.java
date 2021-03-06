/*
 * Copyright 2010-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.example.ec2;
import software.amazon.awssdk.services.ec2.EC2Client;
import software.amazon.awssdk.services.ec2.model.MonitorInstancesRequest;
import software.amazon.awssdk.services.ec2.model.UnmonitorInstancesRequest;

/**
 * Toggles detailed monitoring for an EC2 instance
 */
public class MonitorInstance
{
    public static void monitorInstance(String instance_id)
    {
        EC2Client ec2 = EC2Client.create();

        MonitorInstancesRequest request = MonitorInstancesRequest.builder()
                .instanceIds(instance_id).build();

        ec2.monitorInstances(request);

        System.out.printf(
            "Successfully enabled monitoring for instance %s",
            instance_id);
    }

    public static void unmonitorInstance(String instance_id)
    {
        EC2Client ec2 = EC2Client.create();

        UnmonitorInstancesRequest request = UnmonitorInstancesRequest.builder()
            .instanceIds(instance_id).build();

        ec2.unmonitorInstances(request);

        System.out.printf(
            "Successfully disabled monitoring for instance %s",
            instance_id);
    }

    public static void main(String[] args)
    {
        final String USAGE =
            "To run this example, supply an instance id and a monitoring " +
            "status\n" +
            "Ex: MonitorInstance <instance-id> <true|false>\n";

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String instance_id = args[0];
        boolean monitor = Boolean.valueOf(args[1]);

        if (monitor) {
            monitorInstance(instance_id);
        } else {
            unmonitorInstance(instance_id);
        }
    }
}

