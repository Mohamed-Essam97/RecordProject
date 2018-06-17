package com.example.mohamed.record;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.gigamole.library.PulseView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment  {


    public FragmentOne() {
        // Required empty public constructor
    }


    PulseView pulseView;

    Chronometer chronometer;
    private boolean running;
    private long pauseoffset;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_one,
                container, false);

        pulseView=view.findViewById(R.id.pv);
        chronometer=view.findViewById(R.id.chronometer);
        final Button Record = (Button) view.findViewById(R.id.btn_Record);
        final Button Pause=view.findViewById(R.id.btn_Pause);
        Button Save=view.findViewById(R.id.btn_Save);

        Pause.setVisibility(View.INVISIBLE);

        Record.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!running)
                {
                    chronometer.setBase(SystemClock.elapsedRealtime()-pauseoffset);
                    chronometer.start();
                    running=true;
                }
                pulseView.startPulse();
                Record.setVisibility(View.INVISIBLE);
                Pause.setVisibility(View.VISIBLE);



                Toast.makeText(FragmentOne.super.getContext(), "Record", Toast.LENGTH_SHORT).show();



            }
        });
        Pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running)
                {
                    chronometer.stop();
                    pauseoffset=SystemClock.elapsedRealtime()-chronometer.getBase();
                    running=false;
                }

                pulseView.finishPulse();
                Pause.setVisibility(View.INVISIBLE);
                Record.setVisibility(View.VISIBLE);


                Toast.makeText(FragmentOne.super.getContext(), "Pause", Toast.LENGTH_SHORT).show();




            }
        });
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseoffset=0;

                Pause.setVisibility(View.INVISIBLE);
                Record.setVisibility(View.VISIBLE);
                pulseView.finishPulse();

                Toast.makeText(FragmentOne.super.getContext(), "Save", Toast.LENGTH_SHORT).show();




            }
        });


        return view;
    }

}
