package tecnonutri.techfit.tecnonutri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tecnonutri.techfit.tecnonutri.models.Feed;
import tecnonutri.techfit.tecnonutri.models.Items;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Falha" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FeedService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FeedService service = retrofit.create(FeedService.class);
        //retorna um objeto que faz a chamada
        Call<Feed> request_feed= service.listFeed();

        //chamada para o endpoint assyncrona
        request_feed.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                //obteve uma resposta (com sucesso ou sem sucesso)
                if(response.isSuccessful()){
                    //se foi sucesso
                    Feed feed = response.body();
                    for(Items i: feed.items){
                        System.out.println("------------PROFILE------------");
                        System.out.println("NAME: "+i.profile.name);
                        System.out.println("IMAGE: "+i.profile.image);
                        System.out.println("GENERAL GOAL: "+i.profile.general_goal);
                        System.out.println("--------------ITEMS----------------");
                        System.out.println("DATE: "+i.date);
                        System.out.println("ENERGY: "+i.energy);
                        System.out.println("IMAGE: "+i.image);
                        System.out.println("MEAL: "+i.meal);
                        System.out.println("-----------------------------------");

                    }
                    System.out.println("RESPOSTA "+response.body());

                }else{
                    //se foi erro
                    Log.i("TAG","Erro:  "+response.code());
                }

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                //não conseguiu fazer a requisicão
                Log.e(TAG,"Error: "+t.getMessage());
            }
        });

    }
}
