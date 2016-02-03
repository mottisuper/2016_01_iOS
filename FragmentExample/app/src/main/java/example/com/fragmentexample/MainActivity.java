package example.com.fragmentexample;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout layoutNavigation;
    MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutNavigation = (LinearLayout)findViewById(R.id.layoutNavigation);

    }

    public void btnShowMyFragment(View view) {
        layoutNavigation.setVisibility(View.GONE);
        myFragment = new MyFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_container, myFragment).commit();
    }

    public void btnCloseMyFragment(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.remove(myFragment);
        fragmentTransaction.commit();
        layoutNavigation.setVisibility(View.VISIBLE);
    }


    public static class MyFragment extends Fragment{

        MyInterface myInterface;


        void setMyInterface(MyInterface myInterface){
            this.myInterface = myInterface;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_my, container, false);
            Button btnBack = (Button) view.findViewById(R.id.btnBack);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).btnCloseMyFragment();
                    if(myInterface != null)
                        myInterface.stam();
                }
            });
            return view;
        }

        static interface MyInterface{
            public void stam();
        }
    }
}
