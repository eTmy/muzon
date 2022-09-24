package com.etmy.onlinerpg.core;

import com.etmy.onlinerpg.abstraction.Item;
import com.etmy.onlinerpg.exception.NotFoundMessageException;
import com.etmy.onlinerpg.item.ItemType;
import com.etmy.onlinerpg.item.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserTest {

    User user = new User(new Account("Tester", "test"));
    @Mock
    Quest quest;
    @Mock
    Weapon weapon;
    @Mock
    Item item;

    @BeforeEach
    void setup() {

    }

    @Test
    void testGetQuest_WhenQuestFound_ShouldReturnQuest() {
        user.addQuest(quest);
        when(quest.getId()).thenReturn(1);

        assertEquals(quest, user.getQuest(1));
    }

    @Test
    void testGetQuest_WhenQuestNotFound_ShouldThrowNotFoundMessageException() {
        assertThrows(NotFoundMessageException.class, () -> user.getQuest(1));
    }

    @Test
    void testTakeItem_WhenItemTypeWeapon_ShouldAddToInventory() {
        when(item.getType()).thenReturn(ItemType.WEAPON);
        user.takeItem(item);

        assertEquals(item, user.getInventory().get(0));
    }

}