/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.skeletonapp;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Make sure that the main launcher activity opens up properly, which will be
 * verified by {@link #testActivityTestCaseSetUpProperly}.
 */
public class SkeletonAppTest extends ActivityInstrumentationTestCase2<SkeletonActivity> {
	
	Activity mActivity;
	EditText mText;
	Button mBack;
	Button mClear;
	ImageView mImage;
	
	
	  /**
     * Required no argument constructor
     */
    public SkeletonAppTest()
    {
        this("SkeletonAppTest");
    }

    
    /**
     * @param name
     */
    public SkeletonAppTest(String name)
    {
        super(SkeletonActivity.class);
        setName(name);
    }
    
    /* (non-Javadoc)
     * @see android.test.ActivityInstrumentationTestCase2#setUp()
     */
    @Override
	protected void setUp() throws Exception
    {
        super.setUp();
        mActivity = getActivity();
        mText = (EditText) mActivity.findViewById(com.example.android.skeletonapp.R.id.editor);
        mBack = (Button) mActivity.findViewById(com.example.android.skeletonapp.R.id.back);
        mClear = (Button) mActivity.findViewById(com.example.android.skeletonapp.R.id.clear);
        mImage = (ImageView) mActivity.findViewById(com.example.android.skeletonapp.R.id.image);
    }

    
    /* (non-Javadoc)
     * @see android.test.ActivityInstrumentationTestCase2#tearDown()
     */
    @Override
	protected void tearDown() throws Exception
    {
        super.tearDown();
    }


    /**
     * pre-condition testing
     */
    public final void testPreconditions()
    {
        assertNotNull(mActivity);
    }
    
    /**
     * test whether required fields are present
     */
    public final void testHasFields()
    {
    	assertNotNull(mText);
    	assertNotNull(mBack);
    	assertNotNull(mClear);
    	assertNotNull(mImage);
    }
    
    /**
     * Test back button click - NEED TO FIND WAY TO TEST ACTIVITY CHANGES!
     */
/*    public final void testBackButton()
    {
    	
    	mActivity.runOnUiThread(new Runnable() {
  	      @Override
			public void run() {
  	        mBack.performClick();
  	      }
  	    });
    	
    }*/
    
    /**
     * Test Back button says "Back"
     */
    public final void testBackButtonText()
    {
    	String expected = "Back";
    	String actual = mBack.getText().toString();
    	assertEquals(String.format("Back button should say %s, but says %s",expected, actual),expected,actual);
    }
    
    /**
     * Test Clear buttons says "Clear"
     */
    public final void testClearButtonText()
    {
    	String expected = "Clear";
    	String actual = mClear.getText().toString();
    	assertEquals(String.format("Back button should say %s, but says %s",expected, actual),expected,actual);
    }
    
    /**
     * Test Clear button's "Clear" text is red
     */
    public final void testClearButtonTextColor()
    {
    	int expected = Color.RED;
    	int actual = mClear.getCurrentTextColor();

    	assertEquals(String.format("Clear button should have %d colored text, but has %d colored text",expected, actual),expected,actual);
    }
    
    /**
     * Test opening message is present
     */
    public final void testTextFieldPrecondition()
    {
    	String expected = String.format("Hello there, you Activity!");
    	String actual = mText.getText().toString();
    	
    	assertEquals(String.format("Expected %s but was %s",expected, actual), expected, actual);
    }

    
    /**
     * Test Clear button clears the text field
     */
    public final void testClearButtonClearsTextField()
    {
    	mActivity.runOnUiThread(new Runnable() {
	      @Override
		public void run() {
	        mClear.performClick();
	      }
	    });
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	String expected = "";
    	String actual = mText.getText().toString();
    	assertEquals(String.format("Expected the string to be %s (empty), was %s",expected,actual),expected,actual);
    }
    
    /**
     * Test order of items in footer - back button to left of image
     */
    public final void testImageIsRightOfBackButton()
    {
    	int imageLeft= mImage.getLeft();
    	int backLeft= mBack.getLeft();
    	assertTrue(String.format("Image's left pixel is %d and back's left pixel is %d",imageLeft,backLeft),imageLeft>backLeft);
    }
    
    /**
     * Test order of items in footer - image to left of clear button
     */
    public final void testImageIsLeftOfClearButton()
    {
    	int imageLeft= mImage.getLeft();
    	int clearLeft= mClear.getLeft();
    	assertTrue(String.format("Image's left pixel is %d and clear's left pixel is %d",imageLeft,imageLeft),imageLeft<clearLeft);
    }
}