package com.bojiejiang.volumeunitcoverter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

        initializeTextView();
    }

    public void initializeTextView(){

        teaspoonTextView = (TextView) findViewById(R.id.tsp_text_view);
        tablespoonTextView = (TextView)findViewById(R.id.tbs_text_view);
        cupTextView = (TextView)findViewById(R.id.cup_text_view);
        ounceTextView = (TextView)findViewById(R.id.oz_text_view);
        pintTextView = (TextView)findViewById(R.id.pint_text_view);
        quartTextView = (TextView)findViewById(R.id.quart_text_view);
        gallonTextView = (TextView)findViewById(R.id.gallon_text_view);
        poundTextView = (TextView)findViewById(R.id.pound_text_view);
        milliliterTextView = (TextView)findViewById(R.id.ml_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        milligramTextView = (TextView) findViewById(R.id.mg_text_view);
        kilogramTextView = (TextView) findViewById(R.id.kg_text_view);
    }

    public void addItemsToUnitTypeSpinner(){

        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);

        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.conversion_types,
                        android.R.layout.simple_spinner_item);

        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);
    }

    public void addListenerToUnitTypeSpinner(){

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
                // TODO maybe add something here later
            }
        });
    }

    public void checkIfConvertingFromTsp(String currentUnit){
        if(currentUnit.equals("teaspoon")){
            updateUnitTypeUsingTsp(Quantity.Unit.tsp);
        }else if (currentUnit.equals("tablespoon")){
            updateTypesUsingOther(Quantity.Unit.tbs);
        }else if (currentUnit.equals("cup")){
            updateTypesUsingOther(Quantity.Unit.cup);
        }else if (currentUnit.equals("ounce")){
            updateTypesUsingOther(Quantity.Unit.oz);
        }else if (currentUnit.equals("pint")){
            updateTypesUsingOther(Quantity.Unit.pint);
        }else if (currentUnit.equals("quart")){
            updateTypesUsingOther(Quantity.Unit.quart);
        }else if (currentUnit.equals("gallon")){
            updateTypesUsingOther(Quantity.Unit.gallon);
        }else if (currentUnit.equals("pound")){
            updateTypesUsingOther(Quantity.Unit.pound);
        }else if (currentUnit.equals("milliliter")){
            updateTypesUsingOther(Quantity.Unit.ml);
        }else if (currentUnit.equals("liter")){
            updateTypesUsingOther(Quantity.Unit.liter);
        }else if (currentUnit.equals("milligram")){
            updateTypesUsingOther(Quantity.Unit.mg);
        }else if (currentUnit.equals("kilogram")){
            updateTypesUsingOther(Quantity.Unit.kg);
        }
    }

    public void  updateUnitTypeUsingTsp(Quantity.Unit currentUnit){
        double doubleToConvert =
                Double.parseDouble(amountTextView.getText().toString());
        String teaspoonValueAndUnit = doubleToConvert + " tsp";

        teaspoonTextView.setText(teaspoonValueAndUnit);

        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.tbs,tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.cup,cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.oz,ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.pint,pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.quart,quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.gallon,gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.pound,poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.ml,milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.liter,literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.mg,milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,Quantity.Unit.kg,kilogramTextView);

    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert,
                                            Quantity.Unit unitConvertingTo,
                                            TextView theTextView){

        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);

        String tempUnit =
                unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);
    }

    public void updateTypesUsingOther(Quantity.Unit currentUnit){

        double doubleToConvert =
                Double.parseDouble(amountTextView.getText().toString());

        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);

        String valueInTeaspoons =
                currentQuantitySelected.to(Quantity.Unit.tsp).toString();

        teaspoonTextView.setText(valueInTeaspoons);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit,
                Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,currentUnit,
                Quantity.Unit.cup,cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,currentUnit,
                Quantity.Unit.oz,ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,currentUnit,
                Quantity.Unit.pint,pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,currentUnit,
                Quantity.Unit.quart,quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,currentUnit,
                Quantity.Unit.gallon,gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,currentUnit,
                Quantity.Unit.pound,poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,currentUnit,
                Quantity.Unit.ml,milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,currentUnit,
                Quantity.Unit.liter,literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,currentUnit,
                Quantity.Unit.mg,milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert,currentUnit,
                Quantity.Unit.kg,kilogramTextView);

        if(currentUnit.name().equals(currentQuantitySelected.unit.name())){

            String currentUnitTextViewText = doubleToConvert + " " +
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
                                            TextView targetTextView){

        Quantity currentQuantitySelected = new
                Quantity(doubleToConvert, currentUnit);

        String tempTextViewText =
                currentQuantitySelected.to(Quantity.Unit.tsp).to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);
    }



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
