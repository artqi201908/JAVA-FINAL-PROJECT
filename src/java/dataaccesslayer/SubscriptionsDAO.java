package dataaccesslayer;

import transferobject.SubscriptionDTO;
import java.util.List;
import transferobject.SubscriptionDTO;

public interface SubscriptionsDAO {

    List<SubscriptionDTO> getAllSubscriptions();

    SubscriptionDTO getSubscriptionById(int subscriptionId);

    void addSubscription(SubscriptionDTO subscription);

    void updateSubscription(SubscriptionDTO subscription);

    void deleteSubscription(int subscriptionId);
}
