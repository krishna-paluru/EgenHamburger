package com.krishna.TexasHamburger.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class OpenHours {
    private DayOfWeek day;
    @Temporal(TemporalType.TIME)
    private LocalTime open;
    @Temporal(TemporalType.TIME)
    private LocalTime close;

    public void setOpen(LocalTime open) {
        String strDateFormat = "HH:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        this.open = LocalTime.parse(sdf.format(open));
    }

    public void setClose(LocalTime close) {
        String strDateFormat = "HH:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        this.close = LocalTime.parse(sdf.format(close));
    }
}
