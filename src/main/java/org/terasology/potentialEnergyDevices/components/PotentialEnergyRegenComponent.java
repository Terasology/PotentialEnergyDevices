// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.potentialEnergyDevices.components;

import org.terasology.engine.network.Replicate;
import org.terasology.gestalt.entitysystem.component.Component;

public class PotentialEnergyRegenComponent implements Component<PotentialEnergyRegenComponent> {
    @Replicate
    public float energyPerSecond;
}
