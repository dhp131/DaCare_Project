<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical"
android:padding="16dp">

    <TextView
android:id="@+id/questionTextView"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:textSize="18sp" />

    <RadioGroup
android:id="@+id/optionsRadioGroup"
android:layout_width="match_parent"
android:layout_height="wrap_content" />

    <TextView
android:id="@+id/feedbackTextView"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:textSize="16sp"
android:textColor="@android:color/holo_green_dark"
android:visibility="gone" />

    <Button
android:id="@+id/submitButton"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Submit" />
</LinearLayout>