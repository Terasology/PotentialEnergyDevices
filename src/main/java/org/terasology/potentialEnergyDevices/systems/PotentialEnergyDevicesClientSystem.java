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
package org.terasology.potentialEnergyDevices.systems;

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.potentialEnergyDevices.components.PotentialEnergyDeviceComponent;
import org.terasology.rendering.nui.layers.ingame.inventory.GetItemTooltip;
import org.terasology.rendering.nui.widgets.TooltipLine;

@RegisterSystem(RegisterMode.CLIENT)
public class PotentialEnergyDevicesClientSystem extends BaseComponentSystem {

    @ReceiveEvent
    public void getItemTooltip(GetItemTooltip event, EntityRef entityRef, PotentialEnergyDeviceComponent potentialEnergyDeviceComponent) {
        event.getTooltipLines().add(new TooltipLine(String.format("Energy: %.0f/%.0f", potentialEnergyDeviceComponent.currentStoredEnergy, potentialEnergyDeviceComponent.maximumStoredEnergy)));
    }
}
