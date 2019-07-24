package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    public Map<Long ,TimeEntry> hTimeEntriesMap = new HashMap<Long ,TimeEntry>();

    Long currentCount = 1L;

   /* public TimeEntry create(TimeEntry t ){

        Long id = currentCount++;

     TimeEntry t2 =  new TimeEntry(id,t.getProjectId(),t.getUserId(),t.getDate(),t.getHours());
        hTimeEntriesMap.put(id ,t2);
        return  t ;

    }*/

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentCount++;

        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        hTimeEntriesMap.put(id, newTimeEntry);
        return newTimeEntry;
    }



    @Override
    public TimeEntry find(Long timeEntryId) {
       /* TimeEntry t = null;
       for (int i=0 ;i< list.size();i++){

            t = list.get(i);
           if(timeEntryId == t.getId()){

               break;

           }
       }

        return t;*/

       return hTimeEntriesMap.get(timeEntryId);

    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(hTimeEntriesMap.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (find(id) == null) {
            return null;
        }

        TimeEntry updatedTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        hTimeEntriesMap.replace(id, updatedTimeEntry);
        return updatedTimeEntry;
    }

    @Override
    public void delete(Long id) {
        hTimeEntriesMap.remove(id);
    }


}
