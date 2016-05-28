package io.monteirodev.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    private int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
//        display(quantity);
//        displayPrice(quantity * 5);
        int price = calculatePrice();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        String priceMessage = createOrderSummary(price, whippedCreamCheckBox.isChecked());
        displayMessage(priceMessage);
    }

    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
        int price = calculatePrice();
        // displayPrice(price);
    }

    public void decrement(View view) {
        if (quantity <= 1) {
            quantity = 0;
        } else {
            quantity--;
        }
        displayQuantity(quantity);
        int price = calculatePrice();
        //displayPrice(price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);

        quantityTextView.setText("" + number);
    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
        Context context = this;

    }

    private String createOrderSummary(int price, boolean hasWhippedCream){
        return "Name: Kaptain Kunal" +
                "\nHas Whipped cream? " + hasWhippedCream +
                "\nQuantity: "+quantity+
                "\nTotal: £"+price+
                "\nThank you!";
    }

}