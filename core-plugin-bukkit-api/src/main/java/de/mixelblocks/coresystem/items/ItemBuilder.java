/*
 * mixelblocks-coresystem | Copyright (C) 2022 | mixelblocks.de | LuciferMorningstarDev
 * Licensed under the MIT License
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * You should have received a copy of the MIT License
 *
 * Repository:
 *     Github:          https://github.com/MixelBlccks/mixelblocks-coresystem
 *
 * Contact:
 *     Discord Server:  https://mixelblocks.de/discord
 *     Website:         https://mixelblocks.de/
 *     DashBoard:       https://dash.mixelblocks.de/
 *     Mail:            contact@mixelblocks.de
 *     Minecraft:       mixelblocks.de:25565
 *
 */
package de.mixelblocks.coresystem.items;

import de.mixelblocks.coresystem.serialization.ComponentSerialization;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.items:ItemBuilder
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta meta;

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.meta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material, 1);
        this.meta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.meta = this.itemStack.getItemMeta();
    }

    public ItemBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder displayName(String displayName) {
        meta.displayName(
                ComponentSerialization.sectionOnly.deserialize(
                        ComponentSerialization.etAndHEX.serialize(
                                ComponentSerialization.etAndHEX.deserialize(
                                        displayName
                                )
                        )
                )
        );
        return this;
    }

    public ItemBuilder name(String displayName) {
        return displayName(displayName);
    }

    public ItemBuilder unbreakable(boolean canBreak) {
        meta.setUnbreakable(canBreak);
        return this;
    }

    public ItemBuilder customModelData(int modelId) {
        meta.setCustomModelData(modelId);
        return this;
    }

    public ItemBuilder itemFlag(ItemFlag flag) {
        itemStack.getItemMeta().getItemFlags().add(flag);
        return this;
    }

    public ItemBuilder itemFlags(ItemFlag ... flags) {
        for(ItemFlag flag : flags) meta.getItemFlags().add(flag);
        return this;
    }

    public ItemBuilder enchantment(EnchantmentBuilder enchantment) {
        meta.addEnchant(enchantment.getType(), enchantment.getLevel(), enchantment.isIgnoreLevelRestrictions());
        return this;
    }

    public ItemBuilder enchantments(EnchantmentBuilder ... enchantments) {
        for(EnchantmentBuilder enchantment : enchantments) meta.addEnchant(enchantment.getType(), enchantment.getLevel(), enchantment.isIgnoreLevelRestrictions());
        return this;
    }

    public ItemBuilder lore(String lore) {
        List<Component> lores = new ArrayList<>();
        lores.add(
                ComponentSerialization.sectionOnly.deserialize(
                        ComponentSerialization.etAndHEX.serialize(
                                ComponentSerialization.etAndHEX.deserialize(
                                        lore
                                )
                        )
                )
        );
        meta.lore(lores);
        return this;
    }

    public ItemBuilder lore(String ... loreLines) {
        List<Component> lores = new ArrayList<>();
        for(String line: loreLines) lores.add(
                ComponentSerialization.sectionOnly.deserialize(
                        ComponentSerialization.etAndHEX.serialize(
                                ComponentSerialization.etAndHEX.deserialize(
                                        line
                                )
                        )
                )
        );
        meta.lore(lores);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack complete() {
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static class EnchantmentBuilder {

        private Enchantment type;
        private int level;
        private boolean ignoreLevelRestrictions = false;

        public EnchantmentBuilder(Enchantment enchantment, int level) {
            this.type = enchantment;
            this.level = level;
        }

        public EnchantmentBuilder(Enchantment enchantment, int level, boolean ignoreLevelRestrictions) {
            this.type = enchantment;
            this.level = level;
            this.ignoreLevelRestrictions = ignoreLevelRestrictions;
        }

        public Enchantment getType() {
            return type;
        }

        public int getLevel() {
            return level;
        }

        public boolean isIgnoreLevelRestrictions() {
            return ignoreLevelRestrictions;
        }

        public static EnchantmentBuilder build(Enchantment enchantment, int level) {
            return new EnchantmentBuilder(enchantment, level);
        }

        public static EnchantmentBuilder build(Enchantment enchantment, int level, boolean ignoreLevelRestrictions) {
            return new EnchantmentBuilder(enchantment, level, ignoreLevelRestrictions);
        }
    }
}
