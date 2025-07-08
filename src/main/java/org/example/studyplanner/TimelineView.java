package org.example.studyplanner;

import java.time.LocalDateTime;
import java.util.List;

public class TimelineView {

    public String habitDateViewAll(HabitTracker ht){
        List<Habit> habits = ht.getHabits();
        StringBuilder response = new StringBuilder();
        for(Habit habit : habits){
            response.append("[ Habit: ")
                    .append(habit.getName())
                    .append(". Records: ");
            List<LocalDateTime> records = ht.getHabitRecords(habit.getId());
            for(LocalDateTime record : records){
                response.append(ht.formatHabitDate(record)).append(", ");
            }
            response.append("]");
        }

        return response.toString();
    }

}