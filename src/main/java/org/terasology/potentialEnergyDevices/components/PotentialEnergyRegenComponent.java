// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.potentialEnergyDevices.components;

import org.terasology.engine.entitySystem.Component;
import org.terasology.engine.network.Replicate;

public class PotentialEnergyRegenComponent implements Component {
    @Replicate
    public float energyPerSecond;
}
