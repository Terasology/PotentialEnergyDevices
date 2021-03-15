/*
 * Copyright 2016 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.potentialEnergyDevices.components;

import org.terasology.engine.entitySystem.Component;
import org.terasology.engine.network.Replicate;
import org.terasology.engine.world.block.ForceBlockActive;
import org.terasology.logic.inventory.ItemDifferentiating;

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
        if (Float.compare(that.maximumStoredEnergy, maximumStoredEnergy) != 0) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (currentStoredEnergy != +0.0f ? Float.floatToIntBits(currentStoredEnergy) : 0);
        result = 31 * result + (maximumStoredEnergy != +0.0f ? Float.floatToIntBits(maximumStoredEnergy) : 0);
        return result;
    }
}
