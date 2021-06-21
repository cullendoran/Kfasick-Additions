package me.kfasick.kfasickadditions;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;

class FireCake extends SlimefunItem {

    public FireCake(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        BlockUseHandler blockUseHandler = this::onBlockRightClick;
        addItemHandler(blockUseHandler);

        ItemUseHandler itemUseHandler = this::onItemUseRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onBlockRightClick(PlayerRightClickEvent event) {
        // This will prevent the Player from eating this cake.
        event.cancel();
        // Now set the Player on fire for 5 seconds
        event.getPlayer().setFireTicks(5 * 20);
    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {
        // Calling event.cancel() in here would prevent the cake
        // from being placed down.
        event.getPlayer().giveExpLevels(1);
    }

}

public class KfasickAdditions extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this);
        class FireCake extends SlimefunItem {

            public FireCake(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
                super(category, item, recipeType, recipe);
            }

            @Override
            public void preRegister() {
                BlockUseHandler blockUseHandler = this::onBlockRightClick;
                addItemHandler(blockUseHandler);

                ItemUseHandler itemUseHandler = this::onItemUseRightClick;
                addItemHandler(itemUseHandler);
            }

            private void onBlockRightClick(PlayerRightClickEvent event) {
                // This will prevent the Player from eating this cake.
                event.cancel();
                // Now set the Player on fire for 5 seconds
                event.getPlayer().setFireTicks(5 * 20);
            }

            private void onItemUseRightClick(PlayerRightClickEvent event) {
                // Calling event.cancel() in here would prevent the cake
                // from being placed down.
                event.getPlayer().giveExpLevels(1);
            }

        }
        NamespacedKey categoryId = new NamespacedKey(this, "kfasick_additions");
        CustomItem categoryItem = new CustomItem(Material.DIAMOND, "Kfasick Additions");

        // Our custom Category
        Category category = new Category(categoryId, categoryItem);

        // The custom item for our SlimefunItem
        SlimefunItemStack itemStack = new SlimefunItemStack("TESTING_ITEM", Material.EMERALD, "Testing Item Only", "", "Testing");

        // A 3x3 shape representing our recipe
        ItemStack[] recipe = {
                new ItemStack(Material.DIAMOND),    null,                               new ItemStack(Material.DIAMOND),
                null,                               SlimefunItems.CARBONADO,            null,
                new ItemStack(Material.DIAMOND),    null,                               new ItemStack(Material.DIAMOND)
        };

        SlimefunItem sfItem = new SlimefunItem(category, itemStack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
        sfItem.register(this);
        // Our item is now registered




    }

    @Override
    public void onDisable() {
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }
}

