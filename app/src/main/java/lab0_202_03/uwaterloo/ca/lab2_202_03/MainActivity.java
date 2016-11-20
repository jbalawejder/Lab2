package lab0_202_03.uwaterloo.ca.lab2_202_03;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import ca.uwaterloo.sensortoy.LineGraphView;

public class MainActivity extends AppCompatActivity {
    // For data extraction only
//    File root = android.os.Environment.getExternalStorageDirectory();
//    File dir = new File (root.getAbsolutePath() + "/download");
//    File file = new File(dir, "Output.csv");

    // Instantiate LineGraphView object
    LineGraphView accelGraph;

    // Instantiate global listeners for resetting purposes
    AccelerometerEventListener accelListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout); // Create the parent layout
        layout.setOrientation(LinearLayout.VERTICAL); // Set the orientation to vertical

        // Instantiating accelerometer graph
        // Create the line graph that tracks the x, y, and z values of the accelerometer
        accelGraph = new LineGraphView(getApplicationContext(), 100, Arrays.asList("x", "y", "z"));
        layout.addView(accelGraph); // Add the graph to the parent layout
        accelGraph.setVisibility(View.VISIBLE); // Make the graph visible

        // Accelerometer Label for outputting accelerometer readings
        TextView accelSensorLabel = new TextView(getApplicationContext());
        accelSensorLabel.setTextColor(Color.BLACK);
        layout.addView(accelSensorLabel); // Add the label to the parent layout

        // Request the sensor manager
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get instances of each sensor with the sensor manager
        Sensor accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION); // Get the accelerometer sensor

        // Instantiate event listeners to receive the events from the sensors
        accelListener = new AccelerometerEventListener(accelSensorLabel, accelGraph);

        // Register each listener to the respective sensor
        sensorManager.registerListener(accelListener, accelSensor, SensorManager.SENSOR_DELAY_FASTEST);

        // Button to reset the step account pedometer
        Button resetButton = new Button(getApplicationContext());
        resetButton.setText("Reset Step Count");
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                accelListener.reset(); // Reset the accelerometer sensor
            }
        });
        layout.addView(resetButton); // Add the button to the parent layout

//        dir.mkdir();
//        int interval = 50;
//        int duration = 20000;
//        TimerTask timer1Task = new TimerTask() {
//            @Override
//            public void run() {
//                writeToFile(accelListener.getValues());
//            }
//        };
//
//        TimerTask timer2Task = new TimerTask() {
//            @Override
//            public void run() {
//                System.exit(0);
//            }
//        };
//
//        Timer timer1 = new Timer();
//        Timer timer2 = new Timer();
//        timer1.schedule(timer1Task, interval, interval);
//        timer2.schedule(timer2Task, duration);
    }

    // Method for data extraction and file write out
//    private void writeToFile(float[] values){
//        try {
//            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
//            pw.append(values[0] + ", " + values[1] + ", " + values[2] + "\n");
//            pw.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

