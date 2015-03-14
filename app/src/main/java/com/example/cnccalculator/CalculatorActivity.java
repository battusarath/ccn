package com.example.cnccalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cnccalculator.model.CNCPoints;
import com.example.cnccalculator.model.Calculator;
import com.example.cnccalculator.model.Constants;
import com.example.cnccalculator.model.CornerSelection;
import com.example.cnccalculator.model.PositionSelection;

public class CalculatorActivity extends ActionBarActivity {

    private Calculator cncCalculator;
    private TextView resultPoint1View;
    private TextView resultPoint2View;
    private double xOffset;
    private double yOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Bundle extrasBundle = getIntent().getExtras();
        CornerSelection selection = CornerSelection.valueOf(extrasBundle.getString(Constants.CORNER_SELECTION_PARAMETER));
        PositionSelection position = PositionSelection.valueOf(extrasBundle.getString(Constants.POSITION_SELECTION_PARAMETER));
        Log.d("debug", selection.toString());
        Log.d("debug", position.toString());

        cncCalculator = new Calculator(selection,position);
        resultPoint1View = ((TextView) this.findViewById(R.id.cnc_result1));
        resultPoint2View = ((TextView) this.findViewById(R.id.cnc_result2));

        ((EditText) this.findViewById(R.id.xoffset_in)).addTextChangedListener(xOffsetWatcher);
        ((EditText) this.findViewById(R.id.yoffset_in)).addTextChangedListener(yOffsetWatcher);
        ((EditText) this.findViewById(R.id.alpha_in)).addTextChangedListener(alphaWatcher);
        ((EditText) this.findViewById(R.id.radio_in)).addTextChangedListener(radioWatcher);
    }

    private TextWatcher xOffsetWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                xOffset = Double.parseDouble(s.toString());
            } catch (Exception e) {
                xOffset = Calculator.default_xoffset;
            }
            cncCalculator.setOrigin(xOffset,yOffset);
            updateResult();
        }
    };

    private TextWatcher yOffsetWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                yOffset = Double.parseDouble(s.toString());
            } catch (Exception e) {
                yOffset = Calculator.default_yoffset;
            }
            cncCalculator.setOrigin(xOffset,yOffset);
            updateResult();
        }
    };

    private TextWatcher alphaWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            double alphaValue;
            try {
                alphaValue = Double.parseDouble(s.toString());
            } catch(Exception e) {
                alphaValue = Calculator.default_alpha;
            }
            cncCalculator.setAlphaDegrees(alphaValue);
            updateResult();
        }
    };

    private TextWatcher radioWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            double radioValue;
            try {
                radioValue = Double.parseDouble(s.toString());
            } catch (Exception e) {
                radioValue = Calculator.default_radio;
            }
            cncCalculator.setRadio(radioValue);
            updateResult();
        }
    };

    private void updateResult() {
        CNCPoints cncPointsResult;
        cncPointsResult = cncCalculator.calculatePoint();
        resultPoint1View.setText(String.format("X: %.2f; Y: %.2f", cncPointsResult.getFirst().getX(), cncPointsResult.getFirst().getY()));
        resultPoint2View.setText(String.format("X: %.2f; Y: %.2f", cncPointsResult.getSecond().getX(), cncPointsResult.getSecond().getY()));
    }

}
