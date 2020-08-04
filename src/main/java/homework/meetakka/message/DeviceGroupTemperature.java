package homework.meetakka.message;

import lombok.Value;

import java.util.Date;

@Value
public class DeviceGroupTemperature implements DeviceCenterCommand {

    Location location;

    Double temperature;

    Date time;

}
