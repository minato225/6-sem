package carservice.model;

import java.util.Date;

public record Order(int id, Brand brand, int mileage, boolean isFinished, String departure, String destination, Date startTime) {
}
