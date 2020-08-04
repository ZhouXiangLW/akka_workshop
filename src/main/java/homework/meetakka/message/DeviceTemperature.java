package homework.meetakka.message;

import lombok.Value;

@Value
public class DeviceTemperature implements DeviceGroupCommand {

    Location location;

    Double temperature;

}
