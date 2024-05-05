package com.dku.council.danfestatable.domain.matchtable.model.dto.response;

import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseMyTableDto {

    private final int tableNumber;

    private final int totalHeart;

    private final String startTime;

    private final String endTime;

    private final List<String> users;

    public ResponseMyTableDto(MatchingTable table, List<String> users) {
        this.tableNumber = table.getTableNumber();
        this.totalHeart = table.getTotalHeart();
        this.startTime = cleanTime(table.getStartTime());
        this.endTime = cleanTime(table.getEndTime());
        this.users = users;
    }

    private String cleanTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
        return time.format(formatter);
    }
}
