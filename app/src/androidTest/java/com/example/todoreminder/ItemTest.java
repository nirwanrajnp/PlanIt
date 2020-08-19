/*package com.example.todoreminder;

import android.content.ClipData;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ItemTest {
    ArrayList<ClipData.Item> items;

    // Generate some items
    @Before
    public void createItems() {
        items = new ArrayList<>();
        items.add(new Item("I love you, buddy!",false));
        items.add(new Item("Look, last night was a mistake.",false));
        items.add(new Item("How can any of us trust you?",false));
    }

    // Test add/remove of list items functionality
    @Test
    public void itemMethods() {
        assertEquals(3, items.size());
        items.remove(0);
        assertEquals(2, items.size());
        items.add(new Item ("New Item 1", false));
        items.add(new Item ("New Item 2", false));
        assertEquals(4, items.size());
    }

    // Test status completion toggle functionality
    @Test
    public void testChangeComplete() {
        ClipData.Item first = items.get(0);
        assertEquals(false, first.isComplete());
        items.get(0).setComplete(true);
        assertEquals(true, first.isComplete());
        items.get(0).setComplete(false);
        assertEquals(false, first.isComplete());
    }

    // Test title edit functionality
    @Test
    public void testChangeTitle() {
        ClipData.Item first = items.get(0);
        assertEquals("I love you, buddy!", first.getTitle());
        items.get(0).setTitle("New Title!");
        assertEquals("New Title!", first.getTitle());
    }
}
*/