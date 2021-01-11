import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        // Use spy to achieve this case
        restaurant = new Restaurant("Dummy", "Pune", LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"));
        Restaurant restaurantMock = Mockito.spy(restaurant);
        LocalTime testCurrentTime = LocalTime.parse("10:59:00");
        Mockito.when(restaurantMock.getCurrentTime()).thenReturn(testCurrentTime);
        boolean openOrClose = restaurantMock.isRestaurantOpen();
        Assertions.assertTrue(openOrClose);

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        restaurant = new Restaurant("Dummy", "Pune", LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"));
        Restaurant restaurantMock = Mockito.spy(restaurant);
        LocalTime timeCurrent = LocalTime.parse("19:00:00"); // after closing time
        Mockito.when(restaurantMock.getCurrentTime()).thenReturn(timeCurrent);
        boolean openOrClose = restaurantMock.isRestaurantOpen();
        Assertions.assertFalse(openOrClose);

    }

    // Implementing the TDD fail cases
    @Test
    public void verify_the_order_value_of_the_items_in_the_list(){
        restaurant = new Restaurant("Ritz", "Pune",
                LocalTime.parse("10:00:00"), LocalTime.parse("18:00:00"));
        restaurant.addToMenu("Vanilla Ice", 3);
        restaurant.addToMenu("Cheese Potato", 4);
        restaurant.addToMenu("Sandwich", 2);
        restaurant.addToMenu("Honey potato",3);
        SelectedItems Items = new SelectedItems();
        Items.addSelectedItems(Item.returnItemObject("Sandwich", 2));
        Items.addSelectedItems(Item.returnItemObject("Honey potato", 3));
        Items.addSelectedItems(Item.returnItemObject("Vanilla Ice", 3));
        Assertions.assertEquals(8,Items.getTotalValueOfSelectedItems());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    // Refactored Code - 10 percent points
    public Restaurant repeatedRefactoredCode(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        return restaurant;

    }
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        Restaurant restaurant = repeatedRefactoredCode();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        Restaurant restaurant = repeatedRefactoredCode();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        Restaurant restaurant = repeatedRefactoredCode();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}