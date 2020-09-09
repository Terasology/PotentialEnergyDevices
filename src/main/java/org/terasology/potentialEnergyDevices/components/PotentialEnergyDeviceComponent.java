// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.potentialEnergyDevices.components;

import org.terasology.engine.entitySystem.Component;
import org.terasology.engine.network.Replicate;
import org.terasology.engine.world.block.ForceBlockActive;
import org.terasology.inventory.logic.ItemDifferentiating;

@ForceBlockActive
public class PotentialEnergyDeviceComponent implements Component, ItemDifferentiating {
    @Replicate
    public float currentStoredEnergy;
    @Replicate
    public float maximumStoredEnergy;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PotentialEnergyDeviceComponent that = (PotentialEnergyDeviceComponent) o;

        if (that.currentStoredEnergy > 0 || this.currentStoredEnergy > 0) {
            // never allow items with power to stack
            return false;
        }
        if (Float.compare(that.currentStoredEnergy, currentStoredEnergy) != 0) {
            return false;
        }
        return Float.compare(that.maximumStoredEnergy, maximumStoredEnergy) == 0;
    }

    @Override
    public int hashCode() {
        int result = (currentStoredEnergy != +0.0f ? Float.floatToIntBits(currentStoredEnergy) : 0);
        result = 31 * result + (maximumStoredEnergy != +0.0f ? Float.floatToIntBits(maximumStoredEnergy) : 0);
        return result;
    }
}
