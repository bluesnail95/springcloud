package gdut.ff.util;

import com.netflix.hystrix.HystrixObservableCommand;
import gdut.ff.domain.User;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

public class UserObservableCommand extends HystrixObservableCommand<User> {

    private RestTemplate restTemplate;
    private Long id;

    public UserObservableCommand(Setter setter,RestTemplate restTemplate,Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected Observable<User> construct() {
        Observable<User> userObservable = Observable.create(new Observable.OnSubscribe<User>() {
            public void call(Subscriber<? super User> observer) {
                try{
                    if(!observer.isUnsubscribed()) {
                        User user = restTemplate.getForObject("http://USER-SERVICE/users/1",User.class,1);
                        observer.onNext(user);
                        observer.onCompleted();
                    }
                }catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
        return userObservable;
    }
}
