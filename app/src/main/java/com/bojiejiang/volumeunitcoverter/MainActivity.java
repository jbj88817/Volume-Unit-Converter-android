package com.bojiejiang.volumeunitcoverter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private Spinner unitTypeSpinner;
    private EditText amountTextView;
    protected double mDoubleToConvert;

    TextView teaspoonTextView, tablespoonTextView, cupTextView, ounceTextView,
            pintTextView, quartTextView, gallonTextView, poundTextView,
            milliliterTextView, literTextView, milligramTextView, kilogramTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemsToUnitTypeSpinner();

        addListenerToUnitTypeSpinner();

        amountTextView = (EditText) findViewById(R.id.amount_edit_text);
        amountTextView.addTextChangedListener(amountWatcher);

        initializeTextView();
    }

    public void initializeTextView() {

        teaspoonTextView = (TextView) findViewById(R.id.tsp_text_view);
        tablespoonTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ounceTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.pint_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        poundTextView = (TextView) findViewById(R.id.pound_text_view);
        milliliterTextView = (TextView) findViewById(R.id.ml_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        milligramTextView = (TextView) findViewById(R.id.mg_text_view);
        kilogramTextView = (TextView) findViewById(R.id.kg_text_view);
    }

    public void addItemsToUnitTypeSpinner() {

        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.conversion_types,
                        android.R.layout.simple_spinner_item);

        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);
    }

    public void addListenerToUnitTypeSpinner() {

        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                String itemSelectedInSpinner =
                        parent.getItemAtPosition(pos).toString();

                checkIfConvertingFromTsp(itemSelectedInSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                updateUnitTypeUsingTsp(Quantity.Unit.tsp);
            }
        });
    }

    public void checkIfConvertingFromTsp(String currentUnit) {
        if (currentUnit.equals("teaspoon")) {
            updateUnitTypeUsingTsp(Quantity.Unit.tsp);
        } else if (currentUnit.equals("tablespoon")) {
            updateTypesUsingOther(Quantity.Unit.tbs);
        } else if (currentUnit.equals("cup")) {
            updateTypesUsingOther(Quantity.Unit.cup);
        } else if (currentUnit.equals("ounce")) {
            updateTypesUsingOther(Quantity.Unit.oz);
        } else if (currentUnit.equals("pint")) {
            updateTypesUsingOther(Quantity.Unit.pint);
        } else if (currentUnit.equals("quart")) {
            updateTypesUsingOther(Quantity.Unit.quart);
        } else if (currentUnit.equals("gallon")) {
            updateTypesUsingOther(Quantity.Unit.gallon);
        } else if (currentUnit.equals("pound")) {
            updateTypesUsingOther(Quantity.Unit.pound);
        } else if (currentUnit.equals("milliliter")) {
            updateTypesUsingOther(Quantity.Unit.ml);
        } else if (currentUnit.equals("liter")) {
            updateTypesUsingOther(Quantity.Unit.liter);
        } else if (currentUnit.equals("milligram")) {
            updateTypesUsingOther(Quantity.Unit.mg);
        } else if (currentUnit.equals("kilogram")) {
            updateTypesUsingOther(Quantity.Unit.kg);
        }
    }

    public void updateUnitTypeUsingTsp(Quantity.Unit currentUnit) {

        String teaspoonValueAndUnit = mDoubleToConvert + " tsp";

        teaspoonTextView.setText(teaspoonValueAndUnit);

        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, Quantity.Unit.kg, kilogramTextView);

    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert,
                                            Quantity.Unit unitConvertingTo,
                                            TextView theTextView) {

        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);

        String tempUnit =
                unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);
    }

    public void updateTypesUsingOther(Quantity.Unit currentUnit) {


        Quantity currentQuantitySelected = new Quantity(mDoubleToConvert, currentUnit);

        String valueInTeaspoons =
                currentQuantitySelected.to(Quantity.Unit.tsp).toString();

        teaspoonTextView.setText(valueInTeaspoons);

        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(mDoubleToConvert, currentUnit,
                Quantity.Unit.kg, kilogramTextView);

        if (currentUnit.name().equals(currentQuantitySelected.unit.name())) {

            String currentUnitTextViewText = mDoubleToConvert + " " +
                    currentQuantitySelected.unit.name();

            String currentTextViewName = currentQuantitySelected.unit.name() +
                    "_text_view";

            int currentId =
                    getResources().getIdentifier(currentTextViewName, "id",
                            MainActivity.this.getPackageName());

            TextView currentTextView = (TextView) findViewById(currentId);

            currentTextView.setText(currentUnitTextViewText);
        }

    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert,
                                            Quantity.Unit currentUnit,
                                            Quantity.Unit preferredUnit,
                                            TextView targetTextView) {

        Quantity currentQuantitySelected = new
                Quantity(doubleToConvert, currentUnit);

        String tempTextViewText =
                currentQuantitySelected.to(Quantity.Unit.tsp).to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);
    }

    private TextWatcher amountWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            mDoubleToConvert = 1.0;
            addListenerToUnitTypeSpinner();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {

                mDoubleToConvert = Double.parseDouble(s.toString());

            } catch (NumberFormatException e) {

                mDoubleToConvert = 0.0;

            }

            addListenerToUnitTypeSpinner();

        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
