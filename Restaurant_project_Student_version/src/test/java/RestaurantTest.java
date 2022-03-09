import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    private Restaurant restaurant;
    private LocalTime openingTime, closingTime;

    @BeforeEach
    private void initialize() {
        openingTime = LocalTime.parse("10:30:00");
        closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        openingTime = LocalTime.parse("10:30:00");
        closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Paradise","Hyderabad",openingTime,closingTime);
        assertTrue(restaurant.isRestaurantOpen(), "Restaurant is not open Now");
        }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        openingTime = LocalTime.parse("01:50:50");
        closingTime = LocalTime.parse("02:30:30");
        restaurant = new Restaurant("Swagath","Hyderabad",openingTime,closingTime);
        assertFalse(restaurant.isRestaurantOpen(), "Restaurant is open Now");

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        // removed here and added the code @ before each annotation
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        // removed here and added the code @ before each annotation
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        // removed here and added the code @ before each annotation
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void verify_order_total_on_for_OneSelected_items(){
        List<Item> itemsSelected = new ArrayList<>();
        List<Item> restaurantMenu = restaurant.getMenu();
        itemsSelected.add(( restaurantMenu).get(0));
        assertEquals(119,restaurant.total_order_value_of_selected_items(itemsSelected));
    }
    @Test
    public void verify_order_total_on_for_TwoSelected_items(){
        List<Item> itemsSelected = new ArrayList<>();
        List<Item> restaurantMenu = restaurant.getMenu();
        itemsSelected.add(restaurantMenu.get(0));
        itemsSelected.add(restaurantMenu.get(1));
        assertEquals(388,restaurant.total_order_value_of_selected_items(itemsSelected));
    }
}