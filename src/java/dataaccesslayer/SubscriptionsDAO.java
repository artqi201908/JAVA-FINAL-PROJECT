package java.dataaccesslayer;

import java.transferobject.SubscriptionDTO;
import java.util.List;

public interface SubscriptionsDAO {
    List<SubscriptionDTO> getAllSubscriptions();

    SubscriptionDTO getSubscriptionById(int subscriptionId);

    void addSubscription(SubscriptionDTO subscription);

    void updateSubscription(SubscriptionDTO subscription);

    void deleteSubscription(int subscriptionId);
}
