package dev.vorstu.coworkingapp;

import dev.vorstu.coworkingapp.repositories.BookingRepository;
import dev.vorstu.coworkingapp.services.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
@Slf4j
public class CleanupSchedule {

    private final BookingService bookingService;

    @Scheduled(cron = "0 0 2 * * *")
    public void cleanUpExpiredBookings() {
        try {
            log.info("Beginning of bookings cleaning");
            bookingService.cleanUpExpiredBookings();
            log.info("Cleaning completed successfully");
        } catch (Exception e) {
            log.error("The cleanup failed.");
            throw e;
        }
    }

}
