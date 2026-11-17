package com.acme.slots.web;

import com.acme.slots.model.ReservationRequest;
import com.acme.slots.model.Slot;
import com.acme.slots.service.SlotService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warehouses/{warehouseId}/slots")
public class SlotController {

    private final SlotService slots;

    public SlotController(SlotService slots) {
        this.slots = slots;
    }

    @GetMapping("/{date}")
    public List<Slot> day(@PathVariable long warehouseId,
                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return slots.day(warehouseId, date);
    }

    @PostMapping("/{date}/{hour}")
    public Slot reserve(@PathVariable long warehouseId,
                        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                        @PathVariable int hour,
                        @RequestBody ReservationRequest request) {
        return slots.reserve(warehouseId, date, hour, request.getPallets(), request.getPriority());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void conflict() {
    }
}
