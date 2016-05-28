package io.monteirodev.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(
                name,
                price,
                hasWhippedCream,
                hasChocolate);
        // displayMessage(priceMessage);

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.fromParts("mailto","", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
    }

    public void increment(View view) {
        if (quantity == 10){
            Toast.makeText(MainActivity.this, "You cannot have more than 10 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
        // int price = calculatePrice();
        // displayPrice(price);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(MainActivity.this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
        // int price = calculatePrice();
        //displayPrice(price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);

        quantityTextView.setText(number + "");
    }

    /**
     * Calculates the price of the order based on whipped cream and chocolate.
     *
     * @param hasWhippedCream
     * @param hasChocolate
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {

        int basePrice = 5;
        if (hasWhippedCream) basePrice += 1;
        if (hasChocolate) basePrice += 2;

        return basePrice * quantity;
    }

    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    private String createOrderSummary(String name, int price, boolean hasWhippedCream,  boolean hasChocolate){
        String priceMessage = getString(R.string.order_summary_name,name);
        priceMessage += "\n";
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream,hasWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, hasChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_price, price);
        priceMessage += "\n";
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

}