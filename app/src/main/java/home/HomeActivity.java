package home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.fiz.myapplication.R;

/**
 * Created by Fiz on 16/3/25.
 */
public class HomeActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("demo");
        toolbar.setSubtitle("1234");
        toolbar.setLogo(R.drawable.ic_launcher);
        setSupportActionBar(toolbar);

    }

}
