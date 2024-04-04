package dataaccesslayer;

import java.transferobject.FoodWasteTrackerDTO;
import java.util.List;

public interface FoodWasteTrackerDAO {
    List<FoodWasteTrackerDTO> getAllWasteRecords();

    FoodWasteTrackerDTO getWasteRecordById(int recordId);

    void addWasteRecord(FoodWasteTrackerDTO wasteRecord);

    void updateWasteRecord(FoodWasteTrackerDTO wasteRecord);

    void deleteWasteRecord(int recordId);
}
