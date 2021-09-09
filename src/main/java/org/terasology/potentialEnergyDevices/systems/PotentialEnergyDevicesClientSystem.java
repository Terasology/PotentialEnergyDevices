// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.potentialEnergyDevices.systems;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterMode;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.gestalt.entitysystem.event.ReceiveEvent;
import org.terasology.module.inventory.ui.GetItemTooltip;
import org.terasology.nui.widgets.TooltipLine;
import org.terasology.potentialEnergyDevices.components.PotentialEnergyDeviceComponent;

@RegisterSystem(RegisterMode.CLIENT)
public class PotentialEnergyDevicesClientSystem extends BaseComponentSystem {

    @ReceiveEvent
    public void getItemTooltip(GetItemTooltip event, EntityRef entityRef, PotentialEnergyDeviceComponent potentialEnergyDeviceComponent) {
        event.getTooltipLines().add(new TooltipLine(String.format("Energy: %.0f/%.0f", potentialEnergyDeviceComponent.currentStoredEnergy, potentialEnergyDeviceComponent.maximumStoredEnergy)));
    }
}
