package com.shakib_hussain.place_finder.Activities;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.shakib_hussain.place_finder.Adapter.ImageAdapter;
import com.shakib_hussain.place_finder.MainActivity;
import com.shakib_hussain.place_finder.R;

import java.util.ArrayList;

public class Add_Pg_Activity extends AppCompatActivity implements ListenerEdit {

    // Chips
    ChipGroup chipGroup;
    String[] option;
    MaterialAutoCompleteTextView autoCompleteTextView;

    // Set Image
    int PICK_IMAGE_MULTIPLE = 1;
    ImageAdapter imageAdapter;
    ArrayList<Uri> mArrayUri;
    RecyclerView mImageRecycler;

    Button selectImgBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pg);


        selectImgBtn = findViewById(R.id.selectImages);
        selectImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent();
            }
        });

        setChips();
        setAdapter();

    }


    private void setAdapter() {

        mArrayUri = new ArrayList<Uri>();
        mImageRecycler = findViewById(R.id.imageRecyclerV);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        mImageRecycler.setLayoutManager(staggeredGridLayoutManager);
        imageAdapter = new ImageAdapter(Add_Pg_Activity.this, mArrayUri, this);
        mImageRecycler.setAdapter(imageAdapter);
        imageAdapter.getAllActors(mArrayUri);

    }


    public void intent() {

        Intent intent = new Intent();
        intent.setType("image/*");

        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_MULTIPLE);
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onStart() {
        super.onStart();
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                if (cout <= 6) {
                    for (int i = 0; i < cout; i++) {
                        Uri imageurl = data.getClipData().getItemAt(i).getUri();
                        mArrayUri.add(imageurl);
                    }
                } else {
                    Toast.makeText(getApplicationContext(),  getString(R.string.images_limit_exceed), Toast.LENGTH_LONG).show();
                    mArrayUri.clear();
                }

            } else {

                Uri imageurl = data.getData();
                mArrayUri.add(imageurl);
            }
        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClicked(int id) {
        removeItem(id);
    }

    public void removeItem(int position) {
        mArrayUri.remove(position);
        imageAdapter.notifyItemRemoved(position);
    }


    private void setChips() {


        chipGroup = findViewById(R.id.chipGrp);
        autoCompleteTextView = findViewById(R.id.actv);

        option = new String[]{"Wifi", "AC", "Food", "CCTV Cameras", "24/7 security", "RO water"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option_item, option);
        autoCompleteTextView.setText("Select");
        autoCompleteTextView.setAdapter(arrayAdapter);


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String curItem = arrayAdapter.getItem(position).toString();

                Chip chip = new Chip(Add_Pg_Activity.this);
                chip.setText(curItem);
                chip.setElevation(6);
                chip.setCloseIconEnabled(true);


                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        chipGroup.removeView(v);

                    }
                });
                chipGroup.addView(chip, 0);

                Toast.makeText(Add_Pg_Activity.this, curItem + " Selected...",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void saveDetails(View view) {

        Toast.makeText(getApplicationContext(), "Details Successfully Saved....", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }


    public void backActivity(View view) {
        onBackPressed();
        finish();
    }
}
