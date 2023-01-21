package hr.tstrelar.fluxsse.event;

public interface NotificationEventListener {
    void onMessage(String message);
    void onComplete();
}
