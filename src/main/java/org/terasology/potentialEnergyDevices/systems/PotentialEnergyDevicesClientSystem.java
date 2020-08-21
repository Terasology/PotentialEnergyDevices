// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.potentialEnergyDevices.systems;

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.potentialEnergyDevices.components.PotentialEnergyDeviceComponent;
import org.terasology.rendering.nui.layers.ingame.inventory.GetItemTooltip;
import org.terasology.nui.widgets.TooltipLine;

@RegisterSystem(RegisterMode.CLIENT)
public class PotentialEnergyDevicesClientSystem extends BaseComponentSystem {

    @ReceiveEvent
    public void getItemTooltip(GetItemTooltip event, EntityRef entityRef, PotentialEnergyDeviceComponent potentialEnergyDeviceComponent) {
        event.getTooltipLines().add(new TooltipLine(String.format("Energy: %.0f/%.0f", potentialEnergyDeviceComponent.currentStoredEnergy, potentialEnergyDeviceComponent.maximumStoredEnergy)));
    }
}
