package com.acme.slots.service;

import com.acme.slots.model.Priority;
import com.acme.slots.model.Slot;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SlotService {

    private static final Logger log = LogManager.getLogger(SlotService.class);

    private final int slotsPerDay;
    private final Map<String, List<Slot>> calendar = new ConcurrentHashMap<>();

    public SlotService(@Value("${slots.per-day}") int slotsPerDay) {
        this.slotsPerDay = slotsPerDay;
    }

    public List<Slot> day(long warehouseId, LocalDate date) {
        return calendar.computeIfAbsent(warehouseId + "/" + date, key -> {
            log.info("Opening {} slots for warehouse {} on {}", slotsPerDay, warehouseId, date);
            return IntStream.range(0, slotsPerDay)
                    .mapToObj(hour -> new Slot(warehouseId, date, hour))
                    .collect(Collectors.toList());
        });
    }

    public Slot reserve(long warehouseId, LocalDate date, int hour, int pallets, Priority priority) {
        Slot slot = day(warehouseId, date).get(hour);
        if (slot.isReserved()) {
            log.warn("Slot {} at warehouse {} on {} already reserved", hour, warehouseId, date);
            throw new IllegalStateException("slot taken");
        }
        slot.reserve(pallets, priority);
        log.info("Reserved slot {} at warehouse {} on {}: {} pallets, {}",
                hour, warehouseId, date, pallets, priority);
        return slot;
    }
}
