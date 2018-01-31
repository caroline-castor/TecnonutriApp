package tecnonutri.techfit.tecnonutri;

import retrofit2.Call;
import retrofit2.http.GET;
import tecnonutri.techfit.tecnonutri.models.Feed;

public interface FeedService {
    String BASE_URL= "http://api.tecnonutri.com.br/api/v4/";
    @GET("feed")
    Call<Feed> listFeed();


}
