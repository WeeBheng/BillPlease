package sg.edu.rp.c346.id22016788.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    TextView TotalBill;
    TextView SplitBill;
    ToggleButton SVSToggle;
    ToggleButton GSTToggle;
    EditText DiscountInput;
    EditText AmountInput;
    EditText PaxInput;
    Button SplitButton;
    Button ResetButton;
    RadioGroup PaymentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TotalBill = findViewById(R.id.totalbill);
        SplitBill = findViewById(R.id.splitbill);
        SVSToggle = findViewById(R.id.SVStoggle);
        GSTToggle = findViewById(R.id.GSTtoggle);
        DiscountInput = findViewById(R.id.discountInput);
        AmountInput = findViewById(R.id.amountInput);
        PaxInput = findViewById(R.id.paxInput);
        SplitButton = findViewById(R.id.splitButton);
        ResetButton = findViewById(R.id.resetButton);
        PaymentType = findViewById(R.id.paymentType);

        SplitButton.OnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action

                //Assigning values and variables
                String original = AmountInput.getText().toString();
                int originalprice = Integer.parseInt(original);
                String paxnum = PaxInput.getText().toString();
                int pax = Integer.parseInt(paxnum);
                String discountpercent = DiscountInput.getText().toString();
                int discount = Integer.parseInt(discountpercent);

                //Price calculation after GST, SVS and discount
                boolean isChecked1 = GSTToggle.isChecked();
                boolean isChecked2 = SVSToggle.isChecked();
                
                if (isChecked2) {
                    int originalprice = originalprice * 1.1;
                }
                else if (isChecked1) {
                    int originalprice = originalprice * 1.08;
                }
                else if (isChecked1 && isChecked2) {
                    int originalprice = originalprice * 1.08 * 1.1;
                }

                int total = originalprice;
                int total1 = total*(1 - (discount/100));

                //Output messages
                String stringResponse1 = "Total Bill: $" + total1;
                String stringResponse2 = "Each Pays: $" + (total1/pax) + " in cash";
                String stringResponse3 = "Each Pays: $" + (total1/pax) + " via PayNow to 912345678";

                int checkedRadioId = PaymentType.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.cashpayment){
                    // Write the code when male selected
                    TotalBill.setText(stringResponse1);
                    SplitBill.setText(stringResponse2);
                }
                else{
                    TotalBill.setText(stringResponse1);
                    SplitBill.setText(stringResponse3);
                }

            }
        });
    }
}