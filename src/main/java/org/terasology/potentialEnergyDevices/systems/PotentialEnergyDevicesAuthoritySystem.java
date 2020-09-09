// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.potentialEnergyDevices.systems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.engine.core.Time;
import org.terasology.engine.entitySystem.entity.EntityManager;
import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterMode;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.engine.entitySystem.systems.UpdateSubscriberSystem;
import org.terasology.engine.registry.In;
import org.terasology.engine.world.WorldProvider;
import org.terasology.math.TeraMath;
import org.terasology.potentialEnergyDevices.components.PotentialEnergyDeviceComponent;
import org.terasology.potentialEnergyDevices.components.PotentialEnergyRegenComponent;

@RegisterSystem(RegisterMode.AUTHORITY)
public class PotentialEnergyDevicesAuthoritySystem extends BaseComponentSystem implements UpdateSubscriberSystem {
    private static final long UPDATE_INTERVAL = 1000;
    private static final Logger logger = LoggerFactory.getLogger(PotentialEnergyDevicesAuthoritySystem.class);

    @In
    WorldProvider worldProvider;
    @In
    Time time;
    @In
    EntityManager entityManager;

    long nextUpdateTime;

    @Override
    public void update(float delta) {
        long currentTime = time.getGameTimeInMs();
        if (currentTime > nextUpdateTime) {
            nextUpdateTime = currentTime + UPDATE_INTERVAL;

            // add all natural regen/decay
            for (EntityRef entity : entityManager.getEntitiesWith(PotentialEnergyRegenComponent.class,
                    PotentialEnergyDeviceComponent.class)) {
                PotentialEnergyRegenComponent regenComponent = entity.getComponent(PotentialEnergyRegenComponent.class);
                PotentialEnergyDeviceComponent consumerComponent =
                        entity.getComponent(PotentialEnergyDeviceComponent.class);
                if (consumerComponent.currentStoredEnergy < consumerComponent.maximumStoredEnergy) {
                    // TODO: make this more precise by finding how much time has elapsed since the last update
                    consumerComponent.currentStoredEnergy =
                            TeraMath.clamp(consumerComponent.currentStoredEnergy + regenComponent.energyPerSecond, 0,
                                    consumerComponent.maximumStoredEnergy);
                    entity.saveComponent(consumerComponent);
                }
            }
        }
    }
}
