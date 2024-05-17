package com.dku.council.danfestatable.domain.matchtable.model.dto.response;

import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class SummarizedTableDto {
    private final Long id;
    private final int tableNumber;
    private final String startTime;
    private final String endTime;
    private final int totalHeart;
    private final String userCount;

    public SummarizedTableDto(MatchingTable table) {
        this.id = table.getId();
        this.tableNumber = table.getTableNumber();
        this.startTime = cleanTime(table.getStartTime());
        this.endTime = cleanTime(table.getEndTime());
        this.totalHeart = table.getTotalHeart();
        this.userCount = makeUserCount(table.getUsers());
    }

    private String cleanTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
        return time.format(formatter);
    }

    private String makeUserCount(List<User> users) {
        int totalSize = users.size();
        int count = 0;

        for(User user : users) {
            if(user.getGender().equals("남자")) {
                count++;
            }
        }
        return "남자:" + count + "명, 여자:" + (totalSize - count) + "명";
    }
}
