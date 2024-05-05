package com.dku.council.danfestatable.domain.admin.dto.list;

import com.dku.council.danfestatable.domain.admin.dto.response.ResponseUserAdminDto;
import com.dku.council.danfestatable.domain.matchtable.model.entity.MatchingTable;
import com.dku.council.danfestatable.domain.user.model.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class SummarizedTableAdminDto {
    private final Long id;
    private final int tableNumber;
    private final int totalHeart;
    private final String startTime;
    private final String endTime;
    private final String userCount;
    private final String users;

    public SummarizedTableAdminDto(MatchingTable table) {
        this.id = table.getId();
        this.tableNumber = table.getTableNumber();
        this.totalHeart = table.getTotalHeart();
        this.startTime = cleanTime(table.getStartTime());
        this.endTime = cleanTime(table.getEndTime());
        this.userCount = separate(table.getUsers());
        this.users = makeInfo(ResponseUserAdminDto.of(table.getUsers()));
    }

    private String separate(List<User> users) {
        int totalSize = users.size();
        int count = 0;

        for(User user : users) {
            if(user.getGender().equals("남자")) {
                count++;
            }
        }
        return "남자:" + count + "명, 여자:" + (totalSize - count) + "명";
    }

    private String cleanTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm:ss");
        return time.format(formatter);
    }

    private String makeInfo(List<ResponseUserAdminDto> users) {
        StringBuilder sb = new StringBuilder();
        for(ResponseUserAdminDto user : users) {
            sb.append(user.getName()).append(" / ").append(user.getGender()).append(" / ").append(user.getPhone()).append("<br>");
        }
        return sb.toString();
    }
}
