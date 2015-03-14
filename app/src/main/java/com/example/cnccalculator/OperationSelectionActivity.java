package com.example.cnccalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.example.cnccalculator.model.Constants;
import com.example.cnccalculator.model.CornerSelection;
import com.example.cnccalculator.model.PositionSelection;


public class OperationSelectionActivity extends ActionBarActivity {

    PositionSelection position;
    CornerSelection corner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_selection);

        final RadioGroup optionRG  = (RadioGroup) findViewById(R.id.optionRG);
        final RadioGroup positionRG = (RadioGroup) findViewById(R.id.positionRG);
        optionRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int j = 0; j < group.getChildCount(); j++) {
                    final ToggleButton view = (ToggleButton) group.getChildAt(j);
                    view.setChecked(view.getId() == checkedId);
                }
            }
        });

        positionRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.posicionRB1 :
                        position = PositionSelection.First; break;
                    case R.id.posicionRB2 :
                        position = PositionSelection.Second; break;
                    case R.id.posicionRB3 :
                        position = PositionSelection.Third; break;
                    case R.id.posicionRB4 :
                        position = PositionSelection.Fourth; break;
                    default:

                }
                if (corner != null) {
                    sendIntent();
                }
            }
        });
    }

   public void onToggle(View view) {
        ((RadioGroup) view.getParent()).check(view.getId());
        switch (view.getId()) {
            case R.id.first_option_button:
                corner = CornerSelection.Horizontal; break;
            case R.id.second_option_button:
                corner = CornerSelection.Vertical; break;
            default:

        }
        if(position != null) {
            sendIntent();
        }
    }
    private void sendIntent() {
        Intent calculatorIntent = new Intent(this,CalculatorActivity.class);
        calculatorIntent.putExtra(Constants.CORNER_SELECTION_PARAMETER,corner.toString());
        calculatorIntent.putExtra(Constants.POSITION_SELECTION_PARAMETER,position.toString());
        this.startActivity(calculatorIntent);
    }
}
